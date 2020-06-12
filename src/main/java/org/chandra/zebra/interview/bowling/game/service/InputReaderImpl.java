package org.chandra.zebra.interview.bowling.game.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.chandra.zebra.interview.bowling.game.exceptions.InvalidFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class that implements the InputFileReader interface to read the input file data.
 *
 * @author Chandra Sekhar Battula
 */
@AllArgsConstructor
public class InputReaderImpl implements InputFileReader {

    // Variable that declares a Logger for logging any information to the console
    private static final Logger LOGGER = Logger.getLogger(InputReaderImpl.class.getName());

    // Variables to store the file name passed to the application
    private File inputFileName;

    /**
     * Method to read the file and return a list of String array where each element in the array is an attribute in the input file
     *
     * @return List<String[]>
     */
    @Override
    public List<String[]> readFile() {
        List<String[]> dataFromFile = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
            List<String> linesInTheFile = bufferedReader.lines().collect(Collectors.toList());
            dataFromFile = linesInTheFile.stream()
                    .map(x -> x.split(" "))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File named {0} is not found, please try executing the application again with the correct file name", inputFileName);
            throw new InvalidFileException("Exception in reading the input file data");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new InvalidFileException("Exception in reading the input file data");
        }

        return dataFromFile;
    }

}
