package GUI;
import Back.Map;
import javax.swing.*;
import java.awt.*;

//displays the progress of the game to the user
public class View {

    //corresponding map and frame
    Map m;
    JFrame frame;
    //array of buttons to represent the alive or dead squares
    JButton[][] b;

    //returns the jFrame
    public JFrame getFrame(){
        return frame;
    }

    //initializes the frame and declares all the variables
    public View(int r, int c, Map map){
        m = map;
        b = new JButton[r][c];

        frame = new JFrame("Conway's Game");
        drawMap(r, c);

        frame.setLayout(new GridLayout(r, c));
        frame.setSize(750, 750);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(3);
    }

    //initiliazes and colors the button array according to the boolean array of whether squares are alive or dead
    public void drawMap(int r, int c){
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                b[row][col] = new JButton();
                b[row][col].setOpaque(true);
                b[row][col].setBorderPainted(false);
                if(m.getCurrent()[row][col])
                    b[row][col].setBackground(Color.CYAN);
                else
                    b[row][col].setBackground(Color.DARK_GRAY);
                frame.add(b[row][col]);
            }
        }
    }

    //colors the button array according to the boolean array of whether squares are alive or dead
    public void drawMap(){
        for (int row = 0; row < m.getRow(); row++) {
            for (int col = 0; col < m.getCol(); col++) {
                if(m.getCurrent()[row][col])
                    b[row][col].setBackground(Color.CYAN);
                else if(!m.getCurrent()[row][col])
                    b[row][col].setBackground(Color.DARK_GRAY);
            }
        }
    }

}