package ui.contentpanel;

import source.KnowledgeRequirement;
import source.SourceCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class CreateKnowledgeRequirementDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tags;
    private JTextArea body;
    private JTextField titleField;

    public CreateKnowledgeRequirementDialog() {
        setSize(400, 600);
        setTitle("ایجاد یک نیازمندی دانشی");
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
        SourceCatalogue catalogue = new SourceCatalogue();
        List<String> tagList = Arrays.asList(tags.getText().split(","));
        final KnowledgeRequirement knowledgeRequirement = new KnowledgeRequirement(titleField.getText(), body.getText(), tagList);
        catalogue.addKnowledgeReq(knowledgeRequirement);
// addSource your code here
        dispose();
    }

    private void onCancel() {
// addSource your code here if necessary
        dispose();
    }
}
