package com.example.protal.service;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.protal.util.BaseUtil;
import com.example.protal.util.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.protal.entity.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;



@Service
public class CartService {


    public  void addCart(String sku, HttpServletResponse response, Model model, String ck){
        Map<String,Commodity> cart = null;
        if(org.springframework.util.StringUtils.isEmpty(ck)){
            cart = new LinkedHashMap<>();
            saveCart(sku,cart);
        }else {
            String json = BaseUtil.decode(ck);
           /* cart = JSON.parseObject(json, new TypeReference<Map<String,Commodity>>(){});*/
           cart = JSON.parseObject(json,new TypeReference<Map<String,Commodity>>(){});
            if(cart.containsKey(sku)){
                Commodity commodity = cart.get(sku);
                commodity.setNum(commodity.getNum()+1);

            }else{
                saveCart(sku,cart);
            }
        }
        model.addAttribute("discount",discount(cart));//
        model.addAttribute("cs",cart.values());
        model.addAttribute("total",total(cart));
       /* String  cartjson =  JSON.toJSONString(cart);
        Cookie cookie  = new Cookie("cart", BaseUtil.encode(cartjson));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);*/
       addCookie(response,cart);
    }
    public void saveCart( String sku, Map<String,Commodity> cart){

        String  json = HttpUtil.getData("http://localhost:8081/commodity/findCommodityBySku/"+sku);
        System.out.println(json);
        Commodity commodity = JSON.parseObject(json,Commodity.class);
        commodity.setNum(1);
        cart.put(sku,commodity);
    }

    public float  total (Map<String,Commodity> cart){
        float  t = 0;
        Collection<Commodity> cs = cart.values();
        for(Commodity c : cs ){
            t= t+ c.getNum()*c.getPrice();
        }
        return t;
    }
    public float update(String sku, int num, String ck, HttpServletResponse response) {
                String json = BaseUtil.decode(ck);
        Map<String, Commodity> cart = JSON.parseObject(json, new TypeReference<Map<String, Commodity>>() {});
        Commodity commodity = cart.get(sku);
        commodity.setNum(num);
       /* String cartjson = JSON.toJSONString(cart);
        Cookie cookie = new Cookie("cart",BaseUtil.encode(cartjson));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);*/
       addCookie(response,cart);
        return total(cart);


    }

    public void lookCart(String ck, Model model) {
        String json = BaseUtil.decode(ck);
        Map<String, Commodity> cart = JSON.parseObject(json, new TypeReference<Map<String, Commodity>>() {});
        model.addAttribute("cs",cart.values());
        model.addAttribute("total",total(cart));
        model.addAttribute("discount",discount(cart));
    }

    public void deletCommodityBySkuFromCommodity(String sku, String ck, Model model, HttpServletResponse response) {
        String json = BaseUtil.decode(ck);
        Map<String, Commodity> cart = JSON.parseObject(json, new TypeReference<Map<String, Commodity>>() {});
        cart.remove(sku);
       /* String cartjson = JSON.toJSONString(cart);
        Cookie cookie = new Cookie("cart",BaseUtil.encode(cartjson));
        response.addCookie(cookie);
        model.addAttribute("cs",cart.values());
        model.addAttribute(total(cart));*/
       addCookie(response,cart);

        model.addAttribute("cs",cart.values());
        model.addAttribute("total",total(cart));
        model.addAttribute("discount",discount(cart));
    }
    public  void  addCookie(HttpServletResponse response,Map<String,Commodity>cart){
        String  cartjson =  JSON.toJSONString(cart);
        Cookie cookie  = new Cookie("cart", BaseUtil.encode(cartjson));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public Map<String, Commodity> getCart(String ck){
        String json = BaseUtil.decode(ck);
        Map<String, Commodity> cart = JSON.parseObject(json, new TypeReference<Map<String, Commodity>>(){});
        return cart;
    }
    public Float discount(Map<String,Commodity>cart){
        String  json = HttpUtil.getData("http://localhost:8081/commodity/discount/"+ total(cart));
        Float price = Float.parseFloat(json);
        return price;
    }
}

