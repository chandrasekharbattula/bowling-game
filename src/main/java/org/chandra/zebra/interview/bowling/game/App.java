package org.chandra.zebra.interview.bowling.game;


import org.chandra.zebra.interview.bowling.game.exceptions.InvalidCommandLineArgumentsException;
import org.chandra.zebra.interview.bowling.game.exceptions.InvalidFileException;
import org.chandra.zebra.interview.bowling.game.exceptions.InvalidInputDataException;
import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.chandra.zebra.interview.bowling.game.service.*;
import org.chandra.zebra.interview.bowling.game.validation.InputValidator;
import org.chandra.zebra.interview.bowling.game.validation.InputValidatorImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.chandra.zebra.interview.bowling.game.constants.Constants.MULTIPLE_ARGS_PROVIDED;
import static org.chandra.zebra.interview.bowling.game.constants.Constants.NO_FILE_PROVIDED;

/**
 * Class that server as a Main Class for an App to compute the score of a bowling game.
 *
 * @author Chandra Sekhar Battula
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    /**
     * The entry point of application.
     *
     * @param args the input arguments parsed from the command line.
     */
    public static void main(String[] args) {
        File inputFileName = null;
        List<String[]> dataFromFile = new ArrayList<>();

        try {
            inputFileName = new File(readInputArguments(args));
        } catch (InvalidCommandLineArgumentsException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            InputFileReader inputFileReader = new InputReaderImpl(inputFileName);
            InputValidator inputValidator = new InputValidatorImpl();
            dataFromFile = inputFileReader.readFile();
            inputValidator.validateInputScores(dataFromFile);
        } catch (InvalidInputDataException | InvalidFileException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        ScoreParser scoreParser = new ScoreParserImpl(dataFromFile);

        List<Bowler> bowlers= scoreParser.getBowlers()
                .stream()
                .map(ScoreCalculator::calculateEachBowlerScores)
                .collect(Collectors.toList());

        OutputWriter outputWriter = new OutputWriterImpl();
        outputWriter.printScoreBoard(bowlers);
    }

    /**
     * Validates the command line arguments
     *
     * @param args Command line arguments given while executing the application.
     * @return The file name passed as argument while executing the application.
     * @throws InvalidCommandLineArgumentsException
     */
    public static String readInputArguments(String[] args) throws InvalidCommandLineArgumentsException {
        if (args.length == 0) {
            throw new InvalidCommandLineArgumentsException(NO_FILE_PROVIDED);
        } else if (args.length > 1) {
            throw new InvalidCommandLineArgumentsException(MULTIPLE_ARGS_PROVIDED);
        } else return args[0];
    }

}
