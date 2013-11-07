package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import sk.th.Word;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    @Transactional(readOnly = true)
    public List<Word> findAllWords() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> parseWords(String words) {
        Assert.notNull(words);
        ArrayList<Word> ret = new ArrayList<Word>();
        String[] rows = words.split("\n");
        for (String row : rows) {
            String[] columns = row.split("-");
            if (columns.length < 2) {
                throw new RuntimeException(columns[0]);
            }

            Word w = new Word(columns[0], columns[1]);
            ret.add(w);
        }
        return ret;
    }

    @Override
    @Transactional
    public void importWords(List<Word> words) {
        wordRepository.importWords(words);
    }
}
