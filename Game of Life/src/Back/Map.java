package Back;
import javax.swing.*;

//handles operations of the grid directly
public class Map {

    //keeps track of whether squares are alive or dead as displayed to the user
    private boolean[][] current;
    //used to calculate whether a square will live or die
    private boolean[][] auxillary;

    //returns the current alive/dead array
    public boolean[][] getCurrent(){
        return current;
    }

    //the displayed amount of alive squares
    private int pop;
    //the cumulative amount of squares that have been alive
    private int tot;

    //returns the current amount of alive squares
    public String getPop() { return "   Current population: " + pop; }
    //returns the current cumulative population
    public String getTot() { return "   Cumulative population: " + tot; }
    //returns the number of rows
    public int getRow() { return current.length; }
    //returns the number of cols
    public int getCol() { return current[0].length; }

    //
    public Map(int r, int c, double p){
        current = new boolean[r][c];
        auxillary = new boolean[r][c];
        for(int row = 0; row < r; row++){
            for(int col = 0; col < c; col++){
                if(Math.random() < p) {
                    current[row][col] = true;
                    pop++;
                    tot++;
                    auxillary[row][col] = true;
                }
            }
        }
    }


    //generates a random starting setup, given row size column size and probability that a square is alive
    public void reroll(int x, int r, int c){
        current = new boolean[r][c];
        auxillary = new boolean[r][c];
        double p = (double) x/100;
        tot = 0;
        pop = 0;
        for(int row = 0; row < r; row++){
            for(int col = 0; col < c; col++){
                if(Math.random() < p) {
                    auxillary[row][col] = true;
                    pop++;
                    tot++;
                }
                else
                    auxillary[row][col] = false;
            }
        }
        current = auxillary;
    }

    //calculates the next turn, and changes the current array and display accordingly
    public void step(){
        int cnt = 0;
        pop = 0;
        for(int row = 0; row < current.length; row++){
            for(int col = 0; col < current[0].length; col++){
                if(row != 0) {
                    if (col != 0 && current[row - 1][col - 1]) { cnt++; }
                    if (current[row - 1][col]) { cnt++; }
                    if (col != (current[0].length - 1) && current[row - 1][col + 1]) { cnt++; }
                }
                if(col != 0) {
                    if(current[row][col - 1]) { cnt++; }
                    if(row != (current.length - 1) && current[row + 1][col - 1]) { cnt++; }
                }
                if(col != (current[0].length - 1)) {
                    if (current[row][col + 1]) { cnt++; }
                    if (row != (current.length - 1) && current[row + 1][col + 1]) { cnt++; }
                }
                if(row != (current.length - 1) && current[row + 1][col]) { cnt++; }

                if(current[row][col]){
                    if(cnt < 2) {
                        auxillary[row][col] = false;
                    }
                    else if(cnt == 2 || cnt == 3){
                        auxillary[row][col] = true;
                        pop++;
                        tot++;
                    }
                    else if(cnt > 3) {
                        auxillary[row][col] = false;
                    }

                }
                else if(cnt == 3) {
                    auxillary[row][col] = true;
                    pop++;
                    tot++;
                }
                cnt = 0;
            }
        }
        for(int r = 0; r < current.length; r++){
            for(int c = 0; c < current.length; c++){
                current[r][c] = auxillary[r][c];
            }
        }
    }
}
