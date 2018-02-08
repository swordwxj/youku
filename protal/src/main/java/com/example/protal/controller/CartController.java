package com.example.protal.controller;

import com.example.protal.service.CartService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cs;

        @RequestMapping("add/{sku}")
        public String  addCart(@PathVariable String sku, Model model, HttpServletResponse response, @CookieValue(value = "cart",required = false)String cart){
            cs.addCart(sku,response,model,cart);
            return  "cart";
        }

        @RequestMapping("update/{sku}/{num}")
        @ResponseBody
        public  String update(@PathVariable String sku,@PathVariable int num,@CookieValue(value="cart",required = false) String cart,HttpServletResponse response ){
            String s = ""+cs.update(sku,num,cart,response);
            System.out.println(s);
            return s;
        }
        @RequestMapping("lookcart")
        public String looklookCart(@CookieValue(value="cart",required = false)String cart,Model model){
                cs.lookCart(cart,model);
                return  "cart";
        }
        @RequestMapping("delete/{sku}")
        public String deleteCommodity(@PathVariable String sku,@CookieValue(value="cart",required = false)String cart,Model model,HttpServletResponse response){
            cs.deletCommodityBySkuFromCommodity(sku,cart,model,response);
            return "cart";
        }




}
