package ui.contentpanel;

import com.mongodb.MongoClient;
import content.Comment;
import content.Content;
import content.ContentCatalogue;
import content.Relationship;
import org.mongodb.morphia.Morphia;
import user.User;

import javax.swing.*;
import java.awt.event.*;
import java.net.UnknownHostException;
import java.util.*;

//@Service
public class DialogAddNewContent extends JDialog {
    private JPanel contentPane;
    private JButton registerButton;
    private JButton cancelButton;
    private JTextField TitleTextField;
    private JTextArea TextTextArea;
    private JTextField FilenameTextField;
    private JTextField LabelsTextField;
    private JLabel titleLabel;
    private JLabel textLabel;
    private JLabel filenameLabel;
    private JLabel labelsLabel;
    private Content content;
    private User user;


    private ContentCatalogue contentCatalogue;

    public DialogAddNewContent() {

        createUI();
    }

    public DialogAddNewContent(Content content, User user) {
        this.content = content;
        this.user = user;
        createUI();
        TitleTextField.setText(content.getName());
        TitleTextField.setEnabled(false);
        TextTextArea.setText(content.getText());
        FilenameTextField.setText(content.getFiles());
        StringBuilder builder = new StringBuilder();
        for (String tag: content.getTags()) {
            builder.append(tag).append(",");
        }
        String tags = builder.toString();
        LabelsTextField.setText(tags);
    }

    private void createUI() {
        contentCatalogue = new ContentCatalogue();
        setSize(400, 600);
        setTitle("افزودن محتوای جدید");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonAction();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void addButtonAction() {

        if (null == content)
            content = new Content();
        content.setDate(new Date());
        content.setName(TitleTextField.getText());
        content.setText(TextTextArea.getText());
        content.setFiles(FilenameTextField.getText());
        String[] tags = LabelsTextField.getText().split(",");
        content.setTags(Arrays.asList(tags));
        contentCatalogue.addContent(content);
    }

    private void onCancel() {
        //TODO
    }

}
