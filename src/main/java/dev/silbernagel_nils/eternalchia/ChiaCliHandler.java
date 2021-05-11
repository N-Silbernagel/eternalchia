package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class ChiaCliHandler {
    private final String[] chiaArguments;

    public ChiaCliHandler(String[] args) {
        this.chiaArguments = args;
    }

    public void plot() throws IOException {
        new ProcessBuilder("ls", String.join(" ", this.chiaArguments)).inheritIO().start();
    }
}
