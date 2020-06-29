package Tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class dataDriven {

    public static void main(String[] args) throws IOException {


        //fileInputStream argument

        FileInputStream fis = new FileInputStream("D:\\QA\\Selenium_projects\\backend_tests_study\\src\\test\\java\\files\\datademo.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();

        for (int i=0; i<sheets; i++){
           XSSFSheet sheet = workbook.getSheetAt(i);
        }

    }
}
