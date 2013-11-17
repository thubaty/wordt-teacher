package sk.th.word;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.entity.WordEntity;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-repositoryContext.xml")
public class WordRepositoryImplTest {

    @Autowired
    WordRepository wordRepository;

    @Test
    public void testFindAll() throws Exception {
        List<WordEntity> wordBag = wordRepository.findAll("anonymousUser", LanguagCode.EN);
        Assert.assertNotNull(wordBag);
        Assert.assertEquals(2, wordBag.size());
    }

    @Test
    public void testNextWord() throws Exception {
        List<WordEntity> wordEntities = wordRepository.loadLearnCandidates("anonymousUser", LanguagCode.EN);
        System.out.println(wordEntities.size());

    }
}
