package sk.th.pipifax.entity;

import org.springframework.util.Assert;
import sk.th.pipifax.Language;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Random;

@Entity
@Table(name="Word")
public class WordEntity {

    @Id
    private String slovak;

    private String english;

    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    private float EFactor;
    private int count;
    private int interval;
    private Timestamp modified;
    @Column(name = "next_repetition")
    private Timestamp nextRepetition;

    public WordEntity() {
    }

    public WordEntity(String english, String slovak) {
        Assert.notNull(english);
        Assert.notNull(slovak);
        this.slovak = slovak.trim();
        this.english = english.trim();
    }

    public String getSlovak() {
        return slovak;
    }

    public void setSlovak(String slovak) {
        this.slovak = slovak;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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
}


