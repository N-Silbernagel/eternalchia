package dev.silbernagel_nils.eternalchia;

import java.util.List;

public class AppArgs {
    private final int parallelPlots;

    private AppArgs(int parallelPlots) {
        this.parallelPlots = parallelPlots;
    }

    public int getParallelPlots() {
        return parallelPlots;
    }

    public static AppArgs fromArgList(List<String> args) {
        int parallelPlots;
        if(!args.contains("-n")) {
            parallelPlots = 1;
        } else {
            parallelPlots = Integer.parseInt(args.get(args.indexOf("-n") + 1));
        }
        return new AppArgs(parallelPlots);
    }
}
