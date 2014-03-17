package sk.th.pipifax.generator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.*;

public class SqlGenerator {

    public static final String USER_TABLE = "pp_user";
    public static final String WORD_TABLE = "pp_worddb";
    public static final String USERTAG_TABLE = "pp_usertag";
    public static final String USERWORD_TABLE = "pp_userword";
    public static final String TAG_TABLE = "pp_wordtag";
    public static final String LANGUAGE_TABLE = "pp_language";

    public static Workbook openWorkBook() throws IOException {
        URL resource = SqlGenerator.class.getClassLoader().getResource("masterData.xlsx");
        FileInputStream file = new FileInputStream(new File(resource.getFile()));
        Workbook workbook = new XSSFWorkbook(file);
        file.close();
        return workbook;
    }

    //todo validate empty tags
    public static void main(String[] args) throws IOException {
        Workbook workbook = openWorkBook();

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);
        Sheet tagSheet = workbook.getSheetAt(1);
        Sheet languageSheet = workbook.getSheetAt(2);
        Sheet userSheet = workbook.getSheetAt(3);
        Sheet userTagSheet = workbook.getSheetAt(4);

        List<ExcelWord> wordTags = parseWords(sheet);
        List<ExcelTag> excelTags = parseTags(tagSheet);
        List<ExcelLanguage> languages = parseLanguages(languageSheet);
        List<ExcelUser> users = parseUsers(userSheet);
        List<ExcelUserTag> userTags = parseUserTags(userTagSheet);

        Map languageIdMap = getLanguageIdMap(languages);
        Map tagIdMap = getTagIdMap(excelTags);
        Map userIdMap = getUserIdMap(users);

        //validation

        StringBuilder sb = new StringBuilder();
        sb.append("delete " + WORD_TABLE + ";\n");
        sb.append("delete " + TAG_TABLE + ";\n");
        sb.append("delete " + LANGUAGE_TABLE + ";\n");
        sb.append("delete " + USER_TABLE + ";\n");
        sb.append("delete " + USERTAG_TABLE + ";\n");

        for (ExcelLanguage language : languages) {
            String s = "insert into " + LANGUAGE_TABLE + " values (" + language.getId() + ",'" + language.getName() + "');\n";
            sb.append(s);
        }

        for (ExcelTag excelTag : excelTags) {
            if (languageIdMap.get(excelTag.getLanguage()) == null) {
                throw new IllegalStateException("Language " + excelTag.getLanguage() + " does not exist (tag: " + excelTag.getName() + ")");
            }
            String s = "insert into " + TAG_TABLE + " values (" + excelTag.getId() + ",'" + excelTag.getName() + "'," + languageIdMap.get(excelTag.getLanguage()) + ");\n";
            sb.append(s);
        }

        int wordCounter = 0;
        for (ExcelWord wordTag : wordTags) {
            if (wordTag.getId() == null) {
                throw new IllegalStateException("Id not found (word: " + wordTag.getSlovak() + ")");
            }
            if (wordTag.getTranslation() == null) {
                throw new IllegalStateException("Translation not found (word: " + wordTag.getId() + ")");
            }
            if (wordTag.getSlovak() == null) {
                throw new IllegalStateException("Slovak not found (word: " + wordTag.getId() + ")");
            }
            if (wordTag.getTag() == null) {
                throw new IllegalStateException("Tag not found (word: " + wordTag.getId() + ")");
            }
            if (tagIdMap.get(wordTag.getTag()) == null) {
                throw new IllegalStateException("Tag " + wordTag.getTag() + " does not exist (word: " + wordTag.getId() + ")");
            }
            String s = "insert into " + WORD_TABLE + " values (" + wordTag.getId() + ",'" + wordTag.getSlovak() + "','" + wordTag.getTranslation() + "'," + tagIdMap.get(wordTag.getTag()) + ");\n";
            sb.append(s);
            wordCounter++;
        }

        for (ExcelUser user : users) {
            String s = "insert into " + USER_TABLE + " values (" + user.getId() + ",'" + user.getUsername() + "','" + user.getPassword() + "');\n";
            sb.append(s);
        }

        for (ExcelUserTag userTag : userTags) {
            if (userIdMap.get(userTag.getUser()) == null) {
                throw new IllegalStateException("User " + userTag.getUser() + " does not exist (user/tag mapping)");
            }
            if (tagIdMap.get(userTag.getTag()) == null) {
                throw new IllegalStateException("Tag " + userTag.getTag() + " does not exist (user/tag mapping)");
            }
            String s = "insert into " + USERTAG_TABLE + " values (" + userIdMap.get(userTag.getUser()) + "," + tagIdMap.get(userTag.getTag()) + ");\n";
            sb.append(s);
        }

        sb.append("delete from " + USERWORD_TABLE + " where word_id not in (select id from pp_worddb);\n");
        sb.append("delete from " + USERTAG_TABLE + " where tag_id not in (select id from pp_wordtag);\n");
        writeToOutputFile(sb.toString());
        System.out.println("Done [" +wordCounter+ " master words parsed]");
    }

    public static void writeToOutputFile(String output) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("/C:/Users/tohy/IdeaProjects/wordteacher/wordteacher-dbmantain/src/main/resources/masterData.sql")));
            String outText = output.toString();
            out.write(outText);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Map getTagIdMap(List<ExcelTag> excelTags) {
        Map<String, Long> m = new HashMap();
        for (ExcelTag tag : excelTags) {
            m.put(tag.getName(), tag.getId());
        }
        return m;
    }

    private static Map getUserIdMap(List<ExcelUser> excelTags) {
        Map<String, Long> m = new HashMap();
        for (ExcelUser user : excelTags) {
            m.put(user.getUsername(), user.getId());
        }
        return m;
    }

    private static Map getLanguageIdMap(List<ExcelLanguage> languages) {
        Map<String, Long> m = new HashMap();
        for (ExcelLanguage language : languages) {
            m.put(language.getName(), language.getId());
        }
        return m;
    }

    private static List<ExcelUserTag> parseUserTags(Sheet userTagSheet) {
        List<ExcelUserTag> list = new ArrayList<ExcelUserTag>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = userTagSheet.iterator();
        //skip first like
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell userNameCell = row.getCell(0);
            String name = userNameCell.getStringCellValue();
            Cell tagCell = row.getCell(1);
            String tag = tagCell.getStringCellValue();
            list.add(new ExcelUserTag(name, tag));
        }
        return list;
    }

    private static List<ExcelUser> parseUsers(Sheet userSheet) {
        List<ExcelUser> list = new ArrayList<ExcelUser>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = userSheet.iterator();
        //skip first like
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell idCell = row.getCell(0);
            Long id = new Double(idCell.getNumericCellValue()).longValue();
            Cell userNameCell = row.getCell(1);
            String name = userNameCell.getStringCellValue();
            Cell passwordCell = row.getCell(2);
            String pass = passwordCell.getStringCellValue();
            list.add(new ExcelUser(id, name, pass));
        }
        return list;
    }

    private static List<ExcelLanguage> parseLanguages(Sheet languageSheet) {
        List<ExcelLanguage> list = new ArrayList<ExcelLanguage>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = languageSheet.iterator();
        //skip first like
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell idCell = row.getCell(0);
            Long id = new Double(idCell.getNumericCellValue()).longValue();
            Cell slovakCell = row.getCell(1);
            String name = slovakCell.getStringCellValue();
            list.add(new ExcelLanguage(id, name));
        }
        return list;
    }

    public static List<ExcelWord> parseWords(Sheet sheet) {

        List<ExcelWord> list = new ArrayList<ExcelWord>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        //skip first like
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        Set<String> tagSet = new HashSet<String>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell idCell = row.getCell(0);
            Long id = new Double(idCell.getNumericCellValue()).longValue();
            Cell slovakCell = row.getCell(1);
            String slovak = slovakCell.getStringCellValue();
            Cell translationCell = row.getCell(2);
            String translation = translationCell.getStringCellValue();
            Cell tagCell = row.getCell(3);
            String tag = tagCell.getStringCellValue();
            tagSet.add(tag);

            list.add(new ExcelWord(id, slovak, translation, tag));
        }

        return list;
    }

    public static List<ExcelTag> parseTags(Sheet sheet) {
        List<ExcelTag> list = new ArrayList<ExcelTag>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        //skip first like
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell idCell = row.getCell(0);
            Long id = new Double(idCell.getNumericCellValue()).longValue();
            Cell slovakCell = row.getCell(1);
            String tag = slovakCell.getStringCellValue();
            Cell translationCell = row.getCell(2);
            String language = translationCell.getStringCellValue();
            Cell tagCell = row.getCell(3);
            list.add(new ExcelTag(id, tag, language));
        }
        return list;
    }
}
