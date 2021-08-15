package phoneBook;

import javax.swing.*;
import java.awt.*;

public class FileAs extends JPanel implements AppLayout {
    JRadioButton FsName, SfName;
    ButtonGroup bgGroup;

    public FileAs() {
        setBorder(BorderFactory.createTitledBorder("File As"));
        FsName = new JRadioButton("Forename,Surname");
        SfName = new JRadioButton("Surname,Forename");
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridLayout(2,0,50,30));
        add(FsName);
        add(SfName);
        ButtonGroup  choose = new ButtonGroup();
        choose.add(FsName);
        choose.add(SfName);

        return this;
    }

    public JRadioButton getFsName(){
        return FsName;
    }

    public JRadioButton getSfName(){
        return SfName;
    }

    public ButtonGroup getBgGroup(){
        return bgGroup;
    }
}



