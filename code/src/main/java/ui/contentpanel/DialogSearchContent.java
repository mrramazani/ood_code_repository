package ui.contentpanel;

import content.ContentCatalogue;

import javax.swing.*;
import java.awt.event.*;

public class DialogSearchContent extends JDialog {
    private JPanel contentPane;
    private JButton buttonSearch;
    private JButton buttonCancel;
    private JTextField searchField;

    public DialogSearchContent() {
        setSize(400, 200);
        setTitle("جستجوی محتوا");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        ContentCatalogue catalogue = new ContentCatalogue();
        catalogue.search(searchField.getText());
        // TODO: show content view
//        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
