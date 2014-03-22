package sk.th.word;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class MysqlRepositoryImpl implements MysqlRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String testSelect() {
        Query query = entityManager.createNativeQuery("select name from firsttable");
        Object singleResult = query.getSingleResult();
        return (String) singleResult;
    }
}
