package sk.th.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.Word;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repositoryContext.xml","classpath:servicesContext.xml","classpath:test-dbInfrastructure.xml"})
public class WordServiceImplTest {

    @Autowired
    WordService wordService;

    @Test
    public void testFindAllWords() throws Exception {
        List<Word> allWords = wordService.findAllWords();
        Assert.assertNotNull(allWords);
    }

    @Test
    public void testStoreWords() throws Exception {
        List<Word> list = new ArrayList<Word>();
        list.add(new Word("eng", "slovak"));
        wordService.importWords(list);
    }

    @Test
    public void testParseWords() throws Exception {
        {
            String wordsText = "hello - ahoj";
            List<Word> words = wordService.parseWords(wordsText);
            Assert.assertNotNull(words);
            Assert.assertEquals(1, words.size());
            Assert.assertEquals("hello", words.get(0).getEnglish());
            Assert.assertEquals("ahoj", words.get(0).getSlovak());
        }

        {
            String wordsText = "hello - ahoj \n welcome - vitaj";
            List<Word> words = wordService.parseWords(wordsText);
            Assert.assertNotNull(words);
            Assert.assertEquals(2, words.size());
            Assert.assertEquals("hello", words.get(0).getEnglish());
            Assert.assertEquals("ahoj", words.get(0).getSlovak());
            Assert.assertEquals("welcome", words.get(1).getEnglish());
            Assert.assertEquals("vitaj", words.get(1).getSlovak());
        }
    }
}
