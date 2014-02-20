package sk.th.pipifax;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Language> getAllLanguages() {
        return entityManager.createQuery("select w from Language w").getResultList();
    }

    @Override
    public List<Language> getAllLanguagesForUser(String userName) {
        Query query = entityManager.createQuery("select distinct l from UserEntity u left join u.tagSet t join t.language l where u.username = :userName");
        query.setParameter("userName", userName);
        return query.getResultList();
    }
}
