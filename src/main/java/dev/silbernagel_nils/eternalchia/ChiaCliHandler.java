package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class ChiaCliHandler {
    private final String[] chiaArguments;
    private final ProcessBuilder processBuilder;
    private final Statistics stats;

    public ChiaCliHandler(String[] args, ProcessBuilder processBuilder, Statistics stats) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
        this.stats = stats;
    }

    public void plot() throws IOException, InterruptedException {
        System.out.println("Starting plot n." + (stats.getGeneratedPlots() + 1));

        long startTime = System.currentTimeMillis();

        processBuilder.command("ls", String.join(" ", this.chiaArguments))
                .inheritIO()
                .start()
                .waitFor();

        long plotTime = System.currentTimeMillis() - startTime;
        stats.addPlottingTime(plotTime);
        stats.addGeneratedPlot();

        System.out.println("Finished plot n." + stats.getGeneratedPlots() + " in " + Statistics.millisToHours(plotTime) + " Hours.\n");
    }
}
