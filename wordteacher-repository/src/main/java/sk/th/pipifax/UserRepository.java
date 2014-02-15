package sk.th.pipifax;

import sk.th.pipifax.entity.TagEntity;
import sk.th.pipifax.entity.UserEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 10.11.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository {

    UserEntity findUserByUsername(String userName);

    List<TagEntity> getTagsForUser(String username);

}
