package dev.silbernagel_nils.eternalchia;

public class Statistics {
    private long generatedPlots = 0;

    private long plotTime = 0;

    public long getGeneratedPlots() {
        return generatedPlots;
    }

    public void addGeneratedPlot() {
        this.generatedPlots++;
    }

    public double getPlotTime() {
        return millisToHours(plotTime);
    }

    public void addPlottingTime(long time) {
        this.plotTime += time;
    }

    public static double millisToHours(long millis) {
        return ((double)(millis / 600)) / 100;
    }

    public void showInfo() {
        System.out.println("Generated " + getGeneratedPlots() + " Plots in " + getPlotTime() + " hours");
    }
}
