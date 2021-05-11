package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    @Mock
    public Statistics stats;

    @Test
    public void it_handles_a_stop_command() {
        System.setIn(new ByteArrayInputStream(InputHandler.STOP_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = new InputHandler(stats);

        inputHandler.listen();

        assertTrue(inputHandler.receivedStop());
    }

    @Test
    public void it_handles_an_info_command() {
        System.setIn(new ByteArrayInputStream(InputHandler.INFO_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = spy(new InputHandler(stats));

        inputHandler.listen();

        verify(stats).showInfo();
    }
}