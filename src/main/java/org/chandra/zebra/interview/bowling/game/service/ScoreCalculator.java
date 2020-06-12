package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.chandra.zebra.interview.bowling.game.models.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;

/**
 * Class to calculate Scores for each bowler
 *
 * @author Chandra Sekhar Battula
 */
public class ScoreCalculator {

    /**
     * Method to Calculate scores for each bowler.
     *
     * @param bowler The bowler object with just the name
     * @return the bowler object with calculated scores for frame and total score.
     */
    public static Bowler calculateEachBowlerScores(Bowler bowler) {
        List<Integer> scores = new ArrayList<>();
        ListIterator<Frame> iterator = bowler.getFrames().listIterator();
        int eachFrameScore = 0, frameNumber = 1;

        while (frameNumber <= 10){
            eachFrameScore += getScore(iterator, iterator.next(), 0, frameNumber == 10);
            scores.add(eachFrameScore);
            frameNumber++;
        }
        bowler.setScoresOfEachFrame(scores);
        return bowler;
    }

    /**
     * Method to Calculate scores for a particular frame.
     *
     * @param iterator       The bowler object with just the name
     * @param currentFrame
     * @param strikesCounter
     * @param lastFrame
     * @return an Integer with score for that particular frame.
     */
    private static int getScore(ListIterator<Frame> iterator, Frame currentFrame, int strikesCounter, boolean lastFrame) {
        int total = 0;

        if (currentFrame.isRegular()) {
            total += strikesCounter < 2 ? currentFrame.getScore() : currentFrame.getFirstChanceIntegerValue();
        }

        if (currentFrame.isSpare()) {
            if (strikesCounter == 1) {
                total += currentFrame.getScore();
            } else if (strikesCounter == 2) {
                total += currentFrame.getFirstChanceIntegerValue();
            } else {
                total += currentFrame.getScore();
                if (iterator.hasNext()) {
                    total += iterator.next().getFirstChanceIntegerValue();
                    iterator.previous();
                }
            }
        }

        if (currentFrame.isStrike()) {
            total += 10;

            if (strikesCounter < 2) {
                strikesCounter++;

                if (lastFrame && iterator.hasNext()) {
                    Frame nextFrame = iterator.next();
                    total += currentFrame.isSpare() ? nextFrame.getFirstChanceIntegerValue() : nextFrame.getScore();
                    return total;
                }

                if (iterator.hasNext()) {
                    Frame nextFrame = iterator.next();
                    total += getScore(iterator, nextFrame, strikesCounter, false);
                    iterator.previous();
                }
            }
        }
        return total;
    }
}
