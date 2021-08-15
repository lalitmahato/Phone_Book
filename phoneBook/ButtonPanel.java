package phoneBook;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel implements AppLayout {
    private GridBagConstraints layout;
    private JButton btnClear, btnSearch, btnAdd, btnRemove, btnUpdate;

    public ButtonPanel(){
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        btnClear = new JButton("Clear");
        btnUpdate = new JButton("Update");
        btnAdd = new JButton("Add");
        btnRemove = new JButton("Remove");
    }
    @Override
    public JPanel panelUI() {
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.ipadx = 150;
        layout.ipady = 90;

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        add(btnClear,layout);

        layout.gridx = 1;
        layout.gridy = 0;
        add(btnUpdate,layout);

        layout.gridx = 0;
        layout.gridy = 1;
        add(btnAdd,layout);

        layout.gridx = 1;
        layout.gridy = 1;
        add(btnRemove,layout);

        return this;
    }

    public JButton getBtnClear(){
        return btnClear;
    }

    public JButton getBtnAdd(){
        return  btnAdd;
    }

    public JButton getBtnRemove(){
        return btnRemove;
    }

    public JButton getBtnUpdate(){
        return btnUpdate;
    }
}
