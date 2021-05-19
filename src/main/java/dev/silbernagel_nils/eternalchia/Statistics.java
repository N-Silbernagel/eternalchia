package dev.silbernagel_nils.eternalchia;

import java.util.concurrent.TimeUnit;

public class Statistics {
    private long generatedPlots = 0;
    private long plotTime = 0;
    private long failPlots = 0;

    public long getGeneratedPlots() {
        return generatedPlots;
    }

    public void addGeneratedPlot() {
        this.generatedPlots++;
    }

    public long getFailedPlots() {
        return failPlots;
    }

    public void addFailedPlot() {
        this.failPlots++;
    }

    public double getPlotTime() {
        return millisToHours(plotTime);
    }

    public void addPlottingTime(long time) {
        this.plotTime += time;
    }

    public static double millisToHours(long millis) {
        return TimeUnit.MILLISECONDS.toHours(millis);
    }

    public void showInfo() {
        System.out.println("\nGenerated " + getGeneratedPlots() + " plots.");
        System.out.println(getFailedPlots() + " plots failed.");
        System.out.println("Total plotting time: " + getPlotTime() + " Hours.\n");
    }
}
