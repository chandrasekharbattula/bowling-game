package org.chandra.zebra.interview.bowling.game.validation;

import org.chandra.zebra.interview.bowling.game.exceptions.InvalidInputDataException;
import org.chandra.zebra.interview.bowling.game.service.InputFileReader;
import org.chandra.zebra.interview.bowling.game.service.InputReaderImpl;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.chandra.zebra.interview.bowling.game.constants.Constants.*;
import static org.junit.Assert.assertEquals;

public class InputValidatorTest {

    InputFileReader inputFileReader;
    InputValidator inputValidator = new InputValidatorImpl();
    List<String[]> scores;


    @Test
    public void testInputWithLessThanTenLines() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/less_than_ten_lines.txt"));
        scores = inputFileReader.readFile();
        try {
            inputValidator.validateInputScores(scores);
        }catch (InvalidInputDataException e){
            assertEquals(BAD_DATA_LESS_LINES + " The current file has only "+ scores.size() + " lines.", e.getMessage());
        }
    }

    @Test
    public void testInputWithOnlyOneAttribute() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/missing_attributes.txt"));
        scores = inputFileReader.readFile();
        try {
            inputValidator.validateInputScores(scores);
        }catch (InvalidInputDataException e){
            assertEquals(MISSING_ATTRIBUTES, e.getMessage());
        }
    }

    @Test
    public void testInputWithInvalidPinsAttribute() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/incorrect_pins.txt"));
        scores = inputFileReader.readFile();
        try {
            inputValidator.validateInputScores(scores);
        }catch (InvalidInputDataException e){
            assertEquals(BAD_DATA_PINS_BOWLED, e.getMessage());
        }
    }

}