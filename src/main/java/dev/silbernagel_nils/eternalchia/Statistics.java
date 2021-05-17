package dev.silbernagel_nils.eternalchia;

public class Statistics {
    private long generatedPlots = 0;
    private long plotTime = 0;
    private long failPlots = 0;

    public long getGeneratedPlots() {
        return generatedPlots;
    }

    public long addGeneratedPlot() {
        return ++this.generatedPlots;
    }

    public void removeGeneratedPlot() {
        this.generatedPlots--;
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
        return ((double)(millis / 600)) / 100;
    }

    public void showInfo() {
        System.out.println("Generated " + getGeneratedPlots() + " plots.");
        System.out.println(getFailedPlots() + " plots failed.");
        System.out.println("Total plotting time: " + getPlotTime() + ".");
    }
}
