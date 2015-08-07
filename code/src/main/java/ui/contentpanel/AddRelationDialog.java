package ui.contentpanel;

import content.Content;
import user.User;

import javax.swing.*;
import java.awt.event.*;

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
        setSize(400,600);
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
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
