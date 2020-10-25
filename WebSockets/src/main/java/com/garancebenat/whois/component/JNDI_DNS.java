package com.garancebenat.whois.component;

import com.sun.jndi.dns.DnsContext;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.naming.*;
import javax.naming.directory.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Slf4j
@UtilityClass
public class JNDI_DNS {

    private final java.util.Properties _p;

    static {
        _p = new java.util.Properties();
        // For Java 9 and more: https://mvnrepository.com/artifact/com.sun.jndi/dns:
        _p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        // Use another DNS server (platform config. otherwise: https://docs.oracle.com/javase/7/docs/technotes/guides/jndi/jndi-dns.html#URL):
//             _p.setProperty(Context.PROVIDER_URL, "dns://194.167.156.13"); // UPPA
//        _p.setProperty(Context.PROVIDER_URL, "dns://8.8.8.8"); // Google
//             _p.setProperty(Context.PROVIDER_URL, "dns://193.146.78.14"); // University of Mondragon
    }

    public DnsRecords suffixes(String url) {
        // Get all Web address suffixes:
        try {
            InetAddress inetAddress = InetAddress.getByName(url);
            InitialContext iC = new InitialContext(_p);
            NameParser np = iC.getNameParser(iC.getNameInNamespace());
            Name universityOfMondragon = np.parse(url);
            DnsContext ctx = (DnsContext) iC.lookup(universityOfMondragon.getPrefix(universityOfMondragon.size() - 1));
            Map<String, Attributes> attr = new HashMap<>();
            attr.put("A", ctx.getAttributes(inetAddress.getHostName(), new String[]{"A"}));
            attr.put("CNAME", ctx.getAttributes(inetAddress.getHostName(), new String[]{"CNAME"}));
            attr.put("MX", ctx.getAttributes(inetAddress.getHostName(), new String[]{"MX"}));
            attr.put("NS", ctx.getAttributes(inetAddress.getHostName(), new String[]{"NS"}));
            attr.put("PTR", ctx.getAttributes(inetAddress.getHostName(), new String[]{"PTR"}));
            attr.put("SOA", ctx.getAttributes(inetAddress.getHostName(), new String[]{"SOA"}));
            attr.put("SRV", ctx.getAttributes(inetAddress.getHostName(), new String[]{"SRV"}));
            attr.put("TXT", ctx.getAttributes(inetAddress.getHostName(), new String[]{"TXT"}));
            Map<String, ArrayList<String>> preparingMap = new HashMap<>();
            for (Map.Entry<String, Attributes> entry : attr.entrySet()) { //For print
                Attributes attribute = entry.getValue();
                NamingEnumeration ne = attribute.getAll();
                while (ne.hasMore()) {
                    BasicAttribute ba = (BasicAttribute) ne.next();
                    System.out.print("\n\tAttribute id. [" + ba.getID() + "]: ");
                    NamingEnumeration nee = ba.getAll();
                    ArrayList<String> test = new ArrayList<>();
                    {
                        while (nee.hasMore()) {
                            test.add(nee.next().toString());
                        }
                    }
                    preparingMap.put(ba.getID(), test);
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

    /*public void test2() { //Alternative
        try {
            Hashtable env = new Hashtable();
            env.put("java.naming.factory.initial",
                    "com.sun.jndi.dns.DnsContextFactory");
            env.put("java.naming.provider.url", "dns://8.8.8.8/");

            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes("fnac.fr",
                    new String[]{"A"});

            for (NamingEnumeration ae = attrs.getAll(); ae.hasMoreElements(); ) {
                Attribute attr = (Attribute) ae.next();
                String attrId = attr.getID();
                System.out.println("Starting analysis");
                for (Enumeration vals = attr.getAll();
                     vals.hasMoreElements();
                     System.out.println(attrId + ": " + vals.nextElement())
                )
                    ;
            }
            ctx.close();
        } catch (Exception e) {
            log.error("Probleme lors de l'interrogation du DNS: " + e);
            e.printStackTrace();
        }
    }*/
}
