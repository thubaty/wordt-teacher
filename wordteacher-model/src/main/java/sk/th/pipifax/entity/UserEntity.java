package sk.th.pipifax.entity;

import sk.th.pipifax.Language;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    Long id;

    String username;

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
}
