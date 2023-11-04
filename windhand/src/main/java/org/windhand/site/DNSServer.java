package org.windhand.site;

import org.xbill.DNS.*;
import org.xbill.DNS.Record;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static org.xbill.DNS.Section.ANSWER;

public class DNSServer extends Thread {

    private final ArrayList<String> blockingSites = new ArrayList<>();

    public void addSiteToBlock(String site) {
        this.blockingSites.add(site);
    }

    public void removeSite(String site) {
        if (this.blockingSites.contains(site)) {
            this.blockingSites.removeIf(s -> s.equals(site));
        }
    }

    @Override
    public void run() {
        try {
            SimpleResolver resolver = new SimpleResolver("8.8.8.8");

            SimpleResolver blockingSites = new SimpleResolver();
            blockingSites.setAddress(InetAddress.getByName("127.0.0.1"));

            ExtendedResolver extended = new ExtendedResolver(new Resolver[]{resolver, blockingSites});

            try {
                Name[] blockedDomains = new Name[this.blockingSites.size()];

                for (int i = 0; i < blockedDomains.length; i++) {
                    blockedDomains[i] = Name.fromString(this.blockingSites.get(i), Name.root);
                }

                for (Name domain : blockedDomains) {
                    Record queryRecord = Record.newRecord(domain, Type.A, DClass.IN);
                    Message query = Message.newQuery(queryRecord);
                    Message response = extended.send(query);

                    if (response.getSectionArray(ANSWER).length == 0) {
                        System.out.println("Blocked or redirected request for " + domain);
                    } else {
                        Record answerRecord = response.getSectionArray(Section.ANSWER)[0];
                        System.out.println("Received response for " + domain + ": " + answerRecord);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}