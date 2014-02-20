package sk.th.pipifax;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.11.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pp_language")
public class Language implements Serializable {

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private LanguageCode code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageCode getCode() {
        return code;
    }

    public void setCode(LanguageCode code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (code != language.code) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
