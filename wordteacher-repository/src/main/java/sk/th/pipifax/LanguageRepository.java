package sk.th.pipifax;

import sk.th.pipifax.Language;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 10.11.13
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */

public interface LanguageRepository {

    List<Language> getAllLanguages();

    List<Language> getAllLanguagesForUser(String userName);
}
