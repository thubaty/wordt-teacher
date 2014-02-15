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


    public static Workbook openWorkBook() throws IOException {
        URL resource = SqlGenerator.class.getClassLoader().getResource("masterData.xlsx");
        FileInputStream file = new FileInputStream(new File(resource.getFile()));
        Workbook workbook = new XSSFWorkbook(file);
        file.close();
        return workbook;
    }

    public static void main(String[] args) throws IOException {
        Workbook workbook = openWorkBook();

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);
        Sheet tagSheet = workbook.getSheetAt(1);
        Sheet languageSheet = workbook.getSheetAt(2);

        List<ExcelWord> wordTags = parseWords(sheet);
        List<ExcelTag> excelTags = parseTags(tagSheet);
        List<ExcelLanguage> languages = parseLanguages(languageSheet);

        Map languageIdMap = getLanguageIdMap(languages);
        Map tagIdMap = getTagIdMap(excelTags);

        //validation

        StringBuilder sb = new StringBuilder();
        sb.append("delete from worddb;\n");
        sb.append("delete from wordtag;\n");
        sb.append("delete from language;\n");

        for (ExcelLanguage language : languages) {
            String s = "insert into language values (" + language.getId() + ","+language.getName() + ")\n";
            sb.append(s);
        }

        for (ExcelTag excelTag : excelTags) {
            String s = "insert into wordtag values (" + excelTag.getId() + ","+excelTag.getName() + ","+languageIdMap.get(excelTag.getLanguage()) + ")\n";
            sb.append(s);
        }

        for (ExcelWord wordTag : wordTags) {
            String s = "insert into worddb values (" + wordTag.getId() + ","+wordTag.getSlovak() + ","+wordTag.getTranslation()+ ","+tagIdMap.get(wordTag.getTag()) + ")\n";
            sb.append(s);
        }

        sb.append("delete from userword where word_id not in worddb");
        sb.append("delete from usertag where tag_id not in tag");
        System.out.println(sb.toString());
    }

    private static Map getTagIdMap(List<ExcelTag> excelTags) {
        Map<String, Long> m = new HashMap();
        for (ExcelTag tag : excelTags) {
            m.put(tag.getName(), tag.getId());
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
            Cell translationCell = row.getCell(2);
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
