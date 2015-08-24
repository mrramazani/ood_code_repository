package ui.userpanel;

import ui.contentpanel.ContentChangeDialog;
import ui.contentpanel.SourceContentsReportDialog;
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

        final JMenuItem deleteUser = new JMenuItem("حذف کاربر");
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                    DeleteUserDialog deleteUserDialog = new DeleteUserDialog();
                    deleteUserDialog.setVisible(true);
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
        JMenuItem userActivity = new JMenuItem("مشاهده فعالیت های یک کاربر");
        userActivity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        UserActivityDialog activityDialog = new UserActivityDialog();
                        activityDialog.setVisible(true);
                    }
                });
            }
        });
        adminMenu.add(addUser);
        adminMenu.add(deleteUser);
        adminMenu.add(changeScore);
        adminMenu.add(userActivity);
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

        final JMenuItem reportSourceContent = new JMenuItem("گزارش از محتوای مختلف یک منبع");
        reportSourceContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                    SourceContentsReportDialog sourcereportdialog = new SourceContentsReportDialog();
                    sourcereportdialog.setVisible(true);
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
                    ContentChangeDialog dialog = new ContentChangeDialog();
                    dialog.setVisible(true);
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
                    AssignFormDialog assignFormDialog = new AssignFormDialog();
                    assignFormDialog.setVisible(true);
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
                        RaiseRateDialog raiseRateDialog = new RaiseRateDialog();
                        raiseRateDialog.setVisible(true);
                    }
                });
            }
        });

        sourceMenu.add(addSource);
        sourceMenu.add(reportSourceContent);
        sourceMenu.add(contentChange);
        adminMenu.add(assignRole);
        adminMenu.add(assignRaiseRate);

        getMenubar().add(adminMenu);
        getMenubar().add(sourceMenu);
    }

}
