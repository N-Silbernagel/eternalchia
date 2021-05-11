package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    @Mock
    private ChiaCliHandler chiaCliHandler;

    @Test
    public void it_handles_a_stop_command() {
        System.setIn(new ByteArrayInputStream(InputHandler.STOP_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = new InputHandler(chiaCliHandler);

        inputHandler.listen();

        assertTrue(inputHandler.receivedStop());
    }

    @Test
    public void it_handles_an_info_command() {
        System.setIn(new ByteArrayInputStream(InputHandler.INFO_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = spy(new InputHandler(chiaCliHandler));

        inputHandler.listen();

        verify(inputHandler).showInfo();
    }

    @Test
    public void info_includes_the_plots_generated() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        long expectedPlots = 5;
        when(chiaCliHandler.getPlots()).thenReturn(expectedPlots);

        InputHandler inputHandler = spy(new InputHandler(chiaCliHandler));

        inputHandler.showInfo();

        assertTrue(output.toString().contains("" + expectedPlots));
    }
}