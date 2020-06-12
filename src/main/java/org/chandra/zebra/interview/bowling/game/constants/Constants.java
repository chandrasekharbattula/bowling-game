package org.chandra.zebra.interview.bowling.game.constants;

/**
 * Class with all the Constants declared that will be used across the application.
 *
 * @author Chandra Sekhar Battula
 */
public class Constants {

    /**
     * The constant NO_FILE_PROVIDED.
     */
    public static final String NO_FILE_PROVIDED = "File name is not provided as command line argument to the application";
    /**
     * The constant MULTIPLE_ARGS_PROVIDED.
     */
    public static final String MULTIPLE_ARGS_PROVIDED = "Only one argument is accepted and it should be a file name.";
    /**
     * The constant BAD_DATA_PINS_BOWLED.
     */
    public static final String BAD_DATA_PINS_BOWLED = "Bad Data ! Invalid pins bowled value provided in the input. Valid values are 0-10, F.";

    /**
     * The constant BAD_DATA_LESS_LINES.
     */
    public static final String BAD_DATA_LESS_LINES = "The input file should have at least 10 lines for the bowling game to work.";

    /**
     * The constant MISSING_ATTRIBUTES.
     */
    public static final String MISSING_ATTRIBUTES = "Expected two values(name and no.of pins bowled) in each line of input file.";
    /**
     * The constant FOUL.
     */
    public static final String FOUL = "F";

}
