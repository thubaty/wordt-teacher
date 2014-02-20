package sk.th.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.LanguageCode;

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
        Long count = wordService.countAllWords("", LanguageCode.EN);
        Assert.assertNotNull(count);
    }


}
