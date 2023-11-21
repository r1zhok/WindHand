package org.windhand.site;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SiteBlock {

    private final ArrayList<String> siteToBlock = new ArrayList<>();
    private static final String HOST_WIN = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    private static final String HOST_LIN = "etc/hosts";


    public void addSiteToBlock(String url) {
        this.siteToBlock.add(url);
    }

    public void removeSite(String url) {
        this.siteToBlock.removeIf(s -> s.equals(url));
        String OS = System.getProperty("os.name").toLowerCase();
        List<String> lines;
        String path;

        try {
            if (OS.contains("win")) {
                lines = Files.readAllLines(Path.of(HOST_WIN));
                path = HOST_WIN;
            } else {
                lines = Files.readAllLines(Path.of(HOST_LIN));
                path = HOST_LIN;
            }

            lines = lines.stream()
                    .filter(line -> !line.contains(url))
                    .collect(Collectors.toList());

            Files.write(Paths.get(path), lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void blockingSite() {
        String OS = System.getProperty("os.name").toLowerCase();

        String hostsFile = "";
        if ((OS.indexOf("win") >= 0)) {
            hostsFile = HOST_WIN;
        } else if ((OS.indexOf("mac") >= 0)) {
            hostsFile = HOST_LIN;
        } else if ((OS.indexOf("nux") >= 0)) {
            hostsFile = HOST_LIN;
        } else {
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
