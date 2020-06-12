package org.chandra.zebra.interview.bowling.game;

import static org.chandra.zebra.interview.bowling.game.constants.Constants.MULTIPLE_ARGS_PROVIDED;
import static org.chandra.zebra.interview.bowling.game.constants.Constants.NO_FILE_PROVIDED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.chandra.zebra.interview.bowling.game.exceptions.InvalidCommandLineArgumentsException;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testMissingArguments() {
        String [] args =  new String[]{};
        try {
            App.readInputArguments(args);
        }catch (InvalidCommandLineArgumentsException e){
            assertEquals(NO_FILE_PROVIDED, e.getMessage());
        }
    }

    @Test
    public void testMoreArguments() {
        String [] args = new String[] {"read.txt", "game.txt"};
        try {
            App.readInputArguments(args);
        }catch (InvalidCommandLineArgumentsException e){
            assertEquals(MULTIPLE_ARGS_PROVIDED, e.getMessage());
        }

    }
}
