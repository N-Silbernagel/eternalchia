package dev.silbernagel_nils.eternalchia;

import java.io.IOException;
import java.util.Scanner;

public class EternalChia {
    private static final String STOP_COMMAND = "s";

    private static String[] commandArgs;
    private static final int timeToNextExecution = 5000;

    private static Process chiaProcess;

    private static Scanner inputScanner;

    private static boolean startNext = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        commandArgs = args;
        inputScanner = new Scanner(System.in);

        listenForInput();

        chiaProcess = executeChiaCommand();
        do {
            readChiaStdOut();
        } while (startNext);
    }

    private static void listenForInput() {
        new Thread(() -> {
            while (startNext){
                String input = inputScanner.nextLine();
                if(input.equalsIgnoreCase(STOP_COMMAND)){
                    startNext = false;
                }
            }
        }).start();
    }

    private static void readChiaStdOut() throws InterruptedException, IOException {
        int errorChar = chiaProcess.getErrorStream().read();
        if (errorChar != -1) {
            System.err.print((char) errorChar);
            return;
        }

        int outputChar = chiaProcess.getInputStream().read();
        if (outputChar == -1) {
            Thread.sleep(timeToNextExecution);
            if(startNext){
                chiaProcess = executeChiaCommand();
            }
            return;
        }

        System.out.print((char) outputChar);
    }

    private static Process executeChiaCommand() throws IOException {
        return new ProcessBuilder("ls", String.join(" ", commandArgs)).start();
    }
}
