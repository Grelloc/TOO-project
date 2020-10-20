package com.garancebenat.whois;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WhoIs {
    public static void main(String[] args) {
//        try {
//            getDesktop()
//                    .browse(getDefault().getPath("src" + separatorChar + "main" + separatorChar + "resources" + separatorChar + "templates.html" + separatorChar + "index.html").toUri());
//        } catch (IOException e) {
//            log.error("PB {}", "Bonjour", e); //Permet de mettre le bonjour dans les {}
//        }
        SpringApplication.run(WhoIs.class, args);
    }
}
