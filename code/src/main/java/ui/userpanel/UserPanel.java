package ui.userpanel;

import ui.contentpanel.ContentPanel;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/25/2015
 */
public class UserPanel extends MainPanel{
//    private JButton contenPanelBtn;

    public UserPanel() throws HeadlessException {
        super();
        createUI();
    }

    public UserPanel(User user) {
        super(user);
        setCurrentUser(user);
        createUI();
    }

//    public void createUI() {
//        super.createUI();
//    }
}
