package ui.contentpanel; /**
 * Created by hamid on 7/8/2015.
 */

import ui.contentpanel.DialogAddNewContent;
import ui.contentpanel.DialogCreateInterContentRelation;
import ui.contentpanel.DialogCreateKnowledgeRequirement;
import ui.contentpanel.DialogSearchContent;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by hamid on 7/8/2015.
 */
public class ContentPanel extends JFrame{
    public ContentPanel()
    {
        initUI();
    }

    private void initUI()
    {
        createMenuBar();

        setTitle("سامانه مدیریت دانش");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
                        DialogSearchContent sc = new DialogSearchContent();
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

        JMenuItem createInterContentRelation = new JMenuItem("ایجاد رابطه بین محتوایی", icon);
        createInterContentRelation .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        DialogCreateInterContentRelation dialog_icr = new DialogCreateInterContentRelation();
                        dialog_icr.setVisible(true);
                    }
                });

            }
        });

        contentManagement.add(addContent);
        contentManagement.add(searchContent);
        contentManagement.add(createKnowledgeReq);
        contentManagement.add(createInterContentRelation);

        menubar.add(contentManagement);

        setJMenuBar(menubar);
    }


}
