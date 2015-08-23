package ui.userpanel;

import user.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class CreateUserDialog extends JFrame {
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
    private JComboBox comboBox1;

//    public AddUserDialog(User user, boolean editable) {
//        AddUserDialog dialog = new AddUserDialog();
////
//    }

    public CreateUserDialog() {
        setContentPane(contentPane);
        setSize(500, 500);
        getRootPane().setDefaultButton(buttonOK);
        comboBox1.addItem(Role.MODERATOR);
        comboBox1.addItem(Role.REGULAR);
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
       nameField.setEnabled(false);
       lastNameField.setText(user.getLastName());
       lastNameField.setEnabled(false);
       passwordField.setText(user.getPassword());
       passwordField.setEnabled(editable);
       usernameField.setText(user.getUsername());
       usernameField.setEnabled(false);
       isAdmin.setSelected(user.isAdmin());
       isAdmin.setEnabled(false);
       StringBuilder stringBuilder = new StringBuilder();
       for (String expertise : user.getExpertise()) {
           stringBuilder.append(expertise).append(",");
       }
       expertiseField.setText(stringBuilder.toString());
       expertiseField.setEnabled(true);
       comboBox1.setSelectedItem(user.getRole());
       comboBox1.setEditable(false);
       comboBox1.setEnabled(false);
   }
    private void onOK() {
        User user = new User(usernameField.getText(), passwordField.getText(), emailField.getText(),
                nameField.getText(), lastNameField.getText());
        user.setAdmin(isAdmin.isSelected());
        user.setRole((Role) comboBox1.getSelectedItem());
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
