package ui.userpanel;

import root.UserLoginDialog;
import ui.contentpanel.ContentPanel;
import user.User;
import user.UserCatalogue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/26/2015
 */
public class MainPanel extends JFrame {

    private JMenuBar menubar;
    private User currentUser;

    public MainPanel() throws HeadlessException {
        setTitle("سامانه مدیریت دانش");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public MainPanel(User user) {
        MainPanel panel = new MainPanel();
        panel.currentUser = user;
    }

    public void logout() {
        this.setVisible(false);
        UserCatalogue userCatalogue = new UserCatalogue();
        userCatalogue.logout(currentUser);
        UserLoginDialog loginDialog = new UserLoginDialog();
        loginDialog.setVisible(true);
    }

    public void createUI() {
        setSize(800, 600);
        menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");
        JMenu userMng = new JMenu("مدیریت پروفایل");
        userMng.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("خروج");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("خروج از سامانه");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        userMng.add(eMenuItem);

        JMenuItem viewProfile = new JMenuItem("مشاهده پروفایل");
        viewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        CreateUserDialog dialog = new CreateUserDialog();
                        dialog.initFields(currentUser, false);
                        dialog.setVisible(true);
                    }
                });
            }
        });
        userMng.add(viewProfile);

        JMenuItem editProfile = new JMenuItem("ویرایش پروفایل");
        editProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        CreateUserDialog dialog = new CreateUserDialog();
                        dialog.initFields(currentUser, true);
                        dialog.setVisible(true);
                    }
                });
            }
        });
        userMng.add(editProfile);

        JMenuItem viewScores = new JMenuItem("مشاهده ی ریز امتیازات");
        viewScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        UserScoreDialog userScoreDialog = new UserScoreDialog();
                        userScoreDialog.setVisible(true);
                    }
                });
            }
        });
        userMng.add(viewScores);

        menubar.add(userMng);

        JMenuItem contentPanel = new JMenuItem("ورورد به پنل مدیریت محتوا");
        contentPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ContentPanel mainPage = new ContentPanel(currentUser);
                        mainPage.setVisible(true);
                    }
                });

            }
        });

        JMenu contentMenu = new JMenu("مدیریت محتوا");
        contentMenu.add(contentPanel);

        menubar.add(contentMenu);
        setJMenuBar(menubar);
    }

    public JMenuBar getMenubar() {
        return menubar;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
