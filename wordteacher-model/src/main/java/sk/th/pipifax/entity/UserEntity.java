package sk.th.pipifax.entity;

import sk.th.pipifax.Language;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    Long id;

    @ManyToMany
    @JoinTable(name = "Usertag", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    Set<TagEntity> tagSet;

    String username;

    @Transient
    private List<Language> languageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<TagEntity> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<TagEntity> tagSet) {
        this.tagSet = tagSet;
    }
}
