package org.chandra.zebra.interview.bowling.game.exceptions;

/**
 * Class that declares a Custom Exception Invalid Command Line Arguments
 */
public class InvalidCommandLineArgumentsException extends RuntimeException {

    /**
     * Instantiates a new Invalid command line arguments exception.
     *
     * @param message the message
     */
    public InvalidCommandLineArgumentsException(String message) {
        super(message);
    }
}
