package sk.th.pipifax.util;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 11.11.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class SRSUtil {

    public static final int MAX_QUALITY = 3;
    public static final float E_FACTOR_FLOOR = 1.3f;
    public static final float INITIAL_E_FACTOR = 2.5f;

    /**
     * Score a Card and set it's correct E-Factor.
     */
    public static float scoreCard(float currentEFactor, int quality) {
        float qFactor = (MAX_QUALITY - quality);
        float newFactor = currentEFactor + (0.1f - qFactor * (0.08f + qFactor * 0.02f));
        if (newFactor < E_FACTOR_FLOOR) {
            newFactor = E_FACTOR_FLOOR;
        }
        return newFactor;
    }

    public static int calcuateInterval(float easynessFactor, int interval, int wcount) {
        int newinterval = 1;
        if (easynessFactor < 3) {
            /**
             * If the quality response was lower than 3 then start repetitions for the item from the beginning without changing the E-Factor
             * (i.e. use intervals I(1), I(2) etc. as if the item was memorized anew).
             */
            wcount = 1;
        }
        if (wcount == 1) {
            newinterval = 1;
        } else if (wcount == 2) {
            newinterval = 6;
        } else if (wcount > 2) {
            newinterval = Math.round(interval * easynessFactor);
        }
        return newinterval;
    }


}
