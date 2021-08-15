package phoneBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class DataPanel extends JPanel implements AppLayout {
    private JTable table;

    public DataPanel() {
        DefaultTableModel model = new DefaultTableModel();
        String[] headers = {"First Name","Last Name","Phone No","Private"};
        model.setColumnIdentifiers(headers);
        table = new JTable(model);

    }

    @Override
    public JPanel panelUI() {
        setBackground(new Color(0,0,0,0));
        table.setSelectionBackground(Color.pink);
        table.setSelectionForeground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Name"));
        table.setShowGrid(true);
        table.setTableHeader(null);
        JScrollPane scrollPane =new JScrollPane(table);
        add(scrollPane);

        return this;
    }

    public DefaultTableModel getModel(){
        return (DefaultTableModel) getTable().getModel();
    }

    public  JTable getTable(){
        return table;
    }
}
