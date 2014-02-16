package sk.th.pipifax.entity;

import sk.th.pipifax.Language;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tohy on 14.02.14.
 */
@Entity
@Table(name="pp_WordTag")
public class TagEntity {

    @Id
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "pp_Usertag", joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    Set<UserEntity> userSet;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<UserEntity> userSet) {
        this.userSet = userSet;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
