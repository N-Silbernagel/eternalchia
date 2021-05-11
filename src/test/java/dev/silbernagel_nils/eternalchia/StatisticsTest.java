package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsTest {
    @Test
    public void info_includes_the_plots_generated() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Statistics stats = spy(new Statistics());

        long expectedPlots = 5;
        when(stats.getGeneratedPlots()).thenReturn(expectedPlots);


        stats.showInfo();

        assertTrue(output.toString().contains("" + expectedPlots));
    }

    @Test
    public void info_includes_the_cumulative_plotting_time() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Statistics stats = spy(new Statistics());

        double expectedTime = 5.0;
        when(stats.getPlotTime()).thenReturn(expectedTime);

        stats.showInfo();

        assertTrue(output.toString().contains("" + expectedTime));
    }
}