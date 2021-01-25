package model_test.utilities_test.inputCheck_test;

import model.utilities.inputCheck.BasicErrorTable;
import model.utilities.inputCheck.Errors;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BasicErrorTableTest {

    BasicErrorTable testTable;
    String[] columnNames;
    ArrayList<Errors> errors;
    Errors e1, e2, e3;

    @Before
    public void setUp() {
        columnNames = new String[]{"column", "type", "taxon", "row", "value"};
        e1 = new Errors("a", "b", "c", "d", "e");
        e2 = new Errors("1", "2", "3", "4", "5");
        e3 = new Errors("/", "*", "-", "+", "0");
        errors = new ArrayList<>();
        errors.add(e1);
        errors.add(e2);
        errors.add(e3);

        testTable = new BasicErrorTable(errors, columnNames);
    }

    @Test
    public void testBasicErrorTable() {
        assertEquals(testTable.getErrorTable().getRowCount(), 3);
        assertEquals(testTable.getErrorTable().getColumnCount(), 5);

        assertEquals(testTable.getErrorTable().getColumnName(0), columnNames[0]);
        assertEquals(testTable.getErrorTable().getColumnName(1), columnNames[1]);
        assertEquals(testTable.getErrorTable().getColumnName(2), columnNames[2]);
        assertEquals(testTable.getErrorTable().getColumnName(3), columnNames[3]);
        assertEquals(testTable.getErrorTable().getColumnName(4), columnNames[4]);

    }

    @Test
    public void testTableCellContent() {
        JTable table = testTable.getErrorTable();

        assertEquals(table.getModel().getValueAt(0, 4), e1.getErrorType());
        assertEquals(table.getModel().getValueAt(0, 3), e1.getErrorValue());
        assertEquals(table.getModel().getValueAt(0, 2), e1.getSite());
        assertEquals(table.getModel().getValueAt(0, 1), e1.getTaxon());
        assertEquals(table.getModel().getValueAt(0, 0), e1.getRowNo());


        assertEquals(table.getModel().getValueAt(1, 4), e2.getErrorType());
        assertEquals(table.getModel().getValueAt(1, 3), e2.getErrorValue());
        assertEquals(table.getModel().getValueAt(1, 2), e2.getSite());
        assertEquals(table.getModel().getValueAt(1, 1), e2.getTaxon());
        assertEquals(table.getModel().getValueAt(1, 0), e2.getRowNo());

        assertEquals(table.getModel().getValueAt(2, 4), e3.getErrorType());
        assertEquals(table.getModel().getValueAt(2, 3), e3.getErrorValue());
        assertEquals(table.getModel().getValueAt(2, 2), e3.getSite());
        assertEquals(table.getModel().getValueAt(2, 1), e3.getTaxon());
        assertEquals(table.getModel().getValueAt(2, 0), e3.getRowNo());

    }
}
