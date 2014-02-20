package sk.th.word;

import org.springframework.stereotype.Repository;
import sk.th.pipifax.JpaBaseRepository;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.entity.WordDbEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class WordDbRepositoryImpl extends JpaBaseRepository<WordDbEntity, Long> implements WordDbRepository {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<WordDbEntity> findAll(String currentUserName, LanguageCode currentLanguage) {
        Query query = entityManager.createQuery("select w from WordDbEntity w left join w.userWords uw left join w.tag t left join t.language l left join t.userSet u where l.code = :lang and u.username = :username");
        query.setParameter("lang", currentLanguage);
        query.setParameter("username", currentUserName);
        return query.getResultList();
    }
}
