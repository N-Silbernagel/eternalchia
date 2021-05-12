package dev.silbernagel_nils.eternalchia;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ChiaCliHandler {
    private final List<String> chiaArguments;
    private final ProcessBuilder processBuilder;
    private final Statistics stats;

    private boolean startNext = true;

    public ChiaCliHandler(List<String> args, ProcessBuilder processBuilder, Statistics stats) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
        this.stats = stats;
    }

    public void plot(int n) throws IOException {
        for (int i=1; i<=n; i++) {
            this.plot();
        }
    }

    private void plot() throws IOException {
        System.out.println("Starting plot n." + (stats.getGeneratedPlots() + 1));

        long startTime = System.currentTimeMillis();

        CompletableFuture<Process> processCompletion = processBuilder.command("ls", String.join(" ", this.chiaArguments))
                .inheritIO()
                .start()
                .onExit();

        processCompletion.thenAccept(process -> this.handleCompletedPlotProcess(process, startTime));
    }

    private void handleCompletedPlotProcess(Process process, long pStartTime) {
        long plotTime = System.currentTimeMillis() - pStartTime;
        stats.addPlottingTime(plotTime);

        if (process.exitValue() != 0) {
            stats.addFailedPlot();
            System.err.println("Generating plot n." + (stats.getGeneratedPlots() + 1) + " failed.\n");
        } else {
            stats.addGeneratedPlot();
            System.out.println("Finished plot n." + stats.getGeneratedPlots() + " in " + Statistics.millisToHours(plotTime) + " Hours.\n");
        }

        if (shouldStartNext()){
            try {
                plot();
            } catch (IOException e) {
                // TODO do something when this exception occurs
            }
        }
    }

    public boolean shouldStartNext() {
        return startNext;
    }

    public void setStartNext(boolean startNext) {
        this.startNext = startNext;
    }
}
