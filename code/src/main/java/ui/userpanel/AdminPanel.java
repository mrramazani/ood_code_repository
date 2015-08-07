package ui.userpanel;

import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/27/2015
 */
public class AdminPanel extends MainPanel{


    public AdminPanel() throws HeadlessException {
        super();
        createUI();
    }

    public AdminPanel(User admin) throws HeadlessException {
        super(admin);
        setCurrentUser(admin);
        createUI();
    }

    public void createUI() {
        super.createUI();
//        JMenuBar menubar = new JMenuBar();

        JMenu adminMenu = new JMenu("مدیریت کاربران");
        JMenu sourceMenu = new JMenu("مدیریت منابع");
        adminMenu.setMnemonic(KeyEvent.VK_C);

        JMenuItem addUser = new JMenuItem("ایجاد کاربر");
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        CreateUserDialog anc = new CreateUserDialog();
                        anc.setVisible(true);
                    }
                });
            }
        });

        JMenuItem deleteUser = new JMenuItem("حذف کاربر");
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        // TODO

//                        DialogSearchContent sc = new DialogSearchContent();
//                        sc.setVisible(true);
                    }
                });


            }
        });
        JMenuItem changeScore = new JMenuItem("تغییر امتیاز کارمند");
        changeScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ChangeUserScoreDialog dialog = new ChangeUserScoreDialog();
                        dialog.setVisible(true);
                    }
                });
            }
        });
        adminMenu.add(addUser);
        adminMenu.add(deleteUser);
        adminMenu.add(changeScore);
        JMenuItem addSource = new JMenuItem("ایجاد منبع");
        addSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        CreateResourceDialog resourceDialog = new CreateResourceDialog(getCurrentUser());
                        resourceDialog.setVisible(true);
                    }
                });


            }
        });
        final JMenuItem categorizeSource = new JMenuItem("تغییر دسته بندی منبع");
        categorizeSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ChangeContentCategoryDialog categoryDialog = new ChangeContentCategoryDialog();
                        categoryDialog.setVisible(true);
                    }
                });
            }
        });

        final JMenuItem categorizeContent = new JMenuItem("تغییر دسته بندی محتوا در منبع");
        categorizeContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //TODO
//                        ChangeContentCategoryDialog categoryDialog = new ChangeContentCategoryDialog();
//                        categoryDialog.setVisible(true);
                    }
                });
            }
        });

        final JMenuItem contentChange = new JMenuItem("گرفتن گزارش از تغییرات محتوا");
        contentChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //TODO
//                        ChangeContentCategoryDialog categoryDialog = new ChangeContentCategoryDialog();
//                        categoryDialog.setVisible(true);
                    }
                });
            }
        });

        final JMenuItem assignRole = new JMenuItem("انتصاب نقش");
        assignRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //TODO
//                        ChangeContentCategoryDialog categoryDialog = new ChangeContentCategoryDialog();
//                        categoryDialog.setVisible(true);
                    }
                });
            }
        });

        final JMenuItem assignRaiseRate = new JMenuItem("تعیین میزان افزایش حقوق کاربر");
        assignRaiseRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //TODO
//                        ChangeContentCategoryDialog categoryDialog = new ChangeContentCategoryDialog();
//                        categoryDialog.setVisible(true);
                    }
                });
            }
        });

        sourceMenu.add(addSource);
        sourceMenu.add(categorizeSource);
        sourceMenu.add(categorizeContent);
        sourceMenu.add(contentChange);
        adminMenu.add(assignRole);
        adminMenu.add(assignRaiseRate);

        getMenubar().add(adminMenu);
        getMenubar().add(sourceMenu);
    }

}
