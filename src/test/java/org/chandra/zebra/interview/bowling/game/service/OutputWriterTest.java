package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OutputWriterTest {

    OutputWriterImpl outputWriter = new OutputWriterImpl();

    InputFileReader inputFileReader;
    ScoreParser scoreParser;
    Bowler bowler;

    @Test
    public void testFrameBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Frame\t\t");
        IntStream.range(1, 11).forEach(e -> {
            stringBuilder.append(e);
            stringBuilder.append("\t\t");
        });
        String expectedString = stringBuilder.toString();
        assertEquals(expectedString, outputWriter.buildFrameString());
    }

    @Test
    public void testBuildScoreString() {
        List<Integer> scoresForEachFrame = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        Bowler bowler = new Bowler(null, null, scoresForEachFrame);
        String[] expectedScores = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        assertArrayEquals(expectedScores, outputWriter.buildScoreString(bowler).trim().split("\t\t"));
    }

    @Test
    public void testBuildPinFallsString() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/file_with_full_score.txt"));
        scoreParser = new ScoreParserImpl(inputFileReader.readFile());
        bowler = ScoreCalculator.calculateEachBowlerScores(scoreParser.getBowlers().get(0));
        String[] expectedScores = new String[]{"X", "X", "X", "X", "X", "X", "X", "X","X", "X", "X", "X"};
        assertArrayEquals(expectedScores,outputWriter.buildPinFallsString(bowler).trim().split("\t+"));
    }

}