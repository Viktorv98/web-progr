package com.example.sweater;

import com.example.sweater.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.sweater.repos.ItemsRepo;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ItemsRepo itemsRepo;
    Iterable<Items> items;

    @GetMapping("/greeting")
    public String greeting (Map<String, Object> model){
        items = itemsRepo.findAll();
        model.put( "items", items);
        return "greeting";
    }

   /* @GetMapping("/all")
    public @ResponseBody
    Iterable<Items> getAllUsers() {
        return itemsRepo.findAll();
    }*/
}