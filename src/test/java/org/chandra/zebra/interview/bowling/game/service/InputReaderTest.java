package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.exceptions.InvalidFileException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputReaderTest {

    InputFileReader inputFileReader;

    @Test
    public void testReadSuccessful() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/file_with_full_score.txt"));
        List<String[]> scoresList = inputFileReader.readFile();
        assertEquals(12, scoresList.size());
    }

    @Test(expected = InvalidFileException.class)
    public void testFileMissing() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/missing.txt"));
        inputFileReader.readFile();
    }
}