package phoneBook;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements AppLayout {
    private JTextField firstName, lastName, phoneNo;
    private JCheckBox  privateCheckBox;

    public InfoPanel(){
        setBorder(BorderFactory.createTitledBorder("Info"));
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        phoneNo = new JTextField(20);
        privateCheckBox = new JCheckBox("Private");


    }
    @Override
    public JPanel panelUI() {
        setLayout(new GridLayout(4,2, 1,1));
        add(new JLabel("First Name"));
        add(firstName);
        add(new JLabel("Last Name"));
        add(lastName);
        add(new JLabel("Phone No."));
        add(phoneNo);
        add(privateCheckBox);

        return this;
    }
    public JTextField getFirstName(){
        return firstName;
    }

    public JTextField getLastName(){
        return lastName;
    }

    public JTextField getPhoneNo(){
        return phoneNo;
    }

    public JCheckBox getPrivateCheckBox(){
        return privateCheckBox;
    }
}

