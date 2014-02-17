package sk.th.pipifax;

import org.springframework.stereotype.Repository;
import sk.th.pipifax.entity.TagEntity;
import sk.th.pipifax.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl extends JpaBaseRepository<UserEntity, Long> implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserEntity findUserByUsername(String userName) {
        TypedQuery<UserEntity> query = entityManager.createQuery("select u from UserEntity u where u.username = :username", UserEntity.class);
        query.setParameter("username", userName);
        return query.getSingleResult();
    }

    @Override
    public List<TagEntity> getTagsForUser(String userName) {
        Query query = entityManager.createQuery("select u.tagSet from UserEntity u where u.username = :username");
        query.setParameter("username", userName);
        return query.getResultList();
    }
}
