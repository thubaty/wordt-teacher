package sk.th.pipifax;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.th.pipifax.entity.TagEntity;
import sk.th.pipifax.entity.WordDbEntity;
import sk.th.word.WordDbRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-repositoryContext.xml")
public class UserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindAll() throws Exception {
        List<TagEntity> tomas = userRepository.getTagsForUser("tomas");
        Assert.assertNotNull(tomas);
        Assert.assertEquals(1, tomas.size());
    }
}
