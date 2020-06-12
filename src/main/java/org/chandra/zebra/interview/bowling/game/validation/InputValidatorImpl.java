package org.chandra.zebra.interview.bowling.game.validation;

import org.chandra.zebra.interview.bowling.game.exceptions.InvalidInputDataException;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static org.chandra.zebra.interview.bowling.game.constants.Constants.*;

/**
 * The class that implements the Input Validator interface and defines the methods to validate the input data.
 */
public class InputValidatorImpl implements InputValidator {

    private static final Logger LOGGER = Logger.getLogger(InputValidatorImpl.class.getName());

    /**
     * A Predicate Functional interface to check if the pins bowled value in the input is valid.
     */
    Predicate<String> validPinsBowled =  x-> x.equals(FOUL)  || Integer.parseInt(x) >= 0 || Integer.parseInt(x) <= 10;

    /**
     * Validates the input scores and throws and exception if any score is found invalid
     *
     * @param scores the scores
     */
    @Override
    public void validateInputScores(List<String[]> scores) {

        if(scores.size() <10) {
            throw new InvalidInputDataException(BAD_DATA_LESS_LINES + " The current file has only "+ scores.size() + " lines.");
        }else{
            scores.stream().forEach(x-> validateEachScore(x));
        }

    }

    /**
     * Validates the data in each line and throws and exception if any data is found invalid
     *
     * @param data the data of each line
     */
    private void validateEachScore(String[] data) {
        if(data.length != 2) {
            throw new InvalidInputDataException(MISSING_ATTRIBUTES);
        }else {
                validatePinsBowled(data[1]);
            }
    }

    /**
     * Validates the pins bowled in each line and throws and exception if any value is found invalid
     *
     * @param pinsBowled the pins bowled values of each line
     */
    private void validatePinsBowled(String pinsBowled) {
        try {
            if (!validPinsBowled.test(pinsBowled)){
                throw new InvalidInputDataException(BAD_DATA_PINS_BOWLED);
            }
        }catch (NumberFormatException e){
            LOGGER.severe(e.getMessage());
            throw new InvalidInputDataException(BAD_DATA_PINS_BOWLED);
        }
    }
}
