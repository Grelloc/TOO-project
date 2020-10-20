package com.garancebenat.whois.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WhoIsController {
//    @RequestMapping(value = "/WhoIs", method = RequestMethod.GET)
//    public String WebPage(){
//        return "html/index/templates.html";
//    }

    //    @RequestMapping(value = "/WhoIs", method = RequestMethod.GET)
//    public ModelAndView WebPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index.html");
//        return modelAndView;
//    }
//
    @RequestMapping(value = "/WhoIs", method = RequestMethod.GET)
    public String WebPage() {
        return "index.html";
    }
}
