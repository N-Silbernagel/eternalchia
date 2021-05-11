package dev.silbernagel_nils.eternalchia;

import java.util.Scanner;

public class InputHandler {
    public static final String STOP_COMMAND = "s";
    public static final String INFO_COMMAND = "i";

    private boolean stopped = false;

    private final Statistics stats;

    public InputHandler(Statistics stats) {
        this.stats = stats;
    }

    public boolean receivedStop() {
        return stopped;
    }

    public void listenParallel() {
        new Thread(this::listen).start();
    }

    public void listen() {
        Scanner inputScanner = new Scanner(System.in);

        while (!receivedStop() && inputScanner.hasNextLine()){
            this.handleNextLine(inputScanner.nextLine());
        }
    }

    private void handleNextLine(String nextLine) {
        if (nextLine.equalsIgnoreCase(STOP_COMMAND)){
            stopped = true;
            return;
        }

        if (nextLine.equalsIgnoreCase(INFO_COMMAND)) {
            stats.showInfo();
        }
    }
}
