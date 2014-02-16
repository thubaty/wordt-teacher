package sk.th.pipifax.util;

import org.junit.Test;
import sk.th.pipifax.entity.RepetitionMode;
import sk.th.pipifax.dto.WordDto;

public class SRSUtilTest {


    @Test
    public void testScoreCard() throws Exception {

        WordDto w = new WordDto();
        w.setEFactor(SRSUtil.INITIAL_E_FACTOR);
        w.setCount(0);
        w.setMode(RepetitionMode.LEARNING);

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());
    }

    @Test
    public void testScore2() throws Exception {

        WordDto w = new WordDto();
        w.setEFactor(SRSUtil.INITIAL_E_FACTOR);
        w.setCount(0);
        w.setMode(RepetitionMode.LEARNING);

        w = SRSUtil.repetition(w, SRSUtil.HARD);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w.setMode(RepetitionMode.QA);

        w = SRSUtil.repetition(w, SRSUtil.MEDIUM);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.MEDIUM);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.MEDIUM);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());

        w = SRSUtil.repetition(w, SRSUtil.CORRECT);
        System.out.println("e:" + w.getEFactor() + ", i:" + w.getInterval());
    }
}
