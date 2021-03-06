package com.garancebenat.whois.controller;

import com.garancebenat.whois.component.DnsRecords;
import com.garancebenat.whois.component.JndiDns;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

@Slf4j
@RestController
public class WebPageController {

    @GetMapping(value = "/WhoIs/domainUrl")
    public DnsRecords getUrl(@RequestParam String url){
        final Pattern regxp = Pattern.compile("(https://|http://)?(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)");
        if(regxp.matcher(url).find()) {
            try {
                url = url.replace("www.", "");
                return new JndiDns(initP(), Arrays.asList("A", "CNAME", "AAAA", "MX", "NS", "PTR", "SOA", "SRV", "TXT")).suffixes(url);
            }
            catch(NullPointerException ne){
                throw new IllegalStateException(url +" "+ ne);
            }
        }
        else {
            throw new IllegalStateException("Bad format of url : " + url);
        }
    }
    private Properties initP(){
        Properties p = new java.util.Properties();
        p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        return p;
    }
}
