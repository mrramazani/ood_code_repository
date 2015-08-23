package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import content.ContentChangeLog;
import content.ContentLogType;
import user.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

public class EvaluateContentDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField score;
    private User user;
    private Content content;

    public EvaluateContentDialog(User user, Content content) {
        initUI();
        this.user = user;
        this.content = content;
    }

    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500,500);
        setTitle("ارزیابی محتوا");

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
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        double temp = content.getAverageRating() * content.getRatings().size();
        content.getRatings().add(Double.parseDouble(score.getText()));
        content.setAverageRating((temp + Double.parseDouble(score.getText())) / content.getRatings().size());
        contentCatalogue.editContent(content);
        userCatalogue.addUserActivity(new UserActivityLog(user, ActivityType.EVALUATE, new Date(), "ثبت امتیاز ارزیابی محتوا: " + content.getName()));
        final List<ContentChangeLog> specificLog = contentCatalogue.getSpecificLog(content, ContentLogType.CREATE);
        final User user1 = specificLog.get(0).getUser();
        UserActivityLog log = new UserActivityLog(user1, ActivityType.CHANGE_SCORE, new Date(), "تغییر امتیاز به مقدار" + score.getText() 
                + "برای ایجاد محتوای " + content.getName());
        Employee employee = new Employee(user1);
        employee.setOverallScore(employee.getOverallScore() + Double.parseDouble(score.getText()));
        userCatalogue.addUserActivity(log);
        userCatalogue.editUser(employee);
        JOptionPane.showConfirmDialog(this, "امتیاز شما با موفقیت ثبت شد.");

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
