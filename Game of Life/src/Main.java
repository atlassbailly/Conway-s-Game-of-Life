import GUI.Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//starts the thread
public class Main {

    //starts the thread that runs the game of life
    public static void main(String[] args){
        Runner r = new Runner();
        Thread t = new Thread(r);
        JButton b = new JButton("Pause");
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                t.suspend();
            }
        });
        JButton ba = new JButton("Play");
        ba.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                t.resume();
            }
        });
        r.firstButton(b);
        r.secondButton(ba);
        t.start();
    }

}
