package com.garancebenat.whois;

import com.garancebenat.whois.Component.JNDI_DNS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.URI;

import static java.awt.Desktop.getDesktop;

@Slf4j
@SpringBootApplication
public class WhoIs {
    public static void main(String[] args) throws IOException {
        try {
            getDesktop()
                    .browse(URI.create("http://localhost:1963/WhoIs"));
        } catch (IOException e) {
            log.error("Gars relou{}", "Bonjour","Comment va?", e); //Permet de mettre le bonjour dans les {}
        }
        final ConfigurableApplicationContext run = SpringApplication.run(WhoIs.class, args);
//        new JNDI_DNS();
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Please press a key to stop the server...");
        reader.readLine();
        SpringApplication.exit(run);
    }
}