package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import user.ActivityType;
import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class ContentEvaluationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel contLbl;
    private JTextField score;
    private JTextField textField1;
//    private JLabel nameLbl;
    private Content content;
    private User user;

    public ContentEvaluationDialog() {
        createUI();
    }

    public ContentEvaluationDialog(Content content, User user) {
        this.content = content;
        this.user = user;
        createUI();
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
        textField1.setText(content.getName());
        textField1.setEnabled(false);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void onOK() {
        UserCatalogue userCatalogue = new UserCatalogue();
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        double temp = content.getAverageRating() * content.getNumOfRatings();
        content.setNumOfRatings(content.getNumOfRatings() + 1);
        content.setAverageRating((temp + Double.parseDouble(score.getText())) / content.getNumOfRatings());
        contentCatalogue.updateContent(content);
        userCatalogue.addUserActivity(new UserActivityLog(user, ActivityType.EVALUATE, new Date(), "ثبت امتیاز ارزیابی محتوا: " + content.getName()));
        JOptionPane.showConfirmDialog(this, "امتیاز شما با موفقیت ثبت شد.");
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
