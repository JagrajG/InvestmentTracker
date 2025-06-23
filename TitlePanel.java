import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    JLabel titleLabel = new JLabel();

    TitlePanel(){
        //JLabel
        titleLabel.setText("Investment Tracking");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        //Panel
        this.setBackground(new Color(117, 117, 117));
        this.setPreferredSize(new Dimension(100,100));
        this.setVisible(true);
        this.add(titleLabel);
    }

}
