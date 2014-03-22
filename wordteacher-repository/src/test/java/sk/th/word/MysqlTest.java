package sk.th.word;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sk.th.pipifax.entity.TestEntity;
import sk.th.pipifax.entity.UserWordEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-repositoryContext.xml")
public class MysqlTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testStore() throws Exception {
        TestEntity te = new TestEntity();
        te.setName("jehoooo");

        entityManager.persist(te);
        entityManager.flush();

        UserWordEntity uw = new UserWordEntity();
        uw.setInterval(1);
        entityManager.persist(uw);
        entityManager.flush();
    }
}
