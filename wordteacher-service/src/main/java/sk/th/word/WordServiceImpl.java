package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.UserRepository;
import sk.th.pipifax.entity.RepetitionMode;
import sk.th.pipifax.entity.UserEntity;
import sk.th.pipifax.entity.UserWordEntity;
import sk.th.pipifax.entity.WordDbEntity;
import sk.th.pipifax.dto.WordDto;
import sk.th.pipifax.util.DateUtil;
import sk.th.pipifax.util.SRSUtil;
import sk.th.pipifax.util.SecurityUtil;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.10.13
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private WordDbRepository wordDbRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public Long countAllWords(String currentUserName, LanguageCode currentLanguage) {
        List<WordDbEntity> all = wordDbRepository.findAll(currentUserName, currentLanguage);
        return new Long(all.size());
    }

    @Override
    public List<WordDto> parseWords(String words) throws InvalidFormatException {
        Assert.notNull(words);
        ArrayList<WordDto> ret = new ArrayList<WordDto>();
        List<String> invalidRows = new ArrayList<String>();
        String[] rows = words.split("\n");
        int rowCounter = 0;
        for (String row : rows) {
            rowCounter++;
            if (StringUtils.isEmpty(row)) {
                invalidRows.add(rowCounter + ". empty row");
                continue;
            }
            String[] columns = row.split("-");
            if (columns.length < 2) {
                invalidRows.add(rowCounter + ". " + row);
                continue;
            }
            if (StringUtils.isEmpty(columns[1].trim())) {
                invalidRows.add(rowCounter + ". " + row);
                continue;
            }
            /*WordDto w = new WordDto(columns[0], columns[1]);
            ret.add(w);*/
        }

        if (!invalidRows.isEmpty()) {
            throw new InvalidFormatException(invalidRows);
        }

        return ret;
    }

    @Override
    @Transactional
    public void importWords(List<WordDto> words, Language language) {
        String currentUserName = SecurityUtil.getCurrentUserName();
        UserEntity userByUsername = userRepository.findUserByUsername(currentUserName);
        for (WordDto word : words) {
            /*word.setUser(userByUsername);
            word.setLanguage(language);*/
            word.setCount(0);
            word.setEFactor(SRSUtil.INITIAL_E_FACTOR);
            word.setNextRepetition(DateUtil.getCurrentDate());
        }
        wordRepository.importWords(words, language);
    }

    @Override
    @Transactional
    public void updateWord(WordDto currentWord) {
        WordDbEntity w = new WordDbEntity();
        w.setId(currentWord.getWordDbId());
        UserEntity u = new UserEntity();
        u.setId(currentWord.getUserId());

        UserEntity user = userRepository.findById(currentWord.getUserId());
        WordDbEntity word = wordDbRepository.findById(currentWord.getWordDbId());


        UserWordEntity userWordEntity = new UserWordEntity();
        userWordEntity.setWord(word);
        userWordEntity.setUser(user);
        userWordEntity.setId(currentWord.getUserWordId());
        userWordEntity.setCount(currentWord.getCount());
        userWordEntity.setEFactor(currentWord.getEFactor());
        userWordEntity.setInterval(currentWord.getInterval());
        userWordEntity.setLastQuality(currentWord.getLastQuality());
        userWordEntity.setModified(DateUtil.getCurrentDate());
        userWordEntity.setNextRepetition(currentWord.getNextRepetition());
        wordRepository.updateWord(userWordEntity);
    }

    @Override
    public WordDto loadNextWord(String currentUserName, LanguageCode currentLanguage) {
        WordDbEntity candidate = wordRepository.loadScheduledWords(currentUserName, currentLanguage);
        if (candidate == null) {
            candidate = wordRepository.loadWordsWithLowQuality(currentUserName, currentLanguage);
            if (candidate == null) {
                return null;
            } else {
                System.out.println("----word scheduled (quality assetment) ----");
                UserWordEntity learningData = wordRepository.loadLearningData(currentUserName, candidate);
                if (learningData == null) {
                    learningData = createDefaultLearningData(candidate);
                }
                return new WordDto(candidate, learningData, RepetitionMode.QA);
            }
        } else {
            System.out.println("----word scheduled----");
            UserWordEntity learningData = wordRepository.loadLearningData(currentUserName, candidate);
            if (learningData == null) {
                learningData = createDefaultLearningData(candidate);
            }
            return new WordDto(candidate, learningData, RepetitionMode.LEARNING);
        }
    }

    private UserWordEntity createDefaultLearningData(WordDbEntity candidate) {
        String currentUserName = SecurityUtil.getCurrentUserName();
        UserEntity userByUsername = userRepository.findUserByUsername(currentUserName);
        UserWordEntity w = new UserWordEntity();
        w.setUser(userByUsername);
        w.setCount(0);
        w.setEFactor(2.5f);
        w.setNextRepetition(new Timestamp(new Date().getTime()));
        w.setWord(candidate);
        return w;
    }
}
