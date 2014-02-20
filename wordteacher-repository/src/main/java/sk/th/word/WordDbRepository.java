package sk.th.word;

import sk.th.pipifax.BaseRepository;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.entity.WordDbEntity;

import java.util.List;

public interface WordDbRepository extends BaseRepository<WordDbEntity, Long> {

    List<WordDbEntity> findAll(String currentUserName, LanguageCode currentLanguage);

}
