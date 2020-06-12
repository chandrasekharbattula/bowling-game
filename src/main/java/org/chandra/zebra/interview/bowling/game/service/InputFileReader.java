package org.chandra.zebra.interview.bowling.game.service;

import java.util.List;

/**
 * The interface that declares the basic abstract method to read input file to the application.
 *
 * @author Chandra Sekhar Battula
 */
public interface InputFileReader {

    /**
     * Method to read the file and return the list of string array..
     *
     * @return A list of string array where each index of the list is a line from the input file
     */
    List<String[]> readFile();

}
