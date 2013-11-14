package sk.th.pipifax.dto;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 11.11.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class Card {

    private float EFactor;
    private int count;
    private int interval;

    public float getEFactor() {
        return EFactor;
    }

    public void setEFactor(float EFactor) {
        this.EFactor = EFactor;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }


    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
