package org.chandra.zebra.interview.bowling.game.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Bowler.
 */
@Data
@AllArgsConstructor
public class Bowler {
    private String bowlerName;
    private List<Frame> frames = new LinkedList<>();
    private List<Integer> scoresOfEachFrame = new ArrayList<>();

    /**
     * Instantiates a new Bowler.
     *
     * @param name the name
     */
    public Bowler(String name){
        this.bowlerName = name;
    }

    /**
     * Register frame.
     *
     * @param frame the frame
     */
    public void registerFrame(Frame frame){
        frames.add(frame);
    }

    /**
     * Did not strike on tenth frame boolean.
     *
     * @return the boolean
     */
    public boolean didNotStrikeOnTenthFrame() {
        return !getTenthFrame().isStrike();
    }

    /**
     * Gets tenth frame.
     *
     * @return the tenth frame
     */
    public Frame getTenthFrame() {
        return frames.get(9);
    }

    /**
     * Gets last frame.
     *
     * @return the last frame
     */
    public Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    /**
     * Is last frame in progress boolean.
     *
     * @return the boolean
     */
    public boolean isLastFrameInProgress() {
        return getLastFrame().isInProgress();
    }

    /**
     * Is playing tenth frame boolean.
     *
     * @return the boolean
     */
    public boolean isPlayingTenthFrame() {
        return frames.size() == 10;
    }

    /**
     * Is playing extra frame boolean.
     *
     * @return the boolean
     */
    public boolean isPlayingExtraFrame() {
        return frames.size() == 11;
    }

    /**
     * Has completed extra frame boolean.
     *
     * @return the boolean
     */
    public boolean hasCompletedExtraFrame() {
        Frame tenthTurn = getTenthFrame();
        return (tenthTurn.isStrike() || tenthTurn.isSpare()) && !isLastFrameInProgress();
    }

    /**
     * Register second chance.
     *
     * @param chance the chance
     */
    public void registerSecondChance(Chance chance) {

        getLastFrame().registerSecondChance(chance);
    }

}
