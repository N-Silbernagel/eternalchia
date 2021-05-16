package dev.silbernagel_nils.eternalchia;

import java.util.Scanner;

public class InputHandler {
    public static final String STOP_COMMAND = "s";
    public static final String INFO_COMMAND = "i";

    private final Statistics stats;
    private final ChiaCliHandler chiaCliHandler;

    public InputHandler(Statistics stats, ChiaCliHandler chiaCliHandler) {
        this.stats = stats;
        this.chiaCliHandler = chiaCliHandler;
    }

    public void backgroundListener() {
        new Thread(this::listen).start();
    }

    public void listen() {
        Scanner inputScanner = new Scanner(System.in);

        while (chiaCliHandler.shouldStartNext() && inputScanner.hasNextLine()){
            this.handleNextLine(inputScanner.nextLine());
        }
    }

    private void handleNextLine(String nextLine) {
        if (nextLine.equalsIgnoreCase(STOP_COMMAND)){
            chiaCliHandler.setStartNext(false);
            return;
        }

        if (nextLine.equalsIgnoreCase(INFO_COMMAND)) {
            stats.showInfo();
        }
    }
}
