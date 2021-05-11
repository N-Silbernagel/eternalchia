package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class ChiaCliHandler {
    private final String[] chiaArguments;
    private final ProcessBuilder processBuilder;

    private long plots = 0;

    public ChiaCliHandler(String[] args, ProcessBuilder processBuilder) {
        this.chiaArguments = args;
        this.processBuilder = processBuilder;
    }

    public void plot() throws IOException, InterruptedException {
        System.out.println("Starting plot n." + (plots + 1));

        processBuilder.command("ls", String.join(" ", this.chiaArguments))
                .inheritIO()
                .start()
                .waitFor();
        plots++;

        System.out.println("Finished plot n." + plots + '\n');
    }

    public long getPlots() {
        return plots;
    }
}
