package GUI;
import Back.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.event.*;

//manages the user interaction with the game
public class Control implements ActionListener {

    //slider for the probability a square starts alive
    JSlider spawnRate;
    //slider for the number of rows/cols
    JSlider rows;

    //button to start the game again
    JButton reroll;
    //button to move only one step forward in the game
    JButton stepper;

    //displays the current amount of alive squares
    JLabel population;
    //displays the cumulative amount of squares that have been alive
    JLabel totPop;

    //frame for all buttons n' stuff
    JFrame controls;
    //corresponding boolean map
    Map m;
    //corresponding user display of map
    View view;

    //returns the corresponding map
    public Map getM() { return m; }

    //gives the introduction to the game and sets up all of the UI controls
    public Control(){
        JOptionPane.showMessageDialog(null, "Hey, welcome to Conway's Game of Life! \n" +
                "These are the rules: \n" +
                "1. Any live cell with fewer than two live neighbours dies. \n" +
                "2. Any live cell with two or three live neighbours lives. \n" +
                "3. Any live cell with more than three live neighbours dies. \n" +
                "4. Any dead cell with exactly three live neighbours becomes a live cell. \n");
        JOptionPane.showMessageDialog(null, "Live cells are cyan, dead cells are gray. \n" +
                "You can move on to the next round by hitting the 'Step' button. \n" +
                "You can start a game by hitting the 'Start' button, which will create a game \n" +
                "with the parameters set by the sliders. \n" +
                " \n" +
                "The sliders set: \n" +
                "1. The probability that a cell starts alive \n" +
                "2. The number of rows and columns of cells \n" +
                " \n" +
                "The control panel also provides you with the current population \n" +
                "cumulative population over all rounds. ");

        m = new Map(1, 1, 1);
        view = new View(1, 1, m);
        controls = new JFrame("Controls");


        spawnRate = new JSlider(JSlider.HORIZONTAL,1, 80, 50);
        spawnRate.setMajorTickSpacing(20);
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 1 ), new JLabel("0.01") );
        labelTable.put( new Integer( 80 ), new JLabel("0.80") );
        spawnRate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

            }
        });
        rows = new JSlider(JSlider.HORIZONTAL,5, 50, 10);
        rows.setMajorTickSpacing(5);
        Hashtable rowTable = new Hashtable();
        rowTable.put( new Integer( 5 ), new JLabel("5") );
        rowTable.put( new Integer( 50 ), new JLabel("50") );
        rows.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

            }
        });

        stepper = new JButton("Step");
        stepper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m.step();
                population.setText(String.valueOf(m.getPop()));
                totPop.setText(String.valueOf(m.getTot()));
                view.drawMap();
            }
        });
        reroll = new JButton("Start");
        reroll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!java.util.Objects.isNull(view))
                    view.getFrame().dispose();
                m = new Map(rows.getValue(), rows.getValue(), (double) spawnRate.getValue()/100);
                view = new View(rows.getValue(), rows.getValue(), m);
            }
        });

        population = new JLabel(m.getPop());
        totPop = new JLabel(m.getTot());


        spawnRate.setLabelTable( labelTable );
        spawnRate.setMajorTickSpacing(20);
        spawnRate.setPaintTicks(true);
        spawnRate.setPaintLabels(true);

        rows.setLabelTable( rowTable );
        rows.setMajorTickSpacing(20);
        rows.setPaintTicks(true);
        rows.setPaintLabels(true);

        controls.add(stepper);
        controls.add(reroll);
        controls.add(spawnRate);
        controls.add(rows);
        controls.add(population);
        controls.add(totPop);

        controls.setLayout(new GridLayout(8, 1));
        controls.setSize(200, 400);
        controls.setVisible(true);
        controls.setResizable(true);
        controls.setDefaultCloseOperation(3);
    }

    //adds a button to the control panel
    public void addButton(JButton b){
        controls.add(b);
    }

    //tells the map to move forward a step, and redraws all necessary things
    public void step(){
        m.step();
        population.setText(String.valueOf(m.getPop()));
        totPop.setText(String.valueOf(m.getTot()));
        view.drawMap();
    }

    //empty method required for ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
