package sk.th.word;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.entity.WordDbEntity;
import sk.th.pipifax.dto.WordDto;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-repositoryContext.xml")
public class WordRepositoryImplTest {

    @Autowired
    WordRepository wordRepository;

    @Test
    public void testFindAll() throws Exception {
        List<WordDto> wordBag = wordRepository.findAll("anonymousUser", LanguageCode.EN);
        Assert.assertNotNull(wordBag);
        Assert.assertEquals(2, wordBag.size());
    }

    @Test
    public void testNextWord() throws Exception {
        WordDbEntity entity = wordRepository.loadScheduledWord("anonymousUser", LanguageCode.EN, new Date());
        System.out.println(entity);
    }

    @Test
    public void testLoadLearningData() throws Exception {
        WordDbEntity entity1 = wordRepository.loadScheduledWord("tomas", LanguageCode.DE, new Date());
        WordDbEntity entity2 = wordRepository.loadWordsWithLowQuality("anonymousUser", LanguageCode.DE);
        System.out.println(entity1);
    }
}
