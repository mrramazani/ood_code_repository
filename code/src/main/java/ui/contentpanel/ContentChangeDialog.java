package ui.contentpanel;

import content.Content;
import content.ContentCatalogue;
import content.ContentChangeLog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ContentChangeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField;
    private JTable table;

    public ContentChangeDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("گزارش از تغییرات محتوا");
        setSize(500, 500);
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

    private void onOK() {
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        final Content search = contentCatalogue.search(textField.getText());
        if (search == null)
            JOptionPane.showMessageDialog(this, "محتوا با نام مورد نظر وجود ندارد.");
        else {
            final List<ContentChangeLog> log = contentCatalogue.getLog(textField.getText());
            Object[][] logs = new Object[log.size()][3];
            for (int i = 0; i < log.size(); i++) {
                logs[i][0] = new Object();
                logs[i][0] = log.get(i).getUser().getUsername();
                logs[i][1] = new Object();
                logs[i][1] = log.get(i).getDate();
                logs[i][2] = new Object();
                logs[i][2] = log.get(i).getContentLogType();
            }
            DefaultTableModel tableModel = new DefaultTableModel(logs, new String[] {"کاربر عامل","تاریخ ","نوع فعالیت"});
            table.setModel(tableModel);
            tableModel.fireTableDataChanged();
        }
    }

    private void onCancel() {
        dispose();
    }
}
