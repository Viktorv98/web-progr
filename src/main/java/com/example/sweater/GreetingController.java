package com.example.sweater;

import com.example.sweater.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.sweater.repos.ItemsRepo;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ItemsRepo itemsRepo;
    Iterable<Items> items;

    @GetMapping("/")
    public String greeting (Map<String, Object> model, HttpServletResponse response, @CookieValue (value = "myCity", defaultValue = "null") String myCookie){
        items = itemsRepo.findAll();
        model.put( "items", items);
        Cookie cookie = new Cookie("myCity", myCookie);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        model.put("city",myCookie);

        return "greeting";
    }

   /* @GetMapping("/all")
    public @ResponseBody
    Iterable<Items> getAllUsers() {
        return itemsRepo.findAll();
    }*/
}