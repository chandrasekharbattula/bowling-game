package org.chandra.zebra.interview.bowling.game.service;

import org.chandra.zebra.interview.bowling.game.models.Bowler;
import org.chandra.zebra.interview.bowling.game.models.Frame;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Class that implements the OutputWriter to print the score board on the Console
 *
 * @author Chandra Sekhar Battula
 */
public class OutputWriterImpl implements OutputWriter {

    /**
     * A Functional interface to build a string to print regular pinfalls.
     */
    Function<Frame, String> buildRegularPinfalls = frame -> {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(frame.getFirstChanceStringValue());
        stringBuilder.append("\t");
        stringBuilder.append(frame.getSecondChanceStringValue());
        stringBuilder.append("\t");
        return stringBuilder.toString();
    };

    /**
     * The main method of the class which invokes other methods to build each segment of the score board and  print the score board on the console
     *
     * @param bowlers The list of bowlers for whom the scores were determined in the application
     */
    @Override
    public void printScoreBoard(List<Bowler> bowlers) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(buildFrameString());
        bowlers.forEach(bowler -> {
            stringBuilder.append("\n");
            stringBuilder.append(bowler.getBowlerName());
            stringBuilder.append("\n");
            stringBuilder.append("Pinfalls");
            stringBuilder.append(buildPinFallsString(bowler));
            stringBuilder.append("\n");
            stringBuilder.append("Score");
            stringBuilder.append(buildScoreString(bowler));
        });

        System.out.println(stringBuilder.toString());
    }

    /**
     * Builds the Frame header
     *
     * @return String with Frame header to be printed on the console
     */
    public String buildFrameString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Frame\t\t");
        IntStream.range(1, 11).forEach(e -> {
            stringBuilder.append(e);
            stringBuilder.append("\t\t");
        });
        return stringBuilder.toString();
    }

    /**
     * Builds the Score line that has score for each frame
     *
     * @return String with Score for each frame to be printed on the console
     */
    public String buildScoreString(Bowler bowler) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 10)
                .map(i -> bowler.getScoresOfEachFrame().get(i))
                .forEach(score -> {
                    stringBuilder.append("\t\t");
                    stringBuilder.append(score);
                });
        return stringBuilder.toString();
    }

    /**
     * Builds the pin falls line that has score for each chance
     *
     * @return String with Pin falls for each chance to be printed on the console
     */
    public String buildPinFallsString(Bowler bowler) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        IntStream.range(0, 10).forEach(i -> {

            Frame frame = bowler.getFrames().get(i);

            if (i == 9) {
                List<Frame> tenRoundFrames = bowler.getFrames().subList(9, bowler.getFrames().size());
                stringBuilder.append(buildTenthFramePinfalls(tenRoundFrames));
            } else {
                if (frame.isStrike()) {
                    stringBuilder.append("\t");
                    stringBuilder.append(frame.getScoreType());
                    stringBuilder.append("\t");
                } else {
                    stringBuilder.append(buildRegularPinfalls.apply(frame));
                }
            }
        });
        return stringBuilder.toString();
    }

    /**
     * Builds the pin falls line for Tenth Frame
     *
     * @return String with Pin falls for Tenth Frame to be printed on the console
     */
    public String buildTenthFramePinfalls(List<Frame> tenRoundFrames) {
        StringBuilder stringBuilder = new StringBuilder();
        tenRoundFrames.forEach(frame -> {
            if (frame.isStrike()) {
                stringBuilder.append(frame.getScoreType());
                stringBuilder.append("\t");
            } else {
                stringBuilder.append(buildRegularPinfalls.apply(frame));
            }
        });

        return stringBuilder.toString();
    }

}
