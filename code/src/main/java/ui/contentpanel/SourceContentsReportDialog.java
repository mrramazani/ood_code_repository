package ui.contentpanel;

import content.ContentCatalogue;
import source.Source;
import source.SourceCatalogue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class SourceContentsReportDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField;
    private JTable table;

    public SourceContentsReportDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 500);
        setTitle("گزارش از محتواهای یک منبع");
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
        SourceCatalogue sourceCatalogue = new SourceCatalogue();
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        List<Source> search = sourceCatalogue.search(textField.getText());
        if (search.size() == 0)
            JOptionPane.showMessageDialog(this, "منبعی با نام موردنظر یافت نشد.");
        else if(search.size() > 1)
            JOptionPane.showMessageDialog(this, "منابع زیر موجودند، لطفا نام یکی از انها را وارد نمایید: " + search);
        else {
            Object[][] sourceContentViews = contentCatalogue.getSourceContentViews(search.get(0));
            DefaultTableModel tableModel = new DefaultTableModel(sourceContentViews, new String[] {"نام منبع","تاریخ ایجاد","تعداد بازدید"});
            table.setModel(tableModel);
            tableModel.fireTableDataChanged();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
