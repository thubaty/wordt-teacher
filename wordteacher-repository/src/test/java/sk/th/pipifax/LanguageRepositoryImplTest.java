package sk.th.pipifax;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.entity.TagEntity;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-repositoryContext.xml")
public class LanguageRepositoryImplTest {

    @Autowired
    LanguageRepository languageRepository;

    @Test
    public void testFindAll() throws Exception {
        List<Language> allLanguages = languageRepository.getAllLanguages();
        Assert.assertNotNull(allLanguages);
        Assert.assertEquals(2, allLanguages.size());
    }

    @Test
    public void testFindAllLanguagesForUser() throws Exception {
        List<Language> allLanguages = languageRepository.getAllLanguagesForUser("tomas");
        Assert.assertNotNull(allLanguages);
        Assert.assertEquals(2, allLanguages.size());
    }
}
