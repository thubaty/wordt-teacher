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
        List resultList = entityManager.createQuery("select w from Language w").getResultList();
        return resultList;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
