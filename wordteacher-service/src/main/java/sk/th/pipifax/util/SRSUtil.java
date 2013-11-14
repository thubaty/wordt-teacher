package sk.th.pipifax.util;

import sk.th.pipifax.dto.Card;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 11.11.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class SRSUtil {

    public static final int MAX_QUALITY = 5;
    public static final float E_FACTOR_FLOOR = 1.3f;

    /**
     * Score a Card and set it's correct E-Factor.
     */
    public static void scoreCard(Card card, int quality) {
        float qFactor = (MAX_QUALITY - quality);
        float newFactor = card.getEFactor() + (0.1f - qFactor * (0.08f + qFactor * 0.02f));
        if (newFactor < E_FACTOR_FLOOR) {
            newFactor = E_FACTOR_FLOOR;
        }
        card.setEFactor(newFactor);
    }

    public static void calcuateInterval(Card card) {
        if (card.getEFactor() < 3) {
            card.setCount(1);
        }
        int count = card.getCount();
        int interval = 1;
        if (count == 2) {
            interval = 6;
        } else if (count > 2) {
            interval = Math.round(card.getInterval() * card.getEFactor());
        }
        card.setInterval(interval);
    }


}
