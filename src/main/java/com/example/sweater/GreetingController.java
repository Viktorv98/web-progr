package com.example.sweater;

import com.example.sweater.models.Korzina;
import com.example.sweater.models.Items;
import com.example.sweater.repos.BasketsRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BasketsRepo basketsRepo;
    Iterable<Items> items;
    Cookie cookie;

    @GetMapping("/")
    public String greeting (Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, @CookieValue (value = "myCity", defaultValue = "null") String myCookie, HttpSession sess){
        items = itemsRepo.findAll();
        model.put( "items", items);
        cookie = new Cookie("myCity", myCookie);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        model.put("city",myCookie);

        int countCar = 0;
        List<Items> list = new ArrayList<>();
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

    @PostMapping ("/buy")
    public String buying (Map<String, Object> model, @RequestParam int car_id, @RequestParam String car_name, @RequestParam int car_cost, @RequestParam String car_count, HttpServletRequest request, HttpSession sess){
        int countCar = 0;
        List<Items> lists = new ArrayList<>();
        lists = (List<Items>)request.getSession().getAttribute("cars");
        countCar = (Integer)request.getSession().getAttribute("countCars");
        lists.add(new Items(car_id,car_name,car_cost, car_count));
        countCar++;
        sess.setAttribute("countCars", countCar);
        sess.setAttribute("cars", lists);
        model.put("countCar", countCar);
        model.put("cars", lists);
        return Integer.toString(countCar);
    }



    @GetMapping("/basket")
    public String bask(Map<String, Object> model, HttpServletRequest request){
        HttpSession session = request.getSession();
        int countCar = 0;
        int price = 0;
        List<Items> list = new ArrayList<>();
        countCar = (Integer)request.getSession().getAttribute("countCars");
        list = (List<Items>)request.getSession().getAttribute("cars");
        for (int i = 0; i < list.size(); i++){
            price += list.get(i).getCost()*Integer.parseInt(list.get(i).getDesk());
        }
        model.put("countCar", countCar);
        model.put("cars", list);
        model.put("price", price);
        return "basket";
    }

    @PostMapping("/basket")
    public String basket(Map<String, Object> model, HttpServletRequest request, @RequestParam int car_id, @RequestParam String car_count){
        HttpSession session = request.getSession();
        int countCar = 0;
        int price = 0;
        List<Items> list = new ArrayList<>();
        countCar = (Integer)request.getSession().getAttribute("countCars");
        list = (List<Items>)request.getSession().getAttribute("cars");
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getId() == car_id){
            list.get(i).setDesk(car_count);
            price += list.get(i).getCost()*Integer.parseInt(list.get(i).getDesk());
            }
        }
        session.setAttribute("countCars", countCar);
        session.setAttribute("cars", list);
        model.put("countCar", countCar);
        model.put("cars", list);
        model.put("price", price);
        return Integer.toString(countCar);
    }

    @PostMapping("/basket/delete")
    public String del (Map<String, Object> model, HttpServletRequest request, @RequestParam int car_id){
        HttpSession session = request.getSession();
        int countCar = 0;
        int price = 0;
        List<Items> list = new ArrayList<>();
        countCar = (Integer)request.getSession().getAttribute("countCars");
        list = (List<Items>)request.getSession().getAttribute("cars");
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getId() == car_id){
                list.remove(i);
                countCar -=1;
            }
        }
        for (int i = 0; i < list.size(); i++){
            price += list.get(i).getCost()*Integer.parseInt(list.get(i).getDesk());
        }
        session.setAttribute("countCars", countCar);
        session.setAttribute("cars", list);
        model.put("countCar", countCar);
        model.put("cars", list);
        model.put("price", price);

        return Integer.toString(countCar);
    }

    @PostMapping ("/buying")
    @ResponseBody
    public String buys (Map<String, Object> model, HttpServletRequest request){
        HttpSession session = request.getSession();
        int countCar = 0;
        List<Items> list = new ArrayList<>();
        countCar = (Integer)request.getSession().getAttribute("countCars");
        list = (List<Items>)request.getSession().getAttribute("cars");

        for (int i = 0; i < list.size(); i++){
            Korzina korzina = new Korzina();
          //  korzina.setId(i+1);
            korzina.setName(list.get(i).getName());
            korzina.setCost(list.get(i).getCost());
            korzina.setCount(Integer.parseInt(list.get(i).getDesk()));
            korzina.setCity(cookie.getValue());
         //
            //   Korzina korzina = new Korzina(list.get(i).getName(),list.get(i).getCost(),Integer.parseInt(list.get(i).getDesk()),"perm");
            basketsRepo.save(korzina);
        }
        return "{}";
    }
}