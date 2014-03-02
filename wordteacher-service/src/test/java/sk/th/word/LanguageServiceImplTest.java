package sk.th.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.service.CodeService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repositoryContext.xml","classpath:servicesContext.xml","classpath:test-dbInfrastructure.xml"})
public class LanguageServiceImplTest {

    @Autowired
    LanguageService languageService;

    @Autowired
    CodeService codeService;

    @Test
    public void testFindAllWords() throws Exception {
        List<LanguageCode> anonymousUser = languageService.getAllLanguagesForUser("anonymousUser");
        System.out.println(anonymousUser.size());
    }



}
