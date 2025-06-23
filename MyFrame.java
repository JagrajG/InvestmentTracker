import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    JButton submitButton = new JButton("Add new Row");
    JPanel rowContainer = new JPanel();
    int rowCount = 0;
    final int MAX_ROWS = 5;

    MyFrame() {

        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setTitle("Investment Tracking");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(122, 121, 121));

        rowContainer.setLayout(new BoxLayout(rowContainer, BoxLayout.Y_AXIS));
        rowContainer.setBackground(new Color(117, 117, 117));
        this.add(new JScrollPane(rowContainer), BorderLayout.CENTER);
        this.add(submitButton, BorderLayout.SOUTH);


        submitButton.addActionListener(e -> {
            if (rowCount < MAX_ROWS) {
                CenterPanel newPanel = new CenterPanel();
                rowContainer.add(newPanel);
                rowContainer.revalidate();
                rowContainer.repaint();
                rowCount++;
            } else {
                JOptionPane.showMessageDialog(this, "Maximum of 10 rows reached.");
            }
        });

        CenterPanel initialPanel = new CenterPanel();
        rowContainer.add(initialPanel);
        rowCount++;
    }
}
