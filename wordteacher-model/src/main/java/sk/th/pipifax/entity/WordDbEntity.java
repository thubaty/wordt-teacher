package sk.th.pipifax.entity;

import sk.th.pipifax.Language;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pp_WordDb")
public class WordDbEntity {

    @Id
    private Long id;
    private String slovak;
    private String translation;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    @OneToMany(mappedBy = "word")
    private List<UserWordEntity> userWords;

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
}
