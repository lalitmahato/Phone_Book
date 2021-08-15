package phoneBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyApp extends JFrame{
    JMenuBar menuBar;
    DataPanel dataPanel;
    InfoPanel infoPanel;
    FileAs fileAs;
    ButtonPanel buttonPanel;
    userRegistration reg;
    MyApp self = this;
    public MyApp(){
        setVisible(true);
        setResizable(false);
        setTitle("Phone Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        dataPanel = new DataPanel() ;
        infoPanel = new InfoPanel();
        fileAs = new FileAs();
        buttonPanel = new ButtonPanel();
        reg = new userRegistration();
        setJMenuBar(getMenu());
        add(appLayout());
        pack();
        setLocationRelativeTo(null);
        addTableData();
        clearField();
        removeData();
        updateField();
        refreshTable();
        fileAs();
    }

    private JPanel appLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;

        layout.gridx=3;
        layout.gridy=3;

        layout.gridx=1;
        layout.gridy=0;
        mainPanel.add(infoPanel.panelUI(),layout);

        layout.gridx = 1;
        layout.gridy = 1;
        mainPanel.add(fileAs.panelUI(),layout);

        layout.gridx = 1;
        layout.gridy = 2;
        mainPanel.add(buttonPanel.panelUI(),layout);

        layout.weighty = 0.5;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight=3;
        mainPanel.add(dataPanel.panelUI(),layout);

        return mainPanel;
    }

    private JMenuBar getMenu(){
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem update = new JMenuItem("Update");
        JMenuItem add = new JMenuItem("Add");
        JMenuItem remove = new JMenuItem("Remove");
        JMenuItem about = new JMenuItem("About");
        fileMenu.add(exit);
        editMenu.add(clear);
        editMenu.add(update);
        editMenu.addSeparator();
        editMenu.add(add);
        editMenu.add(remove);
        helpMenu.add(about);
//        Open Parent Menus
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);
        helpMenu.setMnemonic(KeyEvent.VK_H);
//        Exit Button shortcut key and its action listener
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//        Search Button shortcut key and its action listener
        update.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        update.addActionListener(new ActionListener() {
            JButton updateBtn = buttonPanel.getBtnUpdate();
            @Override
            public void actionPerformed(ActionEvent e) {
               updateBtn.doClick();
            }
        });
//        Add Button shortcut key and its action listener
        add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,KeyEvent.CTRL_DOWN_MASK));

        return menuBar;
    }

    private void addTableData(){
        JButton addBtn = buttonPanel.getBtnAdd();
        JButton clearBtn = buttonPanel.getBtnClear();
        fileAs.getFsName().setSelected(true);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String firstName =  infoPanel.getFirstName().getText().trim();
                    String lastName = infoPanel.getLastName().getText().trim();
                    String phoneNumber = infoPanel.getPhoneNo().getText().trim();
                    String privateCheckBox = infoPanel.getPrivateCheckBox().isSelected() ? "Private" : "";


                    if(firstName.isEmpty() && lastName.isEmpty() && phoneNumber.isEmpty() || firstName.isEmpty() && lastName.isEmpty() || firstName.isEmpty() && phoneNumber.isEmpty() || lastName.isEmpty() && phoneNumber.isEmpty() ){
                        JOptionPane.showMessageDialog(self,"Please complete the above fields.","Warning",JOptionPane.WARNING_MESSAGE);
                    }else {
                        reg.insert(firstName,lastName,phoneNumber,privateCheckBox);
                        refreshTable();
                        JOptionPane.showMessageDialog(self,"You data has been Added.","Success",JOptionPane.INFORMATION_MESSAGE);
                        clearBtn.doClick();
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(self,"You must enter Phone Number In Numerical Format","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void fileAs(){
        JTable dataTable = dataPanel.getTable();
        JRadioButton fsName = fileAs.getFsName();
        JRadioButton sfName = fileAs.getSfName();

        fsName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataTable.moveColumn(1,0);
            }
        });

        sfName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataTable.moveColumn(1,0);
            }
        });
    }

    private void removeData(){
        JButton removeBtn = buttonPanel.getBtnRemove();
        DefaultTableModel model = dataPanel.getModel();
        JTable dataTable = dataPanel.getTable();

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(self, "Do you wanna delete the selected row", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                int selectedRow = dataTable.getSelectedRow();
                JButton clearBtn = buttonPanel.getBtnClear();
                if(confirm == JOptionPane.YES_OPTION){
                    try {
                        String phone = model.getValueAt(selectedRow,2).toString();
                        reg.removeData(phone);
                        refreshTable();
                        clearBtn.doClick();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(self, "No row selected. Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    return;
                }
            }

        });
    }

    private void updateField(){
        JTable dataTable = dataPanel.getTable();
        DefaultTableModel model = dataPanel.getModel();
        JButton clearBtn = buttonPanel.getBtnClear();
        JButton updateBtn = buttonPanel.getBtnUpdate();

        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                infoPanel.getFirstName().setText(model.getValueAt(selectedRow,0).toString());
                infoPanel.getLastName().setText(model.getValueAt(selectedRow,1).toString());
                infoPanel.getPhoneNo().setText(model.getValueAt(selectedRow,2).toString());
                infoPanel.getPrivateCheckBox().setSelected(model.getValueAt(selectedRow, 3).toString().equals("Private"));
            }

            @Override
            public void mousePressed(MouseEvent e) {

                int selectedRow = dataTable.getSelectedRow();
                infoPanel.getFirstName().setText(model.getValueAt(selectedRow, 0).toString());
                infoPanel.getLastName().setText(model.getValueAt(selectedRow, 1).toString());
                infoPanel.getPhoneNo().setText(model.getValueAt(selectedRow, 2).toString());
                infoPanel.getPrivateCheckBox().setSelected(model.getValueAt(selectedRow, 3).toString().equals("Private"));

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                try{
                    String firstName =  infoPanel.getFirstName().getText().trim();
                    String lastName = infoPanel.getLastName().getText().trim();
                    String phoneNumber = infoPanel.getPhoneNo().getText().trim();
                    String privateCheckBox = infoPanel.getPrivateCheckBox().isSelected() ? "Private" : "";

                    if(firstName.isEmpty() && lastName.isEmpty() && phoneNumber.isEmpty() || firstName.isEmpty() && lastName.isEmpty() || firstName.isEmpty() && phoneNumber.isEmpty() || lastName.isEmpty() && phoneNumber.isEmpty() ){
                        JOptionPane.showMessageDialog(self,"Please complete the above fields.","Warning",JOptionPane.WARNING_MESSAGE);
                    } else {
                        String S_NO = model.getValueAt(selectedRow,2).toString();
                        JOptionPane.showMessageDialog(self, "Data of " + firstName + " " + lastName + " is updated","Success",JOptionPane.INFORMATION_MESSAGE);
                        reg.updateField(S_NO,firstName,lastName,phoneNumber,privateCheckBox);
                        refreshTable();
                       clearBtn.doClick();
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(self,"You must enter Phone Number In Numerical Format","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void refreshTable(){
        //it helps to remove all table from JTable
        dataPanel.getModel().setNumRows(0);
        try {
            ResultSet resultSet = reg.get();

            while (resultSet.next()) {
                dataPanel.getModel().addRow(new Object[]{
                        //this helps to pull the data from sql
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("private"),
                });
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void clearField(){
        JButton clearBtn = buttonPanel.getBtnClear();
        JTable dataTable = dataPanel.getTable();
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoPanel.getFirstName().setText("");
                infoPanel.getLastName().setText("");
                infoPanel.getPhoneNo().setText("");
                infoPanel.getPrivateCheckBox().setSelected(false);
                dataTable.clearSelection();
            }
        });
    }

    public static void main(String[] args){
        new MyApp();
    }

}