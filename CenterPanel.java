import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Vector;

public class CenterPanel extends JPanel {
    private JTextField amount;
    private JTextField purchasePrice;
    private JTextField pricePerOz;
    private JTextField spotPrice;
    private JTextField profitLoss;
    private JTextField metalField;
    private boolean isSaved = false;


    int textWidth = 70;
    int textHeight = 50;
    CenterPanel(){

        metalField = new JTextField();
        metalField.setPreferredSize(new Dimension(textWidth, textHeight));

        //Text Felids
         amount = new JTextField();
         purchasePrice = new JTextField();
         pricePerOz = new JTextField();
         spotPrice = new JTextField();
         profitLoss = new JTextField();

         //Unit labes
         JLabel unitSign = new JLabel("oz");
         JLabel dollarSign_one = new JLabel("$");
        JLabel dollarSign_two = new JLabel("$");

        JLabel pricePerOzSign = new JLabel("$/oz");
        JLabel percentSign = new JLabel("%");


        //Text Felid Dimensions
        amount.setPreferredSize(new Dimension(textWidth,textHeight));
        purchasePrice.setPreferredSize(new Dimension(textWidth,textHeight));
        pricePerOz.setPreferredSize(new Dimension(textWidth,textHeight));
        spotPrice.setPreferredSize(new Dimension(textWidth,textHeight));
        profitLoss.setPreferredSize(new Dimension(textWidth,textHeight));

        //Text felid setting
        pricePerOz.setEditable(false);
        profitLoss.setEditable(false);
        // Buttons

        //Panel settings:
        this.setBackground(new Color(117, 117, 117));
        this.setLayout(new FlowLayout());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        //Panel add components:

        this.add(new JLabel("Stock Name: "));
        this.add(metalField);

        //amount in oz
        this.add(amount);
        this.add(unitSign);

        //amount in dollar
        this.add(purchasePrice);
        this.add(dollarSign_one);

        //ammount in $/oz
        this.add(pricePerOz);
        this.add(pricePerOzSign);

        //amount in $
        this.add(spotPrice);
        this.add(dollarSign_two);

        //amount in %
        this.add(profitLoss);
        this.add(percentSign);


        this.setVisible(true);

        //set listeners
        amount.getDocument().addDocumentListener(updatePriceListener);
        purchasePrice.getDocument().addDocumentListener(updatePriceListener);
        pricePerOz.getDocument().addDocumentListener(updateProfitLoss);
        spotPrice.getDocument().addDocumentListener(updateProfitLoss);


    }


    //Add document updatePriceListener
    DocumentListener updatePriceListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updatePricePerOz();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updatePricePerOz();

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updatePricePerOz();

        }

    };

    //Add document updateProfitLoss()

    DocumentListener updateProfitLoss = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateProfitLoss();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateProfitLoss();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateProfitLoss();
        }
    };


    private void updatePricePerOz(){
        try{
            double amt = Double.parseDouble(amount.getText());
            double price = Double.parseDouble(purchasePrice.getText());

            if(amt!=0){
                double result = price/amt;
                pricePerOz.setText(String.format("%.2f", result));
            }else{
                pricePerOz.setText("");

            }


        }catch (NumberFormatException ex){
            pricePerOz.setText("");

        }
    }

    private void updateProfitLoss(){
        try {
            double ppOz = Double.parseDouble(pricePerOz.getText());
            double buyPrice = Double.parseDouble(spotPrice.getText());

            if(ppOz !=0){
                double result = (((buyPrice - ppOz) / ppOz)) * 100 ;

                if(result > 0){
                    profitLoss.setForeground(new Color(12, 122, 42));
                }
                else if (result <=0) {
                    profitLoss.setForeground(Color.red);

                }
                profitLoss.setText(String.format("%.2f", result));
            }else{
                profitLoss.setText("");
            }
        }catch (NumberFormatException ex){
            profitLoss.setText("");
        }
    }

    public void saveToDatabase() {
        if (isSaved) return; // skip if already saved

        try {
            String metalType = metalField.getText();
            double amt = Double.parseDouble(amount.getText());
            double purchase = Double.parseDouble(purchasePrice.getText());
            double ppo = Double.parseDouble(pricePerOz.getText());
            double spot = Double.parseDouble(spotPrice.getText());
            double profit = Double.parseDouble(profitLoss.getText());

            String sql = "INSERT INTO investments (metal, amount, purchase_price, price_per_oz, spot_price, profit_loss) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DatabaseHelper.connect();
                 java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, metalType);
                pstmt.setDouble(2, amt);
                pstmt.setDouble(3, purchase);
                pstmt.setDouble(4, ppo);
                pstmt.setDouble(5, spot);
                pstmt.setDouble(6, profit);

                pstmt.executeUpdate();
                isSaved = true; // mark as saved
                JOptionPane.showMessageDialog(this, "Row saved successfully!");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }


    public void loadFromRecord(InvestmentRecord record) {
        metalField.setText(record.metal);
        amount.setText(String.valueOf(record.amount));
        purchasePrice.setText(String.valueOf(record.purchasePrice));
        pricePerOz.setText(String.format("%.2f", record.pricePerOz));
        spotPrice.setText(String.valueOf(record.spotPrice));
        profitLoss.setText(String.format("%.2f", record.profitLoss));
        profitLoss.setForeground(record.profitLoss >= 0 ? new Color(12, 122, 42) : Color.RED);
    }

    public String getMetal() {
        return metalField.getText(); // Use combo.getSelectedItem().toString() if still using combo box
    }

    public String getAmount() {
        return amount.getText();
    }

    public String getPurchasePrice() {
        return purchasePrice.getText();
    }

    public String getSpotPrice() {
        return spotPrice.getText();
    }



}
