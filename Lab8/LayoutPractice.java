/**
*@version cs251 Lab002 Date: 11/06/2018
*@author Xiaomeng Li
**/
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

/** this is the LayoutPractice class which is going to draw the picture
with red, yellow circles, button and label **/
public class LayoutPractice extends JPanel implements ActionListener  {

    private static int numberOfClicks = 0;
    private static JButton bu= new JButton("Click me for a dialog") ;
    private static JLabel label1;
    /**
    *we need a function to identify player's clicking movement
    *@param ActionEvent e
    **/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bu){
            numberOfClicks = numberOfClicks + 1;
            label1.setText("Button clicks = " + numberOfClicks);
            JOptionPane.showMessageDialog ( null, "You have clicked "
            	+numberOfClicks+" times", "Button Count",
            	JOptionPane.INFORMATION_MESSAGE );
            System.out.println("You have clicked " + numberOfClicks + " times");
        }

    }

    /**
    *LayoutPractice to set up the drawing board
    **/
    public LayoutPractice() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
    }
    
    /**
    * The paintComponent function to draw the circles
    *@param Graphics g
    **/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillOval(25, 25, 450, 450);
        g.setColor(Color.YELLOW);
        g.fillOval(50, 50, 400, 400);
        g.setColor(Color.RED);
        g.fillOval(75, 75, 350, 350);
        g.setColor(Color.YELLOW);
        g.fillOval(100, 100, 300, 300);
        g.setColor(Color.RED);
        g.fillOval(125, 125, 250, 250);
        g.setColor(Color.YELLOW);
        g.fillOval(150, 150, 200, 200);
        g.setColor(Color.RED);
        g.fillOval(175, 175, 150, 150);
        g.setColor(Color.YELLOW);
        g.fillOval(200, 200, 100, 100);
        g.setColor(Color.RED);
        g.fillOval(225, 225, 50, 50);
        g.setColor(Color.YELLOW);
        g.fillOval(237, 237, 25, 25);
    }

    /**
    *createAndShowGUI to set up the button and label. 
    **/
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Layout Practice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LayoutPractice instance = new LayoutPractice();

        JPanel panel = new JPanel();
        bu.addActionListener(instance);
        panel.add(bu);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        label1 = new JLabel();
        label1.setText("Button clicks = "+numberOfClicks);
        panel.add(label1);



        JPanel borderedPanel = new LayoutPractice();
        Border border = BorderFactory.createTitledBorder("This panel has a border");
        borderedPanel.setBorder(border);

        panel.add(borderedPanel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    /** main method to run the drawing board **/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
