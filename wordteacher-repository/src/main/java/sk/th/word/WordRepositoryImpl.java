package sk.th.word;

import org.springframework.stereotype.Repository;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.UserWordEntity;
import sk.th.pipifax.entity.WordDbEntity;
import sk.th.pipifax.dto.WordDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class WordRepositoryImpl implements WordRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<WordDto> findAll(String currentUserName, LanguageCode currentLanguage) {
        Query query = entityManager.createQuery("select w from WordEntity w left join w.user u left join w.language l where l.code = :lang and u.username = :username ");
        query.setParameter("username", currentUserName);
        query.setParameter("lang", currentLanguage);
        List resultList = query.getResultList();
        return resultList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void importWords(List<WordDto> words, Language language) {
        for (WordDto word : words) {
            entityManager.merge(word);
        }
    }

    @Override
    public void updateWord(UserWordEntity currentWord) {
        entityManager.merge(currentWord);
    }

    @Override
    public WordDbEntity loadScheduledWord(String currentUserName, LanguageCode currentLanguage, Date learningDate) {

        String queryString = "select w from WordDbEntity w " +
                "left join w.userWords uw " +
                "left join w.tag t " +
                "left join t.userSet u " +
                //"left join uw.user u " +
                "left join t.language l " +
                "where " +
                "l.code = :lang and " +
                "u.username = :username and " +
                "(uw.nextRepetition is null or :currentDate > uw.nextRepetition) " +
                "order by uw.nextRepetition";
        TypedQuery<WordDbEntity> query = entityManager.createQuery(queryString, WordDbEntity.class);
        query.setMaxResults(1);
        query.setParameter("username", currentUserName);
        query.setParameter("lang", currentLanguage);
        query.setParameter("currentDate", learningDate);
        try {
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public UserWordEntity loadLearningData(String currentUserName, WordDbEntity wordDbEntity) {
        TypedQuery<UserWordEntity> query = entityManager.createQuery("select uw from UserWordEntity uw left join uw.user u where u.username = :username and uw.word = :word", UserWordEntity.class);
        query.setMaxResults(1);
        query.setParameter("username", currentUserName);
        query.setParameter("word", wordDbEntity);
        try {
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public WordDbEntity loadWordsWithLowQuality(String currentUserName, LanguageCode currentLanguage) {
        TypedQuery<WordDbEntity> query = entityManager.createQuery("select w from WordDbEntity w left join w.userWords uw left join w.tag t left join t.language l left join uw.user u where l.code = :lang and u.username = :username and uw.lastQuality < 4 order by uw.modified", WordDbEntity.class);
        query.setMaxResults(1);
        query.setParameter("username", currentUserName);
        query.setParameter("lang", currentLanguage);
        try {
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }
}
