/**
 * Created by rt on 6/21/17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {


    public static void main(String[] args) {
        login();
    }

    //Launches first login frame. After Login button is clicked, it closes window frame
    public static void login() {

        final JFrame frame = new JFrame("Database Login");
        final JButton btnLogin = new JButton("Click to login");

        btnLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg;
                        loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if (loginDlg.isSucceeded()) {
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!\n You are logged on ");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                    }
                });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.setVisible(true);
    }
}