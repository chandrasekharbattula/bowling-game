package org.chandra.zebra.interview.bowling.game.models;

import lombok.Getter;

/**
 * The type Frame.
 */
@Getter
public class Frame {
    /**
     * The First chance.
     */
    Chance firstChance;
    /**
     * The Second chance.
     */
    Chance secondChance;
    /**
     * The Score type.
     */
    ScoreType scoreType = ScoreType.REGULAR;

    /**
     * Instantiates a new Frame.
     *
     * @param firstChance the first chance
     */
    public Frame(Chance firstChance){
        this.firstChance = firstChance;

        if(this.firstChance.getPinsBowled() == 10){
            this.scoreType = ScoreType.STRIKE;
            this.secondChance = new Chance(0);
        }
    }

    /**
     * Instantiates a new Frame.
     *
     * @param firstChance  the first chance
     * @param secondChance the second chance
     */
    public Frame(Chance firstChance, Chance secondChance){
        this.firstChance = firstChance;
        this.secondChance = secondChance;

        if(this.firstChance.getPinsBowled() == 10) {
            scoreType = ScoreType.STRIKE;
        } else if (firstChance.getPinsBowled() + secondChance.getPinsBowled() == 10){
            scoreType = ScoreType.SPARE;
        }
    }

    /**
     * Incomplete extra frame frame.
     *
     * @param firstChance the first chance
     * @return the frame
     */
    public static Frame incompleteExtraFrame(Chance firstChance){
        Frame incompleteExtraFrame =new Frame(firstChance);
        incompleteExtraFrame.secondChance =null;
        incompleteExtraFrame.scoreType = ScoreType.REGULAR;
        return incompleteExtraFrame;
    }

    /**
     * Gets first chance integer value.
     *
     * @return the first chance integer value
     */
    public int getFirstChanceIntegerValue() {
        return firstChance.getPinsBowled();
    }

    /**
     * Gets first chance string value.
     *
     * @return the first chance string value
     */
    public String getFirstChanceStringValue () {
        if (isStrike()) {
            return scoreType.toString();
        } else if (firstChance.getPinsBowled() == 10) {
            return ScoreType.STRIKE.toString();
        }
        return firstChance.toString();
    }

    /**
     * Gets second chance string value.
     *
     * @return the second chance string value
     */
    public String getSecondChanceStringValue () {
        if (isSpare()) {
            return scoreType.toString();
        } else if (secondChance.getPinsBowled() == 10) {
            return ScoreType.STRIKE.toString();
        }
        return secondChance.toString();
    }

    /**
     * Is in progress boolean.
     *
     * @return the boolean
     */
    public boolean isInProgress() {
        return this.firstChance == null || this.secondChance == null;
    }

    /**
     * Register second chance.
     *
     * @param secondChance the second chance
     */
    public void registerSecondChance(Chance secondChance) {
        this.secondChance = secondChance;

        if (getFirstChanceIntegerValue() != 10 && getFrameScore() == 10) {
            this.scoreType = ScoreType.SPARE;
        }
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Integer getScore() {
        switch (scoreType) {
            case STRIKE:
            case SPARE:
                return 10;
            default:
                return getFrameScore();
        }
    }

    private int getFrameScore() {
        return firstChance.getPinsBowled() + secondChance.getPinsBowled();
    }

    /**
     * Is strike boolean.
     *
     * @return the boolean
     */
    public boolean isStrike() {
        return scoreType.equals(ScoreType.STRIKE);
    }

    /**
     * Is spare boolean.
     *
     * @return the boolean
     */
    public boolean isSpare() {
        return scoreType.equals(ScoreType.SPARE);
    }

    /**
     * Is regular boolean.
     *
     * @return the boolean
     */
    public boolean isRegular() {
        return scoreType.equals(ScoreType.REGULAR);
    }
}
