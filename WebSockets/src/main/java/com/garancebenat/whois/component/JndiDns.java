package com.garancebenat.whois.component;

import com.sun.jndi.dns.DnsContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.naming.*;
import javax.naming.directory.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Slf4j
@AllArgsConstructor
public class JndiDns {

    private final java.util.Properties properties;
    private final List<String> records;

    public DnsRecords suffixes(String url) {
        // Get all Web address records:
        try {
            InetAddress inetAddress = InetAddress.getByName(url);
            InitialContext iC = new InitialContext(properties);
            NameParser np = iC.getNameParser(iC.getNameInNamespace());
            Name nameParsed = np.parse(url);
            DnsContext ctx = (DnsContext) iC.lookup(nameParsed.getPrefix(nameParsed.size() - 1));
            Map<String, List<String>> preparingMap = new HashMap<>();
            for (String record : records) {
                //Get all records one by one to avoid getting kicked from DNS during operation and prepare it to json
                NamingEnumeration ne = ctx.getAttributes(inetAddress.getHostName(), new String[]{record}).getAll();
                while (ne.hasMore()) {
                    BasicAttribute ba = (BasicAttribute) ne.next();
                    NamingEnumeration nee = ba.getAll();
                    List<String> kids = new ArrayList<>();
                    while (nee.hasMore()) {
                            kids.add(nee.next().toString());
                    }
                    preparingMap.put(ba.getID(), kids);
                }
            }
            iC.close();
            return new DnsRecords(preparingMap);
        } catch (NamingException ne) {
            log.error(ne.getMessage() + ": " + ne.getExplanation() + ": " + ne.getCause());
        } catch (UnknownHostException uhe) {
            log.error(uhe.getMessage() + ": " + uhe.getCause());
        }
        throw new NullPointerException("sorry, something went wrong");
    }
}
