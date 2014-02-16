package sk.th.pipifax.entity;

import org.springframework.util.Assert;
import sk.th.pipifax.Language;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="pp_userWord")
public class UserWordEntity {

    @Id
    private Long id;

    private String english;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    private float EFactor;
    private int count;
    private int interval;
    private Timestamp modified;
    @Column(name = "next_repetition")
    private Timestamp nextRepetition;

    @Column(name = "last_quality")
    private int lastQuality;

    @Transient
    private RepetitionMode mode;

    public UserWordEntity() {
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public float getEFactor() {
        return EFactor;
    }

    public void setEFactor(float EFactor) {
        this.EFactor = EFactor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getNextRepetition() {
        return nextRepetition;
    }

    public void setNextRepetition(Timestamp nextRepetition) {
        this.nextRepetition = nextRepetition;
    }

    public int getLastQuality() {
        return lastQuality;
    }

    public void setLastQuality(int lastQuality) {
        this.lastQuality = lastQuality;
    }

    public RepetitionMode getMode() {
        return mode;
    }

    public void setMode(RepetitionMode mode) {
        this.mode = mode;
    }
}


