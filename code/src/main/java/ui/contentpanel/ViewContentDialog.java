package ui.contentpanel;

import content.Comment;
import content.Content;
import content.ContentCatalogue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ViewContentDialog extends JFrame {
    private JPanel contentPane;
//    private JButton buttonOK;
//    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel textLabel;
    private JTextArea bodyArea;
    private JTextField fileAdr;
    private JButton upldBtn;
    private JLabel fileLabel;
    private JLabel tagLabel;
    private JTextField tagField;
    private JLabel ratingLabel;
    private JTextField rateField;
    private JLabel commentLbl;
    private JButton evalBtn;
    private JButton versionBtn;
    private JButton cmtBtn;
    private JButton relationBtn;
    private JTable table1;
    private Content content;

    public ViewContentDialog() {
        setContentPane(contentPane);
//        setModal(true);
        titleField.setText(content.getName());
        bodyArea.setText(content.getText());
        fileAdr.setText(content.getFiles().get(0));
        StringBuilder builder = new StringBuilder();
        for (String tag: content.getTags()) {
            builder.append(tag).append(",");
        }
        String tags = builder.toString();
        tagField.setText(tags);
        rateField.setText(String.valueOf(content.getAverageRating()));
        // TODO: set contetn of tabel;


        relationBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCreateInterContentRelation dialog = new DialogCreateInterContentRelation();
                dialog.setFirst(content);
                dialog.setVisible(true);
            }
        });
    }

    private Object[][] tableComments() {
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        List<Comment> comments = contentCatalogue.getCommentsOfContent(content.getName());
        Object[][] objects = new Object[comments.size()][3];
        for (int i = 0; i < comments.size(); i++) {
            objects[i][0] = comments.get(i).getDate();
            objects[i][1] = comments.get(i).getUser().getUsername();
            objects[i][2] = comments.get(i).getContent().getName();
        }
        return objects;
    }

}
