package ui.userpanel;

import user.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class ChangeUserScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField score;
    private JTextField reason;
    private JTextField username;

    public ChangeUserScoreDialog() {
        setContentPane(contentPane);
        setModal(true);
        setSize(500,500);
        setTitle("تغییر امتیاز کاربر");
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

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        UserCatalogue userCatalogue = new UserCatalogue();
        User user = userCatalogue.getUserByUsername(username.getText());
        if (null == user) {
            JOptionPane.showConfirmDialog(this, "این کاربر وجود ندارد.", "خطا", JOptionPane.ERROR_MESSAGE);
        }
        else {
            UserActivityLog log = new UserActivityLog(user, ActivityType.CHANGE_SCORE, new Date(), "تغییر امتیاز به مقدار" + score.getText() + "به علت: "
                    + reason.getText());
            Employee employee = new Employee(user);
            employee.setOverallScore(employee.getOverallScore() + Double.parseDouble(score.getText()));
            userCatalogue.addUserActivity(log);
            userCatalogue.editUser(employee);
            JOptionPane.showConfirmDialog(this, "ثبت امتیاز با موفقیت انجام شد.", "پیام", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }
}
