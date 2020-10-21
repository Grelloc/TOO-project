package com.garancebenat.whois.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WhoIsController {
    //    @GetMapping(value = "/WhoIs") //Alternative
//    public ModelAndView WebPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index.html");
//        return modelAndView;
//    }

    @GetMapping(value = "/WhoIs")
    public String WebPage() {
        return "index.html";
    }
}
