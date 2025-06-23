import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartWindow extends JFrame{

    public ChartWindow(List<CenterPanel> panels){
        setTitle("Investment Growth");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int index = 1;

        for(CenterPanel panel: panels){
            try {
                String metal = panel.getMetal();
                double amount = Double.parseDouble(panel.getAmount());
                double purchase = Double.parseDouble(panel.getPurchasePrice());
                double spot = Double.parseDouble(panel.getSpotPrice());

                double invested = purchase;
                double currentValue = amount * spot;

                String label = metal.isBlank() ? "Investment " + index : metal + " (" + index + ")";
                dataset.addValue(invested, "Invested", label);
                dataset.addValue(currentValue, "Current Value", label);
                index++;
            }catch (Exception ignored){

            }
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Investment Growth",
                "Investment",
                "Value ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(780, 550));
        setContentPane(chartPanel);

    }

}
