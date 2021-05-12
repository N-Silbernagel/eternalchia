package dev.silbernagel_nils.eternalchia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EternalChia {
    private final ChiaCliHandler chiaCliHandler;
    private final InputHandler inputHandler;
    private final Statistics stats;

    public static void main(String[] args) throws IOException {
        List<String> argsList = Arrays.asList(args);

        List<String> appArgs;
        List<String> chiaArgs;

        if(!argsList.contains("--")){
            appArgs = argsList;
            chiaArgs = new ArrayList<>(0);
        } else {
            appArgs = argsList.subList(0, argsList.indexOf("--"));
            chiaArgs = argsList.subList(argsList.indexOf("--"), argsList.size());
        }
        new EternalChia(appArgs, chiaArgs).run();
    }

    public EternalChia(List<String> appArgs, List<String> chiaArgs) {
        stats = new Statistics();
        chiaCliHandler = new ChiaCliHandler(chiaArgs, new ProcessBuilder(), stats);
        inputHandler = new InputHandler(stats, chiaCliHandler);
    }

    public void run() throws IOException {
        inputHandler.listenParallel();

        chiaCliHandler.plot(1);
    }
}
