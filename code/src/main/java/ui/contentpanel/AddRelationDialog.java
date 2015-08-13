package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import content.RelationShipType;
import content.Relationship;
import user.ActivityType;
import user.User;
import user.UserActivityLog;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class AddRelationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField firstContent;
    private JTextField secondContent;
    private JComboBox comboBox1;
    private Content first;
    private Content second;
    private User user;

    public AddRelationDialog() {
        initUI();
    }

    private void initUI() {
        setSize(400, 600);
        setTitle("ایجاد یک رابطه ی بین محتوایی");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRelation();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        for (RelationShipType x: RelationShipType.values())
            comboBox1.addItem(x);
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

    public AddRelationDialog(Content first, User user) {
        this.first = first;
        this.user = user;
        initUI();
        firstContent.setText(first.getName());
        firstContent.setEnabled(false);
    }

    public Content getFirst() {
        return first;
    }

    public void setFirst(Content first) {
        this.first = first;
    }

    public Content getSecond() {
        return second;
    }

    public void setSecond(Content second) {
        this.second = second;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void addRelation() {
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        UserCatalogue userCatalogue = new UserCatalogue();
        Content content2 = contentCatalogue.search(secondContent.getText());
        if (content2 == null) {
            JOptionPane.showMessageDialog(this, "محتوای دوم وجود ندارد");
            dispose();
        }
        this.second = content2;
        contentCatalogue.addRelation(new Relationship(first, second, (RelationShipType)comboBox1.getSelectedItem()));
        userCatalogue.addUserActivity(new UserActivityLog(user, ActivityType.UPDATE, new Date(), "ایجاد رابطه برای محتواهای " + first.getName() + ", " + second.getName()));
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
