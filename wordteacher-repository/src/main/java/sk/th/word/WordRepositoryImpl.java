package sk.th.word;

import org.springframework.stereotype.Repository;
import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.WordEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WordRepositoryImpl implements WordRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<WordEntity> findAll(String currentUserName, LanguagCode currentLanguage) {
        Query query = entityManager.createQuery("select w from WordEntity w left join w.user u left join w.language l where l.code = :lang and u.username = :username ");
        query.setParameter("username", currentUserName);
        query.setParameter("lang", currentLanguage);
        List resultList = query.getResultList();
        return resultList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void importWords(List<WordEntity> words, Language language) {
        for (WordEntity word : words) {
            entityManager.merge(word);
        }
    }

    @Override
    public void updateWord(WordEntity currentWord) {
        entityManager.merge(currentWord);
    }

    @Override
    public List<WordEntity> loadLearnCandidates(String currentUserName, LanguagCode currentLanguage) {
        TypedQuery<WordEntity> query = entityManager.createQuery("select w from WordEntity w left join w.user u left join w.language l where l.code = :lang and u.username = :username and current_timestamp > w.nextRepetition order by w.nextRepetition", WordEntity.class);
        query.setParameter("username", currentUserName);
        query.setParameter("lang", currentLanguage);
        return query.getResultList();
    }
}
