package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreParserImplTest {

    InputFileReader inputFileReader;
    ScoreParser scoreParser;

    @Test
    public void testScoreParserForSingleBowler() {
        inputFileReader = new InputReaderImpl(new File("src/test/resources/file_with_full_score.txt"));
        scoreParser = new ScoreParserImpl(inputFileReader.readFile());
        List<Bowler> bowlerList = scoreParser.getBowlers();
        assertTrue(bowlerList.size() == 1);
        assertTrue(bowlerList.get(0).getBowlerName().equals("Carl"));
    }
}