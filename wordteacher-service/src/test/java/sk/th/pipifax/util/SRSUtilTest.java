package sk.th.pipifax.util;

import org.junit.Test;
import sk.th.pipifax.dto.Card;

public class SRSUtilTest {

    @Test
    public void testScoreCard() throws Exception {
        float newEfactor = SRSUtil.scoreCard(2.5f, 5);
        int newInterval = SRSUtil.calcuateInterval(2.5f, 1, 1);
        System.out.println(newEfactor + " rep in " + newInterval);
        newEfactor = SRSUtil.scoreCard(newEfactor, 5);
        newInterval = SRSUtil.calcuateInterval(newEfactor, newInterval, 2);
        System.out.println(newEfactor + " rep in " + newInterval);
        newEfactor = SRSUtil.scoreCard(newEfactor, 5);
        newInterval = SRSUtil.calcuateInterval(newEfactor, newInterval, 3);
        System.out.println(newEfactor + " rep in " + newInterval);
    }

    @Test
    public void testCalcuateInterval() throws Exception {
        int newInterval = SRSUtil.calcuateInterval(1.3f, 1, 1);


        System.out.println(newInterval);
    }
}
