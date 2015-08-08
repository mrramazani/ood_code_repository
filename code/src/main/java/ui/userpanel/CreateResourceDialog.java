package ui.userpanel;

import source.Source;
import source.SourceCatalogue;
import user.User;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class CreateResourceDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField srcname;
    private JTextField path;
    private JButton pathBtn;
    private User user;

    public CreateResourceDialog(User user) {
        this.user = user;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        pathBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showDialog(fileChooser,"Open/Save");
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    path.setText(file.getAbsolutePath());
                }
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
        SourceCatalogue sourceCatalogue = new SourceCatalogue();
        Source src = new Source(srcname.getText(), path.getText(), user);
        sourceCatalogue.addSource(src);
        JOptionPane.showConfirmDialog(this, "منبع با موفقیت ایجاد شد.", "", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void onCancel() {
// addSource your code here if necessary
        dispose();
    }

}
