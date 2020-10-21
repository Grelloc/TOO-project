package com.garancebenat.whois.Component;

import com.sun.jndi.dns.DnsContext;
import lombok.experimental.UtilityClass;

import javax.naming.*;
import javax.naming.directory.*;

@UtilityClass
public class JNDI_DNS {

    public void codeBarbier() {
        try {
            java.util.Properties _p = new java.util.Properties();
            // For Java 9 and more: https://mvnrepository.com/artifact/com.sun.jndi/dns:
            _p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            // Use another DNS server (platform config. otherwise: https://docs.oracle.com/javase/7/docs/technotes/guides/jndi/jndi-dns.html#URL):
//             _p.setProperty(Context.PROVIDER_URL, "dns://194.167.156.13"); // UPPA
            _p.setProperty(Context.PROVIDER_URL, "dns://8.8.8.8"); // Google
//             _p.setProperty(Context.PROVIDER_URL, "dns://193.146.78.14"); // University of Mondragon
            DirContext dc = new InitialDirContext(_p);
            Attributes attributes = dc.getAttributes("FranckBarbier.com", new String[]{"NS"}); // Stores the name server for a DNS entry...
            if (attributes != null) {
                NamingEnumeration ne = attributes.get("NS").getAll();
                while (ne.hasMoreElements()) {
                    System.out.println("[NS] entry: " + ne.next().toString());
                }
            }
// Get all Web address suffixes:
            InitialContext _ic = new InitialContext(_p);
//            System.out.println("\nInitial context: " + _ic.getNameInNamespace());
            NamingEnumeration ne = _ic.list("");
//            while (ne.hasMore()) {
//                System.out.println("\t" + ((NameClassPair) (ne.next())).getName());
//            }

            NameParser np = _ic.getNameParser(_ic.getNameInNamespace());
            Name university_of_Mondragon = np.parse("www.mondragon.edu");
            System.out.print("\nwww.mondragon.edu has " + university_of_Mondragon.size() + " components:");
            for (int i = 0; i < university_of_Mondragon.size(); i++) {
                System.out.print("\t" + university_of_Mondragon.get(i));
            }
            DnsContext o = (DnsContext) _ic.lookup(university_of_Mondragon.getPrefix(university_of_Mondragon.size() - 1));
            ne = ((o).getAttributes(university_of_Mondragon, null)).getAll();
            while (ne.hasMore()) {
                BasicAttribute ba = (BasicAttribute) ne.next();
                System.out.print("\n\tAttribute id. [" + ba.getID() + "]: ");
                NamingEnumeration nee = ba.getAll();
                while (nee.hasMore()) {
                    System.out.print("\t" + nee.next());
                }
            }
            System.out.println("\n\nClosing...");
            _ic.close();
        } catch (NamingException ne) {
            System.err.println(ne.getMessage() + ": " + ne.getExplanation());
        }
    }
}
