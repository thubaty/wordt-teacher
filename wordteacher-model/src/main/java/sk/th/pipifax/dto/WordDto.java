package sk.th.pipifax.dto;

import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.entity.RepetitionMode;
import sk.th.pipifax.entity.UserWordEntity;
import sk.th.pipifax.entity.WordDbEntity;

import java.sql.Timestamp;
import java.util.Date;


public class WordDto {

    private Long wordDbId;
    private Long userId;
    private Long userWordId;
    private String slovak;
    private String translation;
    private String tag;
    private LanguagCode language;
    private float EFactor;
    private int count;
    private int interval;
    private Timestamp modified;
    private Timestamp nextRepetition;
    private int lastQuality;
    private RepetitionMode mode;

    public WordDto() {
    }

    public WordDto(WordDbEntity word, UserWordEntity learningData, RepetitionMode repetitionMode) {
        wordDbId = word.getId();
        slovak = word.getSlovak();
        translation = word.getTranslation();
        tag = word.getTag().getName();
        language = word.getTag().getLanguage().getCode();
        mode = repetitionMode;
        userId = learningData.getUser().getId();
        userWordId = learningData.getId();
        EFactor = learningData.getEFactor();
        count = learningData.getCount();
        interval = learningData.getInterval();
        modified = learningData.getModified();
        nextRepetition = learningData.getNextRepetition();
        lastQuality = learningData.getLastQuality();

    }

    public String getSlovak() {
        return slovak;
    }

    public void setSlovak(String slovak) {
        this.slovak = slovak;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LanguagCode getLanguage() {
        return language;
    }

    public void setLanguage(LanguagCode language) {
        this.language = language;
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

    public Long getWordDbId() {
        return wordDbId;
    }

    public void setWordDbId(Long wordDbId) {
        this.wordDbId = wordDbId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserWordId() {
        return userWordId;
    }

    public void setUserWordId(Long userWordId) {
        this.userWordId = userWordId;
    }
}


