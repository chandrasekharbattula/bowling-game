package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;

import java.util.List;

/**
 * This interfaces declares an abstract method to get the bowlers and in-turn interpret the scores passed in the input to the application.
 *
 * @author Chandra Sekhar
 */
public interface ScoreParser {

    /**
     * Gets bowlers.
     *
     * @return the bowlers
     */
    List<Bowler> getBowlers();
}
