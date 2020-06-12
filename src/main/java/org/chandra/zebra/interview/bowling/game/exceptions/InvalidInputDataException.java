package org.chandra.zebra.interview.bowling.game.exceptions;

/**
 * Class that declares a Custom Exception for Bad Input Data
 */
public class InvalidInputDataException extends RuntimeException{

    /**
     * Instantiates a new Invalid input data exception.
     *
     * @param message the message
     */
    public InvalidInputDataException(String message) {
        super(message);
    }
}
