import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DatabaseHelper.createTable();
       MyFrame myframe = new MyFrame();
       TitlePanel titlePanel = new TitlePanel();
       CenterPanel centerPanel = new CenterPanel();

       //Add label for title
       myframe.add(titlePanel, BorderLayout.NORTH);

       myframe.setVisible(true);

    }
}
