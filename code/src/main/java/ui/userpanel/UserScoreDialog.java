package ui.userpanel;

import user.ActivityType;
import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class UserScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel usernameLbl;
    private JTable table;
    private User user;

    public UserScoreDialog(User user) {
        this.user = user;
        setContentPane(contentPane);
        setModal(true);
        setSize(500,500);
        setTitle("مشاهده امتیاز کاربر");
        getRootPane().setDefaultButton(buttonOK);
        usernameLbl.setText(user.getUsername());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
//        User search = userCatalogue.getUserByUsername(usernameLbl.getText());
//        if (search == null) {
//            JOptionPane.showMessageDialog(this, "کاربر با نام کاربری داده شده وجود ندارد.");
//        }
        DefaultTableModel tableModel = new DefaultTableModel(getUserScores(user), new String[] {"تاریخ","توضیحات"});
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
        // todo:how to update the ui to see the changes.
        this.update(this.getGraphics());
//        dispose();
    }

    private Object[][] getUserScores(User user) {
        UserCatalogue userCatalogue = new UserCatalogue();
        List<UserActivityLog> activityLogs = userCatalogue.getSpecializedLog(user.getUsername(), ActivityType.CHANGE_SCORE);
        Object[][] objects = new Object[activityLogs.size()][2];
        for (int i = 0; i < activityLogs.size(); i++) {
            objects[i][0] = activityLogs.get(i).getDate();
            objects[i][1] = activityLogs.get(i).getDetail();
        }
        return objects;
    }

    private void onCancel() {
        dispose();
    }
}
