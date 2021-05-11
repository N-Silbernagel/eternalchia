package dev.silbernagel_nils.eternalchia;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {
    @Test
    public void it_acknowledges_a_stop_command() {
        System.setIn(new ByteArrayInputStream(InputHandler.STOP_COMMAND.getBytes(StandardCharsets.UTF_8)));

        InputHandler inputHandler = new InputHandler();

        inputHandler.listen();

        assertTrue(inputHandler.receivedStop());
    }
}