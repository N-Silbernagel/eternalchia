package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class ChiaCliHandler {
    private static final int timeBetweenFailedPlots = 5000;

    private final String[] chiaArguments;
    private final ProcessBuilder processBuilder;
    private final Statistics stats;

    private boolean startNext = true;

    public ChiaCliHandler(String[] args, ProcessBuilder processBuilder, Statistics stats) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
        this.stats = stats;
    }

    public void plot() throws IOException, InterruptedException {
        while (shouldStartNext()) {
            System.out.println("Starting plot n." + (stats.getGeneratedPlots() + 1));

            long startTime = System.currentTimeMillis();

            int existCode = processBuilder.command("ls", String.join(" ", this.chiaArguments))
                    .inheritIO()
                    .start()
                    .waitFor();

            long plotTime = System.currentTimeMillis() - startTime;
            stats.addPlottingTime(plotTime);

            if (existCode != 0) {
                stats.addFailedPlot();
                System.err.println("Generating plot n." + (stats.getGeneratedPlots() + 1) + " failed.");
                Thread.sleep(timeBetweenFailedPlots);
                continue;
            }

            stats.addGeneratedPlot();

            System.out.println("Finished plot n." + stats.getGeneratedPlots() + " in " + Statistics.millisToHours(plotTime) + " Hours.\n");
        }
    }

    public boolean shouldStartNext() {
        return startNext;
    }

    public void setStartNext(boolean startNext) {
        this.startNext = startNext;
    }
}
