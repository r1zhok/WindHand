package org.windhand.application;

import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationBlocker {

    private final ArrayList<String> blockedApp = new ArrayList<>();
    private final ArrayList<Integer> timeToBlock = new ArrayList<>();

    public void addAppToBlock(String application, Integer time) {
        this.blockedApp.add(application);
        this.timeToBlock.add(time);
    }

    /**
     *
     * @// TODO: 01.11.2023 треба доробити видалення для час
     * */

    public void removeApp(String application) {
        for (int i = 0; i < this.blockedApp.size(); i++) {
            if (this.blockedApp.get(i).equals(application)) {
                this.blockedApp.remove(i);
                this.timeToBlock.remove(i);
            }
        }
    }

    public void startToBlock() {
        String OS = System.getProperty("os.name").toLowerCase();

        for (int i = 0; i < blockedApp.size(); i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                long start = System.currentTimeMillis();
                long end = 0;
                while (!(end - start >= timeToBlock.get(finalI))) {
                    ProcessBuilder processBuilder;
                    if (OS.contains("win")) {
                        processBuilder = new ProcessBuilder("taskkill", "/F", "/IM",
                                    blockedApp.get(finalI) + ".exe");
                    } else if (OS.contains("mac") || OS.contains("nux")) {
                        processBuilder = new ProcessBuilder("killall", blockedApp.get(finalI));
                    } else {
                        System.err.println("Sorry, but your OS doesn't support blocking.");
                        System.exit(0);
                        return;
                    }

                    try {
                        processBuilder.start();
                    } catch (IOException e) {
                        Logger.debug(e, "Problem is IOException!");
                    }

                    end = System.currentTimeMillis();
                }
            });

            thread.start();
        }
    }
}
