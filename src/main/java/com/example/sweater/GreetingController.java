package com.example.sweater;

import com.example.sweater.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repos.ItemsRepo;

@Controller
public class GreetingController {
    @Autowired
    private ItemsRepo itemsRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


   /* @GetMapping("/all")
    public @ResponseBody
    Iterable<Items> getAllUsers() {
        return itemsRepo.findAll();
    }*/
}