package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class EternalChia {
    private static final int timeBetweenPlots = 5000;

    private final ChiaCliHandler chiaCliHandler;
    private final InputHandler inputHandler;

    public static void main(String[] args) throws IOException, InterruptedException {
        new EternalChia(args).run();
    }

    public EternalChia(String[] args) {
        inputHandler = new InputHandler();
        chiaCliHandler = new ChiaCliHandler(args);
    }

    public void run() throws IOException, InterruptedException {
        inputHandler.listenParallel();

        while (!inputHandler.receivedStop()) {
            chiaCliHandler.plot();
            Thread.sleep(timeBetweenPlots);
        }
    }
}
