package org.chandra.zebra.interview.bowling.game.validation;

import java.util.List;

/**
 * The interface Input validator declares the abstract method to validate the input data
 */
public interface InputValidator {

    /**
     * Validate input scores.
     *
     * @param scores the scores
     */
    void validateInputScores(List<String[]> scores);

}
