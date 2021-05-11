package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChiaCliHandlerTest {
    @Mock
    public ProcessBuilder processBuilder;
    @Mock
    public Process process;
    @Mock
    public Statistics statistics;

    private final String[] chiaArgs = new String[]{"-k", "32", "-r", "1"};

    @BeforeEach
    public void setUp() throws IOException {
        when(processBuilder.command(anyString(), anyString())).thenReturn(processBuilder);
        when(processBuilder.inheritIO()).thenReturn(processBuilder);
        when(processBuilder.start()).thenReturn(process);
    }

    @Test
    public void it_executes_chia_plot_command_with_args () throws IOException, InterruptedException {
        ChiaCliHandler chiaCliHandler = new ChiaCliHandler(chiaArgs, processBuilder, statistics);

        chiaCliHandler.plot();

        verify(processBuilder).command(anyString(), eq(String.join(" ", chiaArgs)));
        verify(processBuilder).start();
    }

    @Test
    public void it_counts_the_plot_count_up() throws IOException, InterruptedException {
        int plots = 5;

        ChiaCliHandler chiaCliHandler = new ChiaCliHandler(chiaArgs, processBuilder, statistics);

        for(int i = 0; i < plots; i++){
            chiaCliHandler.plot();
        }

        verify(statistics, times(5)).addGeneratedPlot();
    }

    @Test
    public void it_waits_for_the_plot_command_to_finish() throws IOException, InterruptedException {
        ChiaCliHandler chiaCliHandler = new ChiaCliHandler(chiaArgs, processBuilder, statistics);

        chiaCliHandler.plot();

        verify(process).waitFor();
    }

    @Test
    public void it_redirects_the_plotting_output_to_stdout() throws IOException, InterruptedException {
        ChiaCliHandler chiaCliHandler = new ChiaCliHandler(chiaArgs, processBuilder, statistics);

        chiaCliHandler.plot();

        verify(processBuilder).inheritIO();
    }
}