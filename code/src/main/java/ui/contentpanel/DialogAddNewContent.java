package ui.contentpanel;

import com.mongodb.MongoClient;
import content.Content;
import content.ContentCatalogue;
import content.RelationShipType;
import org.mongodb.morphia.Morphia;
import source.Source;
import source.SourceCatalogue;
import user.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
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
    private JTextField srcField;
    private JButton upldBtn;
    private JCheckBox obsolete;
    private JComboBox access;
    private Content content;
    private User user;


    private ContentCatalogue contentCatalogue;

    public DialogAddNewContent() {

        initUI();
    }

    public DialogAddNewContent(Content content, User user) {
        // TODO: add another field to content: version, and increase it when
        this.content = content;
        this.user = user;
        initUI();
        TitleTextField.setText(content.getName());
        TitleTextField.setEnabled(false);
        TitleTextField.setEditable(false);
        TextTextArea.setText(content.getText());
        FilenameTextField.setText(content.getFiles());
        StringBuilder builder = new StringBuilder();
        for (String tag: content.getTags()) {
            builder.append(tag).append(",");
        }
        String tags = builder.toString();
        LabelsTextField.setText(tags);
    }

    private void initUI() {
        contentCatalogue = new ContentCatalogue();
        setSize(400, 600);
        setTitle("افزودن محتوای جدید");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(registerButton);
        for (Role x: Role.values())
            access.addItem(x);

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

        upldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showDialog(fileChooser,"Open/Save");
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    FilenameTextField.setText(file.getAbsolutePath());
                }
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
        SourceCatalogue sourceCatalogue = new SourceCatalogue();
        List<Source> sources = sourceCatalogue.search(srcField.getText());
        if (sources.size() == 0)
            JOptionPane.showMessageDialog(this, "منبع با این نام موجود نمی باشد.");
        content.setDate(new Date());
        content.setName(TitleTextField.getText());
        content.setText(TextTextArea.getText());
        content.setFiles(FilenameTextField.getText());
        if (contentCatalogue.isCopy(filenameLabel.getText()))
            JOptionPane.showMessageDialog(this, "این محتوا کپی شده است.");
        content.setSource(sources.get(0));
        String[] tags = LabelsTextField.getText().split(",");
        content.setTags(Arrays.asList(tags));
        content.setObsolete(obsolete.isSelected());
        content.setAccessRole((Role)access.getSelectedItem());
        contentCatalogue.addContent(content);

        UserCatalogue userCatalogue = new UserCatalogue();
        userCatalogue.addUserActivity(new UserActivityLog(user, ActivityType.CREATE, new Date(), "ایجاد محتوای جدید: " + content.getName()));
    }

    private void onCancel() {
        //TODO
    }

}
