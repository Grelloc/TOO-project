package com.garancebenat.whois.Controller;

import com.garancebenat.whois.Component.JNDI_DNS;
import com.garancebenat.whois.Component.PasTropLa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class WebPageController {
    @PostMapping(value = "/FranckBarbier/WebSockets_illustration", consumes = "application/json", produces = "application/json")
    public String test3(@RequestBody PasTropLa tjsPasLa) {
        return "J'ai trouve tjs pas la " + tjsPasLa.getName() + " " +
                tjsPasLa.getAge();
    }//Attend qu'on lui fasse une requete puis repond avec ce qui est return
    //Donc faut mettre dans ca ce qui va etre parse

    @GetMapping("/JavaSaid/Information")       //Return PasTropLa en Json
    public PasTropLa information() {
        return new PasTropLa("Benat", 20);
    }

    /*@GetMapping("/JavaSaid/Extensions")
    public String extensions(){
        new JNDI_DNS();
    }
*/
    @PostMapping (value = "/JS_Said/domainUrl", consumes = "application/json", produces = "application/json")
    public String getUrl(@RequestBody String url){
        Pattern regxp = Pattern.compile("(https://|http://)?(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)");
        if(regxp.matcher(url).find()) {
            return "Got it" + " " + url;
        }
        else {
            return "Sorry, something went wrong" + url;
        }
    }
}
