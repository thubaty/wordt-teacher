package sk.th.pipifax;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Language> getAllLanguages() {
        return entityManager.createQuery("select w from Language w").getResultList();
    }
}
