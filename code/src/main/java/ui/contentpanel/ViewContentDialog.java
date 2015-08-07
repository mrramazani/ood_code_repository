package ui.contentpanel;

import content.Comment;
import content.Content;
import content.ContentCatalogue;
import user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    private User user;

    public ViewContentDialog(final Content contentIn, User userIn) {
        setContentPane(contentPane);
//        setModal(true);
        this.content = contentIn;
        this.user = userIn;
        titleField.setText(content.getName());
        bodyArea.setText(content.getText());
        fileAdr.setText(content.getFiles());
        StringBuilder builder = new StringBuilder();
        for (String tag: content.getTags()) {
            builder.append(tag).append(",");
        }
        String tags = builder.toString();
        tagField.setText(tags);
        rateField.setText(String.valueOf(content.getAverageRating()));
        // TODO: set comments of table;


        // TODO: copy this part to add new content dialog
        upldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showDialog(fileChooser,"Open/Save");
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    fileAdr.setText(file.getAbsolutePath());
                }
            }
        });
        relationBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRelationDialog dialog = new AddRelationDialog(content, user);
                dialog.setUser(user);
                dialog.setVisible(true);
            }
        });
        versionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAddNewContent dialog = new DialogAddNewContent(content, user);
                dialog.setVisible(true);
            }
        });
        cmtBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCommentDialog dialog = new AddCommentDialog(content, user);
                dialog.setVisible(true);
            }
        });
        evalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvaluateContentDialog dialog = new EvaluateContentDialog(user, content);
                dialog.setVisible(true);
            }
        });
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
