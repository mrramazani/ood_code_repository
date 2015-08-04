package ui.contentpanel;

import content.Content;
import user.User;

import javax.swing.*;
import java.awt.event.*;

public class ContentEvaluationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel contLbl;
    private JTextField textField2;
    private JLabel nameLbl;
    private JLabel msgLabel;
    private Content content;
    private User user;

    public ContentEvaluationDialog() {
        createUI();
    }

    public ContentEvaluationDialog(Content content, User user) {
        this.content = content;
        this.user = user;
        createUI();
        nameLbl.setText(content.getName());
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
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void onOK() {
        msgLabel.setText("امتیاز شما با موفقیت ثبت شد.");
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
