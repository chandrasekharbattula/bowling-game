package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ScoreCalculatorTest {

    InputFileReader inputFileReader;
    ScoreParser scoreParser;
    Bowler bowler;

    @Test
    public void calculateEachBowlerScores() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/file_with_full_score.txt"));
        scoreParser = new ScoreParserImpl(inputFileReader.readFile());
        bowler = ScoreCalculator.calculateEachBowlerScores(scoreParser.getBowlers().get(0));
        assertEquals(Integer.valueOf(270),bowler.getScoresOfEachFrame().get(8));
    }

    @Test
    public void calculateFoulScores() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/file_with_all_fouls.txt"));
        scoreParser = new ScoreParserImpl(inputFileReader.readFile());
        bowler = ScoreCalculator.calculateEachBowlerScores(scoreParser.getBowlers().get(0));
        assertEquals(Integer.valueOf(0),bowler.getScoresOfEachFrame().get(8));
    }
}