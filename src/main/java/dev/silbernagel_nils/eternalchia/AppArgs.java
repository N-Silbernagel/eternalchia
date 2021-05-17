package dev.silbernagel_nils.eternalchia;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppArgs {
    @Parameter(names = "-n", description = "Number of plots to do in parallel")
    private int parallelPlots;
    @Parameter(names = "-dry", description = "Do a dry run, without executing chia cli")
    private boolean dry;
    @Parameter(names = "-ca", description = "Arguments for the chia cli")
    private String chiaArgs;
    @Parameter(names = "-cp", description = "Path to chia executable")
    private String chiaPath;

    public int getParallelPlots() {
        return parallelPlots;
    }

    public boolean isDry() {
        return dry;
    }

    public List<String> getChiaArgsAsList() {
        if (this.chiaArgs == null){
            return new ArrayList<>();
        }
        return Arrays.asList(this.chiaArgs.split(" "));
    }

    public boolean hasChiaPath() {
        return chiaPath == null || chiaPath.isEmpty();
    }

    public String getChiaPath() {
        return chiaPath;
    }
}
