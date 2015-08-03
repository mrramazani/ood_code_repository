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

//    @Autowired
    private MongoClient mongoClient;

//    @Autowired
    private Morphia morphia;

//    @Autowired
    private ContentCatalogue contentCatalogue;

    public DialogAddNewContent() {

//        Set classesToMap = new HashSet();
//        classesToMap.addSource(Content.class);
//        classesToMap.addSource(User.class);
//        classesToMap.addSource(Comment.class);
//        classesToMap.addSource(Relationship.class);
//        morphia = new Morphia(classesToMap);
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

    private void addButtonAction() {

        Content c = new Content();
        c.setDate(new Date());
        c.setName(TitleTextField.getText());
        c.setText(TextTextArea.getText());
        c.setFiles(Collections.<String>singletonList(FilenameTextField.getText()));
        String[] tags = LabelsTextField.getText().split(",");
        c.setTags(Arrays.asList(tags));
        contentCatalogue.addContent(c);
    }

    private void onCancel() {
        //TODO
    }

}
