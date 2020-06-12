package org.chandra.zebra.interview.bowling.game.models;

/**
 * Possible score types a bowler can score for each chance.
 *
 * @author Chandra Sekhar Battula
 */
public enum ScoreType {
    /**
     * Regular score type.
     */
    REGULAR(""),
    /**
     * Spare score type.
     */
    SPARE("/"),
    /**
     * Strike score type.
     */
    STRIKE("X");

    private String symbol;

    ScoreType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
