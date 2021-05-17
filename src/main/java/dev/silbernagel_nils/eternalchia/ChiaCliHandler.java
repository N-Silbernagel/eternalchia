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

    private final List<Process> plottingProcesses = new ArrayList<>();

    public ChiaCliHandler(ArrayList<String> args, ProcessBuilder processBuilder, Statistics stats) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
        this.stats = stats;
    }

    /**
     * Start n plots in parallel
     * @param n number of plots
     */
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
        long nthPlot = stats.addGeneratedPlot();
        System.out.println("Starting plot n." + stats.getGeneratedPlots());

        long startTime = System.currentTimeMillis();

        chiaArguments.add(0, "chia");
        chiaArguments.add(1, "plots");
        chiaArguments.add(2, "create");

        Process plotProcess = processBuilder.command(chiaArguments.toArray(new String[0]))
                .redirectError(new File("chia_cli_errors_" + index))
                .redirectOutput(new File("chia_cli_output_" + index))
                .start();

        plottingProcesses.add(plotProcess);

        plotProcess.onExit()
                .thenAccept(process -> this.handleCompletedPlotProcess(process, startTime, index, nthPlot));
    }

    /**
     * Handle a completed plotting process
     * @param process the process which completed
     * @param pStartTime the time in milliseconds when the process was started
     * @param index the index of the plot in the number of plots to do in parallel
     * @param n this process was the nth plot done since eternalchia was started
     */
    private void handleCompletedPlotProcess(Process process, long pStartTime, int index, long n) {
        long plotTime = System.currentTimeMillis() - pStartTime;
        stats.addPlottingTime(plotTime);
        plottingProcesses.remove(process);

        if (process.exitValue() != 0) {
            stats.addFailedPlot();
            System.err.println("Generating plot n." + n + " failed.\n");
            stats.removeGeneratedPlot();
        } else {
            System.out.println("Finished plot n." + n + " in " + Statistics.millisToHours(plotTime) + " Hours.\n");
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

    public void killPlottingProcesses() {
        this.plottingProcesses.forEach(Process::destroy);
    }

    public boolean shouldStartNext() {
        return startNext;
    }

    public void setStartNext(boolean startNext) {
        this.startNext = startNext;
    }
}
