package com.garancebenat.whois.Controller;

import com.garancebenat.whois.Component.PasTropLa;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebPageController {
    @GetMapping("/FranckBarbier/WebSockets_illustration")
    public String test(){
        return "test";
    }

    @PostMapping(value = "/FranckBarbier/WebSockets_illustration", consumes = "application/json", produces = "application/json")
    public String test3(@RequestBody PasTropLa tjsPasLa) {
        return "J'ai trouve tjs pas la" + tjsPasLa.getName() +
                tjsPasLa.getAge();
    }

    @GetMapping("/FranckBarbier/WebSockets_illustration/test2")
    public PasTropLa test2() {
        return new PasTropLa("Benat", 20);
    }


}
