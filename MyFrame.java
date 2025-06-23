import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {

    JButton submitButton = new JButton("Add New Row");
    JButton saveAllButton = new JButton("Save All");
    JButton quitButton = new JButton("Exit");
    JButton graphButton = new JButton("Show Chart");

    JPanel rowContainer = new JPanel();
    List<CenterPanel> centerPanels = new ArrayList<>();

    int rowCount = 0;
    final int MAX_ROWS = 5;

    public MyFrame() {
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setTitle("Investment Tracking");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(122, 121, 121));

        // Row panel container
        rowContainer.setLayout(new BoxLayout(rowContainer, BoxLayout.Y_AXIS));
        rowContainer.setBackground(new Color(117, 117, 117));
        JScrollPane scrollPane = new JScrollPane(rowContainer);
        this.add(scrollPane, BorderLayout.CENTER);


        // Load saved rows from database
        List<InvestmentRecord> savedRecords = DatabaseHelper.loadAllFromDatabase();

        if (!savedRecords.isEmpty()) {
            for (InvestmentRecord record : savedRecords) {
                CenterPanel panel = new CenterPanel();
                panel.loadFromRecord(record);
                rowContainer.add(panel);
                centerPanels.add(panel);
                rowCount++;
            }
        } else {
            // Fallback: add one blank row if database is empty
            CenterPanel initialPanel = new CenterPanel();
            rowContainer.add(initialPanel);
            centerPanels.add(initialPanel);
            rowCount++;
        }

        // Add row button logic
        submitButton.addActionListener(e -> {
            if (rowCount < MAX_ROWS) {
                CenterPanel newPanel = new CenterPanel();
                rowContainer.add(newPanel);
                centerPanels.add(newPanel);
                rowContainer.revalidate();
                rowContainer.repaint();
                rowCount++;
            } else {
                JOptionPane.showMessageDialog(this, "Maximum rows reached.");
            }
        });

        // Save all button logic
        saveAllButton.addActionListener(e -> {
            for (CenterPanel panel : centerPanels) {
                panel.saveToDatabase();
            }
        });

        //quit button logic
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        //show chart button logic

        graphButton.addActionListener(e -> {
            new ChartWindow(centerPanels).setVisible(true);
        });

        // Bottom panel with both buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(submitButton);
        buttonPanel.add(saveAllButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(graphButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
