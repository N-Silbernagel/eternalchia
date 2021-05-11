package dev.silbernagel_nils.eternalchia;

public class Statistics {
    private long generatedPlots = 0;

    private long accumulatedTimeToPlot = 0;

    public long getGeneratedPlots() {
        return generatedPlots;
    }

    public void addGeneratedPlot() {
        this.generatedPlots++;
    }

    public double getAccumulatedTimeToPlot() {
        return millisToHours(accumulatedTimeToPlot);
    }

    public void addPlottingTime(long time) {
        this.accumulatedTimeToPlot += time;
    }

    public static double millisToHours(long millis) {
        return ((double)(millis / 600)) / 100;
    }
}
