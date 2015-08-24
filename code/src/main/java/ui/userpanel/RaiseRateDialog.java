package ui.userpanel;

import user.UserCatalogue;

import javax.swing.*;
import java.awt.event.*;

public class RaiseRateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField;

    public RaiseRateDialog() {
        setContentPane(contentPane);
        setModal(true);
        setSize(500, 300);
        setTitle("تعیین میزان افزایش حقوق هر کاربر به ازای هر امتیاز");
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

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

    private void onOK() {
        UserCatalogue userCatalogue = new UserCatalogue();
        int anInt = Integer.parseInt(textField.getText());
        if (anInt < 0)
            JOptionPane.showMessageDialog(this, "میزان افزایش حقوق نباید منفی باشد.");
        else{
            userCatalogue.changeRaiseRate(anInt);
            JOptionPane.showMessageDialog(this, "میزان افزایش حقوق به مقدار " + anInt +" ثبت شد");
            dispose();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
