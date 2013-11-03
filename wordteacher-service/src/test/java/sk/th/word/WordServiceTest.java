package sk.th.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.10.13
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext.xml")
public class WordServiceTest {

    @Autowired
    private WordService wordService;

    @Test
    public void testFirst1() throws Exception {
        List<String> allWords = wordService.findAllWords();
        Assert.assertNotNull(allWords);
        System.out.println(allWords);
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
