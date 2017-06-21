/**
 * Created by rt on 6/21/17.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Login extends JFrame {
    JFrame frame1;
    JScrollPane scroll = new JScrollPane(table);
    private String inputUser;
    private String inputPsd;
    private Connection con;
    static JTable table;


    public boolean authenticate(String username, String password) {
        inputUser = username;
        inputPsd = password;

        boolean connected = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/RenzoDataStore", inputUser, inputPsd);
            if (con != null) {
                connected = true;
            }
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student");
//            while (rs.next())
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return connected;
    }


    public void displayRecords() {

        String[] columnNames = {"Id", "Name", "Age"};
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        Vector v = new Vector();
        JComboBox c1 = new JComboBox();

        c1.setBounds(150, 110, 150, 20);

        add(c1);
        String from = (String) c1.getSelectedItem();
        //String textvalue = textbox.getText();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student");
            int i = 0;
            int id;
            String name;
            int age;

            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                age = rs.getInt(3);

                model.addRow(new Object[]{id, name, age});
                System.out.println(id + "  " + name + "  " + age);
                i++;
                v.add(id + " " + name + " " + age);
                c1 = new JComboBox(v);
                c1.setBounds(150, 110, 150, 20);

                add(c1);
            }

            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }

    //TODO: This method is not really needed but it's helpful for testing
    public static void main(String[] args) {
        Login l = new Login();
        l.authenticate("renzo", "sonre269");
        l.displayRecords();
    }
}


