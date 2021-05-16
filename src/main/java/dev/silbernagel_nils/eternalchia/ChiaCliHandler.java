package dev.silbernagel_nils.eternalchia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChiaCliHandler {
    private final List<String> chiaArguments;
    private final ProcessBuilder processBuilder;
    private final Statistics stats;

    private boolean startNext = true;

    public ChiaCliHandler(ArrayList<String> args, ProcessBuilder processBuilder, Statistics stats) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
        this.stats = stats;
    }

    public void plotParallel(int n) throws IOException {
        for (int i=1; i<=n; i++) {
            this.plot(i);
        }
    }

    /**
     * start a plot
     * @param index index of the plot in the number of plots to do in parallel
     */
    private void plot(int index) throws IOException {
        stats.addGeneratedPlot();
        System.out.println("Starting plot n." + stats.getGeneratedPlots());

        long startTime = System.currentTimeMillis();

        chiaArguments.add(0, "chia");
        chiaArguments.add(1, "plots");
        chiaArguments.add(2, "create");

        processBuilder.command(chiaArguments.toArray(new String[0]))
                .redirectError(new File("chia_cli_errors"))
                .redirectOutput(new File("chia_cli_output_" + index))
                .start()
                .onExit()
                .thenAccept(process -> this.handleCompletedPlotProcess(process, startTime, index));
    }

    private void handleCompletedPlotProcess(Process process, long pStartTime, int index) {
        long plotTime = System.currentTimeMillis() - pStartTime;
        stats.addPlottingTime(plotTime);

        if (process.exitValue() != 0) {
            stats.addFailedPlot();
            System.err.println("Generating plot n." + stats.getGeneratedPlots() + " failed.\n");
            stats.removeGeneratedPlot();
        } else {
            System.out.println("Finished plot n." + stats.getGeneratedPlots() + " in " + Statistics.millisToHours(plotTime) + " Hours.\n");
        }

        if (shouldStartNext()){
            try {
                plot(index);
            } catch (IOException e) {
                // TODO: do something clever when IOException is thrown
                e.printStackTrace();
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
