package Tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scala.math.Numeric;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static org.apache.poi.ss.usermodel.CellType.*;

public class dataDriven {


    public static ArrayList<String> getData(String testcaseName, String sheetName) throws IOException {


        /*String sheetName = "testdata";
        String columnName = "Testcases";*/

        ArrayList a = new ArrayList<>();
        FileInputStream fis = new FileInputStream("D:\\QA\\Selenium_projects\\backend_tests_study\\src\\test\\java\\files\\datademo.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();
        try {
            for (int i = 0; i < sheets; i++) {
                if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                    XSSFSheet sheet = workbook.getSheetAt(i); //Sheet is a collection of rows

                    Iterator<Row> rows = sheet.iterator();
                    Row firstRow = rows.next();

                    Iterator<Cell> cell = firstRow.cellIterator(); //Row is a collections of cells
                    int k = 0;
                    int column = 0;


                    while (cell.hasNext()) {
                        Cell value = cell.next();
                        if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
                            column = k;
                        }
                        k++;
                    }
                    while (rows.hasNext()) {
                        Row r = rows.next();
                        if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                            Iterator<Cell> cv = r.cellIterator();

                            while (cv.hasNext()) {
                                Cell c = cv.next();
                                if (c.getCellType() == CellType.STRING) {
                                    a.add(c.getStringCellValue());
                                } else if (c.getCellType() == NUMERIC) {
                                    a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                                } else if (c.getCellType() == FORMULA) {
                                    String cellValue = String.valueOf(c.getNumericCellValue());
                                    a.add(cellValue);
                                    if (DateUtil.isCellDateFormatted(c)) {
                                        DateFormat df = new SimpleDateFormat("dd/MM/yy");
                                        Date date = c.getDateCellValue();
                                        cellValue = df.format(date);
                                    }
                                    a.add(cellValue);
                                } else if (c.getCellType() == BLANK) {
                                    a.add(" ");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Row or column doesn't exist");
        }
        return a;
    }
}
