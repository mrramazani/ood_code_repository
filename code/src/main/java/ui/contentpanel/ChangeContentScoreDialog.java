package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import sun.plugin2.gluegen.runtime.CPU;
import user.ActivityType;
import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class ChangeContentScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField score;
    private User user;
    private Content content;

    public ChangeContentScoreDialog(User user, Content content) {
        createUI();
        this.user = user;
        this.content = content;
    }

    private void createUI() {
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
        JOptionPane.showMessageDialog(this, "امتیاز شما با موفقیت ثبت شد.");
        dispose();
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
