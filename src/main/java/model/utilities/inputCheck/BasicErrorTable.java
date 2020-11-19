package model.utilities.inputCheck;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BasicErrorTable extends Component {

    private JTable table;
    private final String[] columnNames;
    private final ArrayList<Errors> errorsArrayList;

    //Constructor takes ArrayList<Errors>, String[] as parameters
    public BasicErrorTable(ArrayList<Errors> errors, String[] columnNames) {
        this.errorsArrayList = errors;
        this.columnNames = columnNames;
        initialize();

    }

    //EFFECTS: initialize fields
    private void initialize() {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        customizeTable();
        fillTable(tableModel);
    }

    //EFFECTS: customize JTable
    private void customizeTable() {
        table.setEnabled(false);
        setCellAlignment(table);
        setHeaderAlignment(table);
    }

    //REQUIRES: JTable
    //MODIFIES: JTable
    //EFFECTS: setting cell alignment in JTable
    private void setCellAlignment(JTable table) {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        for (int column = 0; column < table.getColumnCount(); column++) {
            table.getColumnModel().getColumn(column).setCellRenderer(render);
        }
    }

    //REQUIRES: JTable
    //MODIFIES: JTable
    //EFFECTS: setting first row cells alignment in JTable
    private void setHeaderAlignment(JTable table) {
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
    }

    //REQUIRES: DefaultTableModel
    //MODIFIES: JTable
    //EFFECTS: fill in the JTable with errors found in form of Error instances
    private void fillTable(DefaultTableModel tableModel) {

        Errors err;
        for (Errors errors : errorsArrayList) {
            err = errors;
            Object[] error = {err.getRowNo(), err.getTaxon(), err.getSite(), err.getErrorValue(), err.getErrorType()};
            tableModel.addRow(error);
        }
    }

    //getters
    public JTable getErrorTable() {
        return table;
    }
}
