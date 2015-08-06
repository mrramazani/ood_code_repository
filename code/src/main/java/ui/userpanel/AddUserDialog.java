package ui.userpanel;

import user.Employee;
import user.SysAdmin;
import user.User;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class AddUserDialog extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JCheckBox isAdmin;
    private JTextArea expertiseField;

//    public AddUserDialog(User user, boolean editable) {
//        AddUserDialog dialog = new AddUserDialog();
////
//    }

    public AddUserDialog() {
        setContentPane(contentPane);
        setSize(500, 500);
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

   public void initFields(User user, boolean editable) {
       emailField.setText(user.getEmail());
       emailField.setEnabled(editable);
       nameField.setText(user.getFirstName());
       nameField.setEnabled(editable);
       lastNameField.setText(user.getLastName());
       lastNameField.setEnabled(editable);
       passwordField.setText(user.getPassword());
       passwordField.setEnabled(editable);
       usernameField.setText(user.getUsername());
       usernameField.setEnabled(editable);
       isAdmin.setSelected(user.isAdmin());
       isAdmin.setEnabled(editable);
       expertiseField.setText(user.getExpertise().toString());
       expertiseField.setEnabled(editable);
   }
    private void onOK() {
        User user = new User(usernameField.getText(), passwordField.getText(), emailField.getText(),
                nameField.getText(), lastNameField.getText());
        user.setAdmin(isAdmin.isSelected());
        String[] split = expertiseField.getText().split(",");
        user.setExpertise(Arrays.asList(split));
        UserCatalogue userCatalogue = new UserCatalogue();
        userCatalogue.addUser(user);
        if (user.isAdmin()) {
            user = new SysAdmin();
        }
        else {
            user = new Employee(user);
        }
        userCatalogue.addUser(user);
        dispose();
    }

    private void onCancel() {
// addSource your code here if necessary
        dispose();
    }
}
