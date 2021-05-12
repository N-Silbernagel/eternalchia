package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    private final List<String> chiaArgs = List.of("-k", "32", "-r", "1");

    private ChiaCliHandler chiaCliHandler;

    @BeforeEach
    public void setUp() throws IOException {
        when(processBuilder.command(anyString(), anyString())).thenReturn(processBuilder);
        when(processBuilder.inheritIO()).thenReturn(processBuilder);
        when(processBuilder.start()).thenReturn(process);

        chiaCliHandler = new ChiaCliHandler(chiaArgs, processBuilder, statistics);
    }

    @Test
    public void it_executes_chia_plot_command_with_args () throws IOException, InterruptedException {
        ChiaCliHandler chiaCliHandler = new ChiaCliHandler(chiaArgs, processBuilder, statistics);

        chiaCliHandler.plot(1);

        verify(processBuilder).command(anyString(), eq(String.join(" ", chiaArgs)));
        verify(processBuilder).start();
    }

    @Test
    public void it_counts_the_plot_count_up() throws IOException, InterruptedException {
        int plots = 5;

        for(int i = 0; i < plots; i++){
            chiaCliHandler.plot(1);
        }

        verify(statistics, times(5)).addGeneratedPlot();
    }

    @Test
    public void it_waits_for_the_plot_command_to_finish() throws IOException, InterruptedException {
        chiaCliHandler.plot(1);

        verify(process).waitFor();
    }

    @Test
    public void it_redirects_the_plotting_output_to_stdout() throws IOException, InterruptedException {

        chiaCliHandler.plot(1);

        verify(processBuilder).inheritIO();
    }

    @Test
    public void it_doesnt_count_plots_up_is_process_exists_with_error() throws IOException, InterruptedException {
        when(process.waitFor()).thenReturn(1);

        chiaCliHandler.plot(1);

        verify(statistics, times(0)).addGeneratedPlot();
    }
}