package org.chandra.zebra.interview.bowling.game.service;

import lombok.AllArgsConstructor;
import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.chandra.zebra.interview.bowling.game.models.Chance;
import org.chandra.zebra.interview.bowling.game.models.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.chandra.zebra.interview.bowling.game.constants.Constants.FOUL;
import static org.chandra.zebra.interview.bowling.game.models.Chance.missedChance;
import static org.chandra.zebra.interview.bowling.game.models.Frame.incompleteExtraFrame;

/**
 * This class implements the interface Score Parser to parse the scores passed in the input to the application and determine the blowers and their scores.
 *
 * @author Chandra Sekhar
 */
@AllArgsConstructor
public class ScoreParserImpl implements ScoreParser {

    private static final Logger LOGGER = Logger.getLogger(ScoreParserImpl.class.getName());

    /**
     * The Data from input.
     */
    List<String[]> dataFromInput;

    /**
     * The Convert to chance object.
     */
    static Function<String, Chance> convertToChanceObject = x -> {
        if (x.equals(FOUL)) {
            return new Chance(0, true);
        } else {
            return new Chance(Integer.parseInt(x));
        }
    };

    /**
     * Method to parse the input data and determine the bowlers and in-turn interpret the scores of each bowler.
     *
     * @return list of bowlers
     */
    @Override
    public List<Bowler> getBowlers() {
        List<Bowler> bowlers = new ArrayList<>();
        this.dataFromInput.stream()
                .forEachOrdered(x -> determineBowlersAndPinsForFrame(bowlers, x[0], x[1]));
        return bowlers;
    }

    /**
     * Get the details of bowlers and store the pins bowled by them for each chance
     *
     * @param bowlers    the bowlers
     * @param bowlerName the bowler name
     * @param pinsBowled the pins bowled
     */
    public void determineBowlersAndPinsForFrame(List<Bowler> bowlers, String bowlerName, String pinsBowled) {

        int bowlerIndex = getBowlerIndex(bowlers, bowlerName);

        if (bowlerIndex == -1) {
            Bowler p = new Bowler(bowlerName);
            Frame newFrame = new Frame(convertToChanceObject.apply(pinsBowled));
            p.registerFrame(newFrame);
            bowlers.add(p);
        } else {
            Bowler bowler = bowlers.get(bowlerIndex);
            Chance chance = convertToChanceObject.apply(pinsBowled);
            boolean isLastFrameInProgress = bowler.isLastFrameInProgress();

            if (bowler.isPlayingTenthFrame()) {
                handleTenthFrame(bowlerName, pinsBowled, bowler);
                return;
            }

            if (bowler.isPlayingExtraFrame()) {
                handleExtraFrame(bowlerName, pinsBowled, bowler);
                return;
            }

            if (isLastFrameInProgress) {
                bowler.registerSecondChance(chance);
                return;
            }

            Frame newFrame = new Frame(convertToChanceObject.apply(pinsBowled));
            bowler.registerFrame(newFrame);
        }
    }

    private void handleTenthFrame(String bowlerName, String pinsBowled, Bowler bowler) {
        if (bowler.getTenthFrame().isInProgress()) {
            bowler.registerSecondChance(convertToChanceObject.apply(pinsBowled));
            return;
        }

        if (bowler.getTenthFrame().isRegular()) {
            LOGGER.log(Level.WARNING, "Ignoring pins bowled {0} of bowler {} with regular score on the last turn", new Object[]{pinsBowled, bowlerName});
            return;
        }

        Frame extraFrame = bowler.didNotStrikeOnTenthFrame()
                ? new Frame(convertToChanceObject.apply(pinsBowled), missedChance())
                : incompleteExtraFrame(convertToChanceObject.apply(pinsBowled));

        bowler.registerFrame(extraFrame);
    }

    private void handleExtraFrame(String bowlerName, String pinsBowled, Bowler bowler) {
        if (bowler.hasCompletedExtraFrame()) {
            LOGGER.log(Level.WARNING, "Ignoring pins bowled {0} of bowler {} extra frame completed", new Object[]{pinsBowled, bowlerName});
            return;
        }

        bowler.registerSecondChance(convertToChanceObject.apply(pinsBowled));
    }

    private int getBowlerIndex(List<Bowler> bowlers, String bowlerName) {
        int index = -1;

        for (int i = 0; i < bowlers.size(); i++) {
            if (bowlers.get(i).getBowlerName().equalsIgnoreCase(bowlerName)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
