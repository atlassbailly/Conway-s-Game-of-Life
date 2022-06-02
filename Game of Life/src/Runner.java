import GUI.Control;
import javax.swing.*;

//thread allows the game to continue without user input. also creates the game controller
public class Runner implements Runnable{

    //marks whether the game is running or not (play/pause)
    private boolean on = true;
    //controller that will actually handle the game
    private Control cont;
    //pause
    private JButton b;
    //play
    private JButton ba;

    //creates the controller and thread, with pause/play functionality
    @Override
    public void run(){
        cont = new Control();
        cont.addButton(b);
        cont.addButton(ba);
        while(on){
            try {
                cont.step();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                on = false;
            }
        }
    }

    //passes in the pause button from the main method
    public void firstButton(JButton b){
        this.b = b;
    }
    //passes in the play button from the main method
    public void secondButton(JButton b){
        this.ba = b;
    }
}
