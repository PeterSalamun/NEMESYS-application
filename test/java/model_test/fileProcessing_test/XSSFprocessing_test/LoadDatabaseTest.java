package model_test.fileProcessing_test.XSSFprocessing_test;

import model.computing.data.NematodesDatabase;
import model.fileProcessing.XSSFprocessing.LoadDatabase;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LoadDatabaseTest {

    private LoadDatabase testD;
    private XSSFWorkbook book;
    private NematodesDatabase n1, n2, n3;
    private NematodesDatabase[] data;
    private HashMap<String, NematodesDatabase> map;

    @Before
    public void setUp() {
        n1 = new NematodesDatabase("Strom", "B1", 5.2);
        n2 = new NematodesDatabase("Zviera", "O3", 0.2);
        n3 = new NematodesDatabase("Rastlina", "H5", 1.1);
        data = new NematodesDatabase[3];
        data[0] = n1;
        data[1] = n2;
        data[2] = n3;

        book = new XSSFWorkbook();
        fillWorkSheet();
        XSSFSheet s = book.getSheetAt(0);
        testD = new LoadDatabase(s);
    }

    private void checkSheet(XSSFSheet sheet) {
        for(int i = 0; i < sheet.getLastRowNum(); i++) {
            for( int j = 0; j < sheet.getRow(0).getLastCellNum(); j++ ) {
                System.out.print(sheet.getRow(i).getCell(j) + ", ");

            }
            System.out.println();
        }
    }

    @Test
    public void testGetDatabase() {
        map = testD.getDatabaseMap();
        NematodesDatabase t = map.get("Strom");
        assertEquals(map.size(), 3);
        assertEquals(t.getGuild(), "B1");
        assertEquals(t.getWeight(), 5.2, 0.1);
        assertEquals(t.getTaxaName(), "Strom");
    }

    private void fillWorkSheet() {
        XSSFSheet sheet = book.createSheet();
        for (int i = 0; i < 7; i++) {
            XSSFRow row = sheet.createRow(i);
            if (i == 0)
                fillheader("Taxa", "Guild", "Weight", row);
            else if (i - 1 < data.length)
                fillRow(data[i - 1], row);
        }
    }

    private void fillRow(NematodesDatabase n, XSSFRow row) {
        row.createCell(0).setCellValue(n.getTaxaName());
        row.createCell(1).setCellValue(n.getGuild());
        row.createCell(2).setCellValue(n.getWeight());
    }

    private void fillheader(String taxa, String guild, String weight, XSSFRow row) {
        row.createCell(0).setCellValue(taxa);
        row.createCell(1).setCellValue(guild);
        row.createCell(2).setCellValue(weight);
    }
}
