package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.UserRepository;
import sk.th.pipifax.entity.UserEntity;
import sk.th.pipifax.entity.WordEntity;
import sk.th.pipifax.util.SecurityUtil;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import java.util.ArrayList;
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
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WordEntity> findAllWords(String currentUserName, LanguagCode currentLanguage) {
        return wordRepository.findAll(currentUserName, currentLanguage);
    }

    @Override
    public List<WordEntity> parseWords(String words) throws InvalidFormatException {
        Assert.notNull(words);
        ArrayList<WordEntity> ret = new ArrayList<WordEntity>();
        String[] rows = words.split("\n");
        for (String row : rows) {
            if (StringUtils.isEmpty(row)) {
                throw new InvalidFormatException("empty row");
            }
            String[] columns = row.split("-");
            if (columns.length < 2) {
                throw new InvalidFormatException(row);
            }
            if (StringUtils.isEmpty(columns[1].trim())) {
                throw new InvalidFormatException(row);
            }
            WordEntity w = new WordEntity(columns[0], columns[1]);
            ret.add(w);
        }
        return ret;
    }

    @Override
    @Transactional
    public void importWords(List<WordEntity> words, Language language) {
        String currentUserName = SecurityUtil.getCurrentUserName();
        UserEntity userByUsername = userRepository.findUserByUsername(currentUserName);
        for (WordEntity word : words) {
            word.setUser(userByUsername);
            word.setLanguage(language);
        }
        wordRepository.importWords(words, language);
    }
}
