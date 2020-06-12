package org.chandra.zebra.interview.bowling.game.exceptions;

/**
 * Class that declares a Custom Exception Invalid Command Line Arguments
 */
public class InvalidFileException extends RuntimeException {

    /**
     * Instantiates a new InvalidFileException exception.
     *
     * @param message the message
     */
    public InvalidFileException(String message) {
        super(message);
    }
}
