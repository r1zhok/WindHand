package org.windhand.site;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class SiteBlock {

    private final ArrayList<String> siteToBlock = new ArrayList<>();

    public void addSiteToBlock(String url) {
        this.siteToBlock.add(url);
    }

    public void removeSite(String url) {
        this.siteToBlock.removeIf(s -> s.equals(url));

    }

    public void blockingSite() {
        String OS = System.getProperty("os.name").toLowerCase();

        // Use OS name to find correct location of hosts file
        String hostsFile = "";
        if ((OS.indexOf("win") >= 0)) {
            // Doesn't work before Windows 2000
            hostsFile = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        } else if ((OS.indexOf("mac") >= 0)) {
            // Doesn't work before OS X 10.2
            hostsFile = "etc/hosts";
        } else if ((OS.indexOf("nux") >= 0)) {
            hostsFile = "/etc/hosts";
        } else {
            // Handle error when platform is not Windows, Mac, or Linux
            System.err.println("Sorry, but your OS doesn't support blocking.");
            System.exit(0);
        }

        for (String url : this.siteToBlock) {
            try {
                Files.write(Paths.get(hostsFile),
                        ("127.0.0.1 " + url).getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
