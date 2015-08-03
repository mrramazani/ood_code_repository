package default_;

import ui.userpanel.AdminPanel;
import ui.userpanel.UserPanel;
import user.User;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLoginDialog extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;

    private UserCatalogue userCatalogue;

    public UserLoginDialog() {
        setContentPane(contentPane);
//        setModal(true);
        setSize(400,400);
        getRootPane().setDefaultButton(buttonOK);
        userCatalogue = new UserCatalogue();

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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
// addSource your code here
        User authenticatedUser = userCatalogue.authenticate(textField1.getText(), passwordField1.getText());
        if (authenticatedUser != null) {
            if (!authenticatedUser.isAdmin()) {
                UserPanel userPanel = new UserPanel(authenticatedUser);
                userPanel.setVisible(true);
            }
            else {
                AdminPanel adminPanel = new AdminPanel(authenticatedUser);
                adminPanel.setVisible(true);
            }
            this.setVisible(false);
        }
        else {
            // show error dialog
            getContentPane().add(new TextArea("Authentication Error"));

        }
    }

    private void onCancel() {
// addSource your code here if necessary
        dispose();
    }

    }
