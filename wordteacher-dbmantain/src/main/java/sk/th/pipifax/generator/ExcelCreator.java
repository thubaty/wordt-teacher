package sk.th.pipifax.generator;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohy on 03.03.14.
 */
public class ExcelCreator {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        URL resource = SqlGenerator.class.getClassLoader().getResource("data.txt");
        FileInputStream file = new FileInputStream(new File(resource.getFile()));
        File f = new File("C:\\temp\\test.xlsx");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        String string = IOUtils.toString(file);
        List<Word> words = parseWords(string);
        int rowCounter = 1;
        int step = 0;
        for (Word word : words) {
            if (rowCounter%101 == 0) {
                step+=1;
            }
            Row row = sheet.createRow(rowCounter-1);
            Cell idCell = row.createCell(0);
            Cell slovakCell = row.createCell(1);
            Cell translationCell = row.createCell(2);
            Cell tagCell = row.createCell(3);
            idCell.setCellType(Cell.CELL_TYPE_NUMERIC);
            idCell.setCellValue(rowCounter);
            slovakCell.setCellType(Cell.CELL_TYPE_STRING);
            slovakCell.setCellValue(word.getSlovak());
            translationCell.setCellType(Cell.CELL_TYPE_STRING);
            translationCell.setCellValue(word.getTranslation());
            tagCell.setCellType(Cell.CELL_TYPE_STRING);
            tagCell.setCellValue("EnglishBasics" + step);
            rowCounter++;
        }
        workbook.write(new FileOutputStream(f));
        file.close();
    }

    public static List<Word> parseWords(String words) throws InvalidFormatException {
        Assert.notNull(words);
        List<String> invalidRows = new ArrayList<String>();
        List<Word> list = new ArrayList<Word>();
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
            Word w = new Word(columns[1], columns[0]);
            System.out.println("row " + w.getTranslation());
            list.add(w);
        }

        if (!invalidRows.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return list;
    }

    static class Word {
        private String slovak;
        private String translation;

        Word(String slovak, String translation) {
            this.slovak = slovak.replace("\n", "").replace("\r", "");
            this.translation = translation.replace("\n", "").replace("\r", "");
        }

        public String getSlovak() {
            return slovak;
        }

        public void setSlovak(String slovak) {
            this.slovak = slovak;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }
    }
}
