package org.chandra.zebra.interview.bowling.game.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.chandra.zebra.interview.bowling.game.constants.Constants.FOUL;

/**
 * The type Chance.
 */
@AllArgsConstructor
@Getter
public class Chance {

    private int pinsBowled;

    private boolean foul;

    /**
     * Instantiates a new Chance.
     *
     * @param pinsBowled the pins bowled
     */
    public Chance(int pinsBowled){
        this.pinsBowled = pinsBowled;
    }

    /**
     * Missed chance chance.
     *
     * @return the chance
     */
    public static Chance missedChance() {
        return new Chance(0);
    }

    /**
     * Overriding toString method extended from Object class
     *
     * @return the String
     */
    @Override
    public String toString() {
        if (this.foul) {
            return FOUL;
        }
        return String.valueOf(this.pinsBowled);
    }
}
