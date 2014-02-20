package sk.th.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.service.CodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repositoryContext.xml","classpath:servicesContext.xml","classpath:test-dbInfrastructure.xml"})
public class WordServiceImplTest {

    @Autowired
    WordService wordService;

    @Autowired
    CodeService codeService;

    @Test
    public void testFindAllWords() throws Exception {
        Long aLong = wordService.countAllWords("tomas", LanguageCode.DE);
        Assert.assertNotNull(aLong);
    }



}
