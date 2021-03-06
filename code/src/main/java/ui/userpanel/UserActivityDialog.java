package ui.userpanel;

import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class UserActivityDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField username;
    private JTable table;

    public UserActivityDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("گزارش از فعالیت های کاربر");
        setSize(500,500);
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

    private void onOK() {
        UserCatalogue userCatalogue = new UserCatalogue();
        final User userByUsername = userCatalogue.getUserByUsername(username.getText());
        if (userByUsername == null) {
            JOptionPane.showMessageDialog(this, "کاربر با این نام کاربری موجود نیست.");
        }
        else {
            final List<UserActivityLog> activities = userCatalogue.getActivities(username.getText());
            Object[][] logs = new Object[activities.size()][4];
            for (int i = 0; i < activities.size(); i++) {
                logs[i][0] = new Object();
                logs[i][0] = activities.get(i).getType();
                logs[i][1] = new Object();
                logs[i][1] = activities.get(i).getDate();
                logs[i][2] = new Object();
                logs[i][2] = activities.get(i).getDetail();
            }
            DefaultTableModel tableModel = new DefaultTableModel(logs, new String[] {"نوع فعالیت","تاریخ ","جزییات"});
            table.setModel(tableModel);
            tableModel.fireTableDataChanged();
        }
    }

    private void onCancel() {
        dispose();
    }
}
