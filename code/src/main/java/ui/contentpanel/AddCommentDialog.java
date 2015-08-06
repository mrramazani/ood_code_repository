package ui.contentpanel;

import content.*;
import user.ActivityType;
import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class AddCommentDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;
    private JTextArea comment;
    private Content content;
    private User user;

    public AddCommentDialog() {
        createUI();
    }

    public AddCommentDialog(Content content, User user) {
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void onOK() {
        Comment cmt = new Comment(comment.getText(), user, new Date(), content);
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        contentCatalogue.addComment(cmt);
        UserCatalogue userCatalogue = new UserCatalogue();
        userCatalogue.addUserActivity(new UserActivityLog(user, ActivityType.COMMENT, new Date(), "ثبت نظر برای محتوای " + content.getName()));
        contentCatalogue.log(new ContentChangeLog(content, ChangeType.COMMENT, new Date(), user));
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
