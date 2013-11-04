package sk.th.word;

import org.springframework.stereotype.Repository;
import sk.th.Word;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WordRepositoryImpl implements WordRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Word> findAll() {
        List resultList = entityManager.createQuery("select w from Word w").getResultList();
        return resultList;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
