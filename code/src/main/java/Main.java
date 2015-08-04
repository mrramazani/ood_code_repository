/**
 * Created by hamid on 7/8/2015.
 */

import com.mongodb.MongoClient;
import content.Comment;
import content.Content;
import content.Relationship;
import default_.UserLoginDialog;
import org.mongodb.morphia.Morphia;
import ui.userpanel.AdminPanel;
import ui.userpanel.MainPanel;
import ui.userpanel.UserPanel;
import user.User;

import java.awt.*;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                UserLoginDialog loginPage = new UserLoginDialog();
//                AdminPanel mainPage = new AdminPanel();
                loginPage.setVisible(true);
            }
        });

    }

//    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("localhost", 27017);
    }

//    @Bean
    public Morphia morphia() {
        Set classesToMap = new HashSet();
        classesToMap.add(Content.class);
        classesToMap.add(User.class);
        classesToMap.add(Comment.class);
        classesToMap.add(Relationship.class);
        return new Morphia(classesToMap);
    }

 }

