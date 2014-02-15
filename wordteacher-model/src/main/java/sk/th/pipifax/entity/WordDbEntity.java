package sk.th.pipifax.entity;

import sk.th.pipifax.Language;

import javax.persistence.*;

@Entity
@Table(name="WordDb")
public class WordDbEntity {

    @Id
    private Long id;
    private String slovak;
    private String translation;

    @ManyToOne
    @JoinColumn(name="tag_id")
    private TagEntity tag;

    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
