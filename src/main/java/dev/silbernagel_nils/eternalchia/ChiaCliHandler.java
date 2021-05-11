package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class ChiaCliHandler {
    private final String[] chiaArguments;
    private final ProcessBuilder processBuilder;

    private long plots = 0;
    private long accPlotTime = 0;

    public ChiaCliHandler(String[] args, ProcessBuilder processBuilder) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
    }

    public void plot() throws IOException, InterruptedException {
        System.out.println("Starting plot n." + (plots + 1));

        long startTime = System.currentTimeMillis();

        processBuilder.command("ls", String.join(" ", this.chiaArguments))
                .inheritIO()
                .start()
                .waitFor();

        accPlotTime += System.currentTimeMillis() - startTime;
        plots++;

        System.out.println("Finished plot n." + plots + " in " + millisToHours(accPlotTime) + " Hours.\n");
    }

    public long getPlots() {
        return plots;
    }

    /**
     * Get accumulated plot time in hours
     */
    public double getAccPlotTime() {
        return millisToHours(accPlotTime);
    }

    private static double millisToHours(long millis) {
        return ((double)(millis / 600)) / 100;
    }
}
