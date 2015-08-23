package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import content.ContentChangeLog;
import content.ContentLogType;
import user.ActivityType;
import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class AddNewVersionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea;
    private JTextField titleField;
    private JTextField fileField;
    private JButton uploadBtn;
    private JTextField tagsField;
    private JTextField versionField;
    private Content content;
    private User user;

    public void initUI() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("ایجاد نسخه جدید");
        setSize(500, 500);
        getRootPane().setDefaultButton(buttonOK);
        titleField.setText(content.getName());
        titleField.setEnabled(false);

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

        uploadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showDialog(fileChooser,"Open/Save");
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    fileField.setText(file.getAbsolutePath());
                }
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public AddNewVersionDialog(Content content, User user) {
        this.content = content;
        this.user = user;
        initUI();
    }

    private void onOK() {
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        UserCatalogue userCatalogue = new UserCatalogue();
        Content newContent = new Content();
        String[] tags = tagsField.getText().split(",");
        newContent.setTags(Arrays.asList(tags));
        newContent.setText(textArea.getText());
        newContent.setDate(new Date());
        newContent.setVersion(versionField.getText());
        newContent.setName(titleField.getText());
        newContent.setAccessRole(content.getAccessRole());
        newContent.setAverageRating(content.getAverageRating());
        newContent.setFiles(fileField.getText());
        newContent.setObsolete(false);
        content.setObsolete(true);
        contentCatalogue.addContent(newContent);
        contentCatalogue.editContent(content);
        userCatalogue.addUserActivity(new UserActivityLog(user, ActivityType.NEW_CONTENT_VERSION, new Date(), "ایجاد نسخه جدید برای محتوای " + content.getName()));
        contentCatalogue.log(new ContentChangeLog(content, ContentLogType.NEW_VERSION, new Date(), user));
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
