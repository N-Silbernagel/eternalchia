package dev.silbernagel_nils.eternalchia;

import java.io.IOException;

public class ChiaCliHandler {
    private final String[] chiaArguments;

    private static final int END_OF_STREAM = -1;

    public ChiaCliHandler(String[] args) {
        this.chiaArguments = args;
    }

    public void plot() throws IOException {
        Process chiaCliProcess = new ProcessBuilder("ls", String.join(" ", this.chiaArguments)).start();

        int cliOutputRead = 0;
        int cliErrRead = 0;

        while(cliErrRead != END_OF_STREAM || cliOutputRead != END_OF_STREAM) {
            cliOutputRead = chiaCliProcess.getInputStream().read();
            cliErrRead = chiaCliProcess.getErrorStream().read();

            if (cliErrRead != END_OF_STREAM) {
                System.err.print((char) cliErrRead);
            }

            if (cliOutputRead != END_OF_STREAM){
                System.out.print((char) cliOutputRead);
            }
        }
    }
}
