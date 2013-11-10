package sk.th.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.WordEntity;
import sk.th.pipifax.service.CodeService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repositoryContext.xml","classpath:servicesContext.xml","classpath:test-dbInfrastructure.xml"})
public class WordServiceImplTest {

    @Autowired
    WordService wordService;

    @Autowired
    CodeService codeService;

    @Test
    public void testFindAllWords() throws Exception {
        List<WordEntity> allWords = wordService.findAllWords("", LanguagCode.EN);
        Assert.assertNotNull(allWords);
    }

    @Test
    public void testStoreWords() throws Exception {
        List<WordEntity> list = new ArrayList<WordEntity>();
        list.add(new WordEntity("eng", "slovak"));
        Language language = codeService.getAllLanguages().get(0);
        wordService.importWords(list, language);
    }

    @Test
    public void testParseWords() throws Exception {
        {
            String wordsText = "hello - ahoj";
            List<WordEntity> words = wordService.parseWords(wordsText);
            Assert.assertNotNull(words);
            Assert.assertEquals(1, words.size());
            Assert.assertEquals("hello", words.get(0).getEnglish());
            Assert.assertEquals("ahoj", words.get(0).getSlovak());
        }

        {
            String wordsText = "hello - ahoj \n welcome - vitaj";
            List<WordEntity> words = wordService.parseWords(wordsText);
            Assert.assertNotNull(words);
            Assert.assertEquals(2, words.size());
            Assert.assertEquals("hello", words.get(0).getEnglish());
            Assert.assertEquals("ahoj", words.get(0).getSlovak());
            Assert.assertEquals("welcome", words.get(1).getEnglish());
            Assert.assertEquals("vitaj", words.get(1).getSlovak());
        }
    }
}
