package com.garancebenat.whois.controller;

import com.garancebenat.whois.component.DnsRecords;
import com.garancebenat.whois.component.JNDI_DNS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Slf4j
@RestController
public class WebPageController {

    /*@GetMapping("/JavaSaid/Extensions")
    public DnsRecords extensions(){
        return JNDI_DNS.suffixes();
    }*/

    @GetMapping(value = "/JS_Said/domainUrl")
    public DnsRecords getUrl(@RequestParam String url){
        final Pattern regxp = Pattern.compile("(https://|http://)?(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)");
        if(regxp.matcher(url).find()) {
            try {
                return JNDI_DNS.suffixes(url);
            }
            catch(NullPointerException ne){
                throw new IllegalStateException(url +" "+ ne);
            }
        }
        else {
            throw new IllegalStateException("Bad format of url : " + url);
        }
    }
}
