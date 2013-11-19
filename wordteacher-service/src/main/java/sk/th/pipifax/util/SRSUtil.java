package sk.th.pipifax.util;

import org.springframework.util.Assert;
import sk.th.pipifax.entity.RepetitionMode;
import sk.th.pipifax.entity.WordEntity;

import java.sql.Timestamp;
import java.util.Calendar;

public class SRSUtil {

    public static final int MAX_QUALITY = 5;
    public static final float E_FACTOR_FLOOR = 1.3f;
    public static final float INITIAL_E_FACTOR = 2.5f;
    public static final float INITIAL_INTERVAL = 1;

    public static final int PERFECT = 5;
    public static final int CORRECT = 4;
    public static final int MEDIUM = 3;
    public static final int HARD = 1;


    public static WordEntity repetition(WordEntity currentWord, int responseQuality) {
        RepetitionMode mode = currentWord.getMode();
        switch (mode) {
            case LEARNING:
                float updatedFactor;
                int updatedInterval;
                int updatedRepetition;

                if (responseQuality < 3) {
                    updatedInterval = 1;
                    updatedRepetition = 0;
                } else {
                    updatedInterval = SRSUtil.calcuateInterval(currentWord.getEFactor(), currentWord.getInterval(), currentWord.getCount());
                    updatedRepetition = currentWord.getCount() + 1;
                }

                updatedFactor = scoreCard(currentWord.getEFactor(), responseQuality);

                currentWord.setEFactor(updatedFactor);
                currentWord.setCount(updatedRepetition);
                currentWord.setInterval(updatedInterval);
                currentWord.setNextRepetition(DateUtil.addDaysToCurrentDate(updatedInterval));
            case QA:
                currentWord.setModified(DateUtil.getCurrentDate());
                currentWord.setLastQuality(responseQuality);
            default:
                return currentWord;
        }
    }
        /**
         * Score a Card and set it's correct E-Factor.
         */

    private static float scoreCard(float currentEFactor, int responseQuality) {
        float qFactor = (MAX_QUALITY - responseQuality);
        float newFactor = currentEFactor + (0.1f - qFactor * (0.08f + qFactor * 0.02f));
        if (newFactor < E_FACTOR_FLOOR) {
            newFactor = E_FACTOR_FLOOR;
        }
        return newFactor;

    }

    /**
     * calculate repetition interval in days
     *
     * @param easinessFactor
     * @param oldInterval
     * @param repetition
     * @return
     */
    private static int calcuateInterval(float easinessFactor, int oldInterval, int repetition) {
        Assert.isTrue(repetition > -1);
        if (repetition == 0) {
            return 1;
        } else if (repetition == 1) {
            return 5;
        } else {
            return Math.round(oldInterval * easinessFactor);
        }
    }
}
