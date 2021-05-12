package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    @Mock
    public Statistics stats;
    @Mock
    public ChiaCliHandler chiaCliHandler;

    @Test
    public void it_handles_a_stop_command() {
        when(chiaCliHandler.shouldStartNext()).thenReturn(true);
        System.setIn(new ByteArrayInputStream(InputHandler.STOP_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = new InputHandler(stats, chiaCliHandler);

        inputHandler.listen();

        verify(chiaCliHandler).setStartNext(false);
    }

    @Test
    public void it_handles_an_info_command() {
        when(chiaCliHandler.shouldStartNext()).thenReturn(true);
        System.setIn(new ByteArrayInputStream(InputHandler.INFO_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = spy(new InputHandler(stats, chiaCliHandler));

        inputHandler.listen();

        verify(stats).showInfo();
    }
}