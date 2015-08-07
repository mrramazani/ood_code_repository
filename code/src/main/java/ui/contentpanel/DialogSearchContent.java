package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import user.User;

import javax.swing.*;
import java.awt.event.*;

public class DialogSearchContent extends JDialog {
    private JPanel contentPane;
    private JButton buttonSearch;
    private JButton buttonCancel;
    private JTextField searchField;
    private User user;

    public DialogSearchContent(User user) {
        initUI();
        this.user = user;
    }



    private void initUI() {
        setSize(400, 200);
        setTitle("جستجوی محتوا");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
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

    private void search() {
        ContentCatalogue catalogue = new ContentCatalogue();
        Content content = catalogue.search(searchField.getText());
        if (null != content) {
            ViewContentDialog viewContentDialog = new ViewContentDialog(content, user);
            viewContentDialog.setVisible(true);
            dispose();
        }
        else {
            JOptionPane.showConfirmDialog(this, "محتوای مورد نظر یافت نشد.","خطا", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
