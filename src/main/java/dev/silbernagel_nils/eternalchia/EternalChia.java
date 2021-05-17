package dev.silbernagel_nils.eternalchia;

import com.beust.jcommander.JCommander;

import java.io.IOException;

public class EternalChia {
    private final ChiaCliHandler chiaCliHandler;
    private final InputHandler inputHandler;
    private final Statistics stats = new Statistics();
    private final AppArgs appArgs = new AppArgs();

    public static void main(String[] args) throws IOException {
        new EternalChia(args).run();
    }

    public EternalChia(String[] args) {
        JCommander.newBuilder()
                .addObject(appArgs)
                .build()
                .parse(args);

        chiaCliHandler = new ChiaCliHandler(appArgs, new ProcessBuilder(), stats);
        inputHandler = new InputHandler(stats, chiaCliHandler);
    }

    public void run() throws IOException {
        Runtime.getRuntime().addShutdownHook(this.onShutdown());

        inputHandler.backgroundListener();

        chiaCliHandler.plotParallel(appArgs.getParallelPlots());
    }

    public Thread onShutdown() {
        return new Thread(chiaCliHandler::killPlottingProcesses);
    }
}
