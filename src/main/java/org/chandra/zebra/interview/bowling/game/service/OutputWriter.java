package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;

import java.util.List;

/**
 * The interface that declares the basic abstract method to print the score board of the game.
 *
 * @author Chandra Sekhar Battula
 */
public interface OutputWriter {

    /**
     * Print score board.
     *
     * @param bowlers the bowlers
     */
    void printScoreBoard(List<Bowler> bowlers);

}
