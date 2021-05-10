package dev.silbernagel_nils.eternalchia;

public class CommandErrorException extends RuntimeException{
    public CommandErrorException(String message) {
        super(message);
    }
}
