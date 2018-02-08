package com.example.protal.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.protal.config.AlipayConfig;
import com.example.protal.entity.Commodity;
import com.example.protal.entity.Order;
import com.example.protal.entity.Pay;
import com.example.protal.service.CartService;
import com.example.protal.service.OrderService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private CartService cs;

    @Autowired
    private OrderService os;

    @RequestMapping("toOrder")
    public String toOrder(@CookieValue(value = "cart",required = false)String cart,Model model ){
        cs.lookCart(cart,model);
        return  "order";
    }
    @RequestMapping("saveOrder")
    @ResponseBody
    public  String saveOrder(@CookieValue(value = "cart",required = false) String ck){
        //1.创建订单
        //2.设计表结构
        //3订单编号
        String  uuid = UUID.randomUUID().toString();
        long time = System.currentTimeMillis();
        String orderNo = uuid + time;
        //获取购物车
            Map<String,Commodity> cart  = cs.getCart(ck);
             float total = cs.total(cart);

             Order order = new Order();
             order.setOrderno(orderNo);
             order.setTotal(total);
        String format = new SimpleDateFormat("yyyy年MM月dd日hh:mm").format(new Date());
        order.setCreatetime(format);
             String message = JSON.toJSONString(order);
             os.sendOrderMessage(message);


        // 2.支付
        Pay p = new Pay();
        p.setOut_trade_no(orderNo);
        p.setTotal_amount(99);
        p.setSubject("aa");
        p.setBody("");
        //  获得初始化的AlipayClient
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        String json = JSON.toJSONString(p);
        alipayRequest.setBizContent(json);
        try {
            return  alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "1";
    }
}
