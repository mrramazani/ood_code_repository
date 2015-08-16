package ui.contentpanel; /**
 * Created by hamid on 7/8/2015.
 */

import content.Content;
import content.ContentCatalogue;
import user.Role;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * Created by hamid on 7/8/2015.
 */
public class ContentPanel extends JFrame{

    private User user;

    public ContentPanel()
    {
        initUI();
    }

    public ContentPanel(User user) throws HeadlessException {
        this.user = user;
        initUI();
    }

    private void initUI()
    {
        createMenuBar();

        setTitle("پنل مدیریت محتوا");
        setSize(800, 600);
        GridLayout gridLayout = new GridLayout(6,6, 10, 15);
        setLayout(gridLayout);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ContentCatalogue contentCatalogue = new ContentCatalogue();
        final List<Content> contents = contentCatalogue.listForRole(Role.REGULAR);
        if (user.getRole().equals(Role.MODERATOR)) {
            contents.addAll(contentCatalogue.listForRole(Role.MODERATOR));
        }
        JLabel[] contentLabels = new JLabel[contents.size()];
        for (int i = 0; i < contentLabels.length; i++) {
            contentLabels[i] = new JLabel(contents.get(i).getName());
            contentLabels[i].setBackground(new Color(66, 239, 245));
            contentLabels[i].setOpaque(true);
            contentLabels[i].setMinimumSize(new Dimension(50, 50));
            contentLabels[i].setPreferredSize(new Dimension(50, 50));
            contentLabels[i].setMaximumSize(new Dimension(50, 50));
            final int finalI = i;
            contentLabels[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ViewContentDialog dialog = new ViewContentDialog(contents.get(finalI), user);
                    dialog.setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            this.add(contentLabels[i]);

        }
//        JTable contentTable = new JTable(10, 5);
//        TableColumn col = new TableColumn()
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("فایل");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("خروج", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("خروج از سامانه");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(eMenuItem);
        menubar.add(file);

        JMenu contentManagement = new JMenu("مدیریت محتوا");
        contentManagement.setMnemonic(KeyEvent.VK_C);

        JMenuItem addContent = new JMenuItem("اضافه کردن محتوا", icon);
        addContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        DialogAddNewContent anc = new DialogAddNewContent();
                        anc.setVisible(true);
                    }
                });
            }
        });

        JMenuItem searchContent = new JMenuItem("جستجو محتوا", icon);
        searchContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        DialogSearchContent sc = new DialogSearchContent(user);
                        sc.setVisible(true);
                    }
                });


            }
        });

        JMenuItem createKnowledgeReq = new JMenuItem("ایجاد یک نیازمندی دانشی", icon);
        createKnowledgeReq .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        DialogCreateKnowledgeRequirement dialog_cnr = new DialogCreateKnowledgeRequirement();
                        dialog_cnr.setVisible(true);
                    }
                });

            }
        });

        contentManagement.add(addContent);
        contentManagement.add(searchContent);
        contentManagement.add(createKnowledgeReq);
//        contentManagement.add(createInterContentRelation);

        menubar.add(contentManagement);

        setJMenuBar(menubar);
    }


}
