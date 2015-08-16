package ui.userpanel;

import user.Role;
import user.User;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;

public class AssignFormDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox;
    private JTextField textField;

    public AssignFormDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 500);
        setTitle("انتصاب نقش");
        comboBox.addItem(Role.MODERATOR);
        comboBox.addItem(Role.REGULAR);

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
        User user = userCatalogue.getUserByUsername(textField.getText());
        if (user != null) {
            user.setRole((Role) comboBox.getSelectedItem());
            userCatalogue.editUser(user);
            JOptionPane.showMessageDialog(this, "تغییر نقش کاربر " + user.getUsername() + " با موفقیت انجام شد.");
            dispose();
        }
        else
            JOptionPane.showMessageDialog(this, "کاربر با نام کاربری وارد شده وجود ندارد.");
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
