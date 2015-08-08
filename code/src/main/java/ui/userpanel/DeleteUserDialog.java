package ui.userpanel;

import user.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class DeleteUserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField username;

    public DeleteUserDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        UserCatalogue userCatalogue = new UserCatalogue();
        User user = userCatalogue.getUserByUsername(username.getText());
        if (null == user) {
            JOptionPane.showMessageDialog(this, "این کاربر وجود ندارد.", "خطا", JOptionPane.ERROR_MESSAGE);
        }
        else {
            userCatalogue.deleteUser(user);
            JOptionPane.showMessageDialog(this, "حذف  کاربر با موفقیت انجام شد.", "پیام", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
