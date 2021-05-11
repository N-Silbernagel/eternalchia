package dev.silbernagel_nils.eternalchia;

import java.util.Scanner;

public class InputHandler {
    private static final String STOP_COMMAND = "s";

    private boolean stopped = false;

    public boolean receivedStop() {
        return stopped;
    }

    public void listenParallel() {
        new Thread(this::listen).start();
    }

    private void listen() {
        Scanner inputScanner = new Scanner(System.in);

        while (!stopped){
            String input = inputScanner.nextLine();
            if(input.equalsIgnoreCase(STOP_COMMAND)){
                stopped = true;
            }
        }
    }
}
