package model.utilities.inputCheck;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BasicErrorTable extends Component {

    JTable table;
    String[] columnNames;
    ArrayList<Errors> errorsArrayList;

    public BasicErrorTable(ArrayList<Errors> errors, String[] columnNames) {
        this.errorsArrayList = errors;
        this.columnNames = columnNames;
        initialize();

    }

    private void initialize() {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setEnabled(false);
        customizeTable();
        fillTable(tableModel);
    }

    private void customizeTable() {
        setCellAlignment(table);
        setHeaderAlignment(table);
    }

    private void fillTable(DefaultTableModel tableModel) {

        Errors err;
        for (int i = 0; i < errorsArrayList.size(); i++) {
            err = errorsArrayList.get(i);
            Object[] error = {err.getRowNo(), err.getTaxon(), err.getSite(), err.errorValue, err.getErrorType()};
            tableModel.addRow(error);

        }
    }

    private void setCellAlignment(JTable table) {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        for (int column = 0; column < table.getColumnCount(); column++) {
            table.getColumnModel().getColumn(column).setCellRenderer(render);
        }
    }


    private void setHeaderAlignment(JTable table) {
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
    }

    public JTable getErrorTable() {
        return table;
    }
}
