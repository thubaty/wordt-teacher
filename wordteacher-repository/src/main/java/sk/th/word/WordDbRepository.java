package sk.th.word;

import sk.th.pipifax.BaseRepository;
import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.entity.WordDbEntity;

import java.util.List;

public interface WordDbRepository extends BaseRepository<WordDbEntity, Long> {

    List<WordDbEntity> findAll(String currentUserName, LanguagCode currentLanguage);

}
