package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import source.Source;
import source.SourceCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class CategorizeContentDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField;
    private JLabel contentName;
    private Content content;

    public CategorizeContentDialog(Content content) {
        this.content = content;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 500);
        setTitle("نسبت دادن محتوا به منبع");
        contentName.setText(this.content.getName());
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        SourceCatalogue sourceCatalogue = new SourceCatalogue();
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        List<Source> search = sourceCatalogue.search(textField.getText());
        if (search.size() == 0) {
            JOptionPane.showMessageDialog(this, "منبع با این نام موجود نیست.");
        }
        else {
            Source src = search.get(0);
            if (content.getSource().getId().equals(src.getId()))
                JOptionPane.showMessageDialog(this, "این منبع قبلا به این محتوا نسبت داده شده است.");
            else {
                content.setSource(src);
                contentCatalogue.editContent(content);
            }
            dispose();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
