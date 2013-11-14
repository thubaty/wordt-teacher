package sk.th.pipifax.util;

import org.junit.Test;
import sk.th.pipifax.dto.Card;

public class SRSUtilTest {

    @Test
    public void testScoreCard() throws Exception {
        Card c = new Card();
        c.setCount(3);
        c.setEFactor(2f);
        c.setInterval(1);
        System.out.println(c.getEFactor());
        SRSUtil.scoreCard(c, 4);
        System.out.println(c.getEFactor());
    }

    @Test
    public void testCalcuateInterval() throws Exception {
        //SRSUtil.calcuateInterval();
    }
}
