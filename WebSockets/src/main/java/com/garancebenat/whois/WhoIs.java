package com.garancebenat.whois;

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
    public static void main(String[] args) {
        try {
            getDesktop()
                    .browse(URI.create("http://localhost:1963/WhoIs")); //Browser can be opened too early, please refresh page after server fully charged
        } catch (IOException e) {
            log.error(String.valueOf(e.getCause()));
        }
        final ConfigurableApplicationContext run = SpringApplication.run(WhoIs.class, args);
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            log.info("\nPlease press a key to stop the server...");
            reader.readLine();
        } catch (IOException e) {
            log.error("Couldn't read what was sent");
        }
        SpringApplication.exit(run);
    }
}