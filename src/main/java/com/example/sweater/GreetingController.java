package com.example.sweater;

import com.example.sweater.models.Baskets;
import com.example.sweater.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.sweater.repos.ItemsRepo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ItemsRepo itemsRepo;
    Iterable<Items> items;

    @GetMapping("/")
    public String greeting (Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, @CookieValue (value = "myCity", defaultValue = "null") String myCookie, HttpSession sess){
        items = itemsRepo.findAll();
        model.put( "items", items);
        Cookie cookie = new Cookie("myCity", myCookie);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        model.put("city",myCookie);

        int countCar = 0;
        List<Integer> list = new ArrayList<>();
        Object countCars = request.getSession().getAttribute("countCars");
        if (countCars == null){
            sess.setAttribute("countCars", countCar);
            sess.setAttribute("cars", list);
        }
        else{
            countCar = (int)countCars;
        }
        model.put("countCar", countCar);
        return "greeting";
    }

    @GetMapping("/buy/{car_id}")
    public String buy (Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, @PathVariable("car_id") int id,  HttpSession sess){

        int countCar = 0;
        List<Integer> list = new ArrayList<>();
        countCar = (Integer)request.getSession().getAttribute("countCars");
        list = (List<Integer>)request.getSession().getAttribute("cars");

        list.add(id);
        countCar++;
        sess.setAttribute("countCars", countCar);
        sess.setAttribute("cars", list);
        model.put("countCar", countCar);
        return Integer.toString(countCar);
    }
   /* @GetMapping("/all")
    public @ResponseBody
    Iterable<Items> getAllUsers() {
        return itemsRepo.findAll();
    }*/
}