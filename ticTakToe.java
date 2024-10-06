/*
Author: Sunburnt1352
summary: this is a small little tik tak toe game I coded in an
about an hour, excuse any mistakes or inefficiencies, if you see anything that
can be improved, feel free to change this code or let me know and I can fix it!
I tried to comment a lot of this, in case someone wants to use this to learn some java,
if the one reading this has any questions about how I used something feel free to reach out.
I hope you have a great day! <3
 */
import java.util.Scanner;

class TicTakToe{
    static Scanner in = new Scanner(System.in);

    private String[][] board;
    private String whoTurn;

    /**
     * this is a constructor for the board
     * in this board is set to a 3x3 and all the
     * elements are set to an empty space, so
     * it's not a null value, also sets the
     * turn to X as X goes first
     */
    TicTakToe(){
        this.board= new String[3][3];
        whoTurn="X";

        for(int i=0;i<3;i++){ //goes through the rows
            for(int j=0;j<3;j++){ //goes through the columns
                board[i][j]=" ";
            }
        }
    }

    /**
     * this is where the user inputs where they want to go
     * it will ask for the row and column and change the
     * corresponding spot in board to whoever turn it is (X or Os)
     */
    public void move(){
        int row; int col; //establishes the row and col
        boolean retryMove;//this value is to make sure the player
        // inputted a valid move, used in the do while loop
        do {
            retryMove=false; //it assumes that the move is correct
            System.out.print("What row do want to play: ");
            row = (in.nextInt()-1);
            System.out.print("What col do want to play: ");
            col = (in.nextInt()-1);
            //above takes in the place where the user wants to go

            if(board[row][col].equals(" ")) { //if the spot is empty
                board[row][col] = whoTurn; //it will set it to X or O
            }
            else{ //if the chosen spot isn't free, it will make the player input again
                retryMove=true;
                System.out.println("Sorry, spot is already taken");
            }
        }while(retryMove);
    }

    /**
     * this will check if the game has been won yet,
     * it looks at the rows, columns and diagonals to check
     * @return it returns a true if the game has been won and a false if not
     */
    public boolean wonYet(){
        //this for loop will run through the rows and columns to check for 3 of the same value
        for(int i=0;i<3;i++){
            if(board[i][0].equals("X") || board[i][0].equals("O")){ //will only run through the row if the first value
                //is an X or an O and not an empty space
                if ((board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]))) { //it then checks if the next two
                    //values in the row are the same as the first, if so
                    displayBoard(); //it displaces the board
                    System.out.println(whoTurn + "s won!"); //sends out a winning messages
                    return true; //and then returns true, as the game was won
                }
            }
            //this will run through the columns now
            if(board[0][i].equals("X") || board[0][i].equals("O") ){ //it again checks that the first value is
                //not an empty space
                if ((board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) ) ) { //does the same as above but for columns
                    displayBoard();
                    System.out.println(whoTurn + "s won!");
                    return true;
                }
            }
        }
        //this now checks the diagonals
        for(int j=0;j<3;j+=2) { //it will loop for the diagonal starting in the first position to the opposite one
            //it jumps by 2 so it checks [0][0] then [0][2]
            if (board[0][j].equals("X") || board[0][j].equals("O")) { //same as above but diagonal
                if (board[0][j].equals(board[1][1]) && board[0][j].equals(board[2][2-j])) { //[2][x] is the final row, and
                    //on the first diagonal is checks [0][2] but on the second it needs to check the opposite at [2][0]
                    displayBoard();
                    System.out.println(whoTurn + "s won!");
                    return true;
                }
            }
        }

        //for a final test, it checks if there are any more space left to go and if its a tie
        for(int q=0;q<3;q++){
            for(int g=0;g<3;g++){ //goes through all the elements in board
                if (board[q][g].equals(" ")) { //if there is an open space it will return false as the game
                    return false; //can still be played
                }
            }
        }
        //if the above return does it run, it means no spots are available and it
        displayBoard();
        System.out.println("Tie Game, no winner"); //will display the board and say its a tie
        return true; //returns true as game is done
    }

    /**
     *This is a function for displaying the board in a readable way
     */
    public void displayBoard(){
        System.out.println(whoTurn+"'s turn");
        System.out.println("__________________");
        System.out.println("     1   2   3");
        //it first displays the top half, which shows a num for the columns and who's turn it is
        for(int i=0;i<3;i++){ //it then loops through the rows
            System.out.print((i+1)+" [ "); //displays the row number
            for(int j=0;j<3;j++){ //it goes through the columns
                if(!board[i][j].equals(" ")) //if the space isn't empty
                    System.out.print(" "+board[i][j]+"  "); //it will show the letter with some space on the sides
                else //if it is empty then it puts some lines on the bottom, this is just to make it
                //easier to visualize
                    System.out.print("___ ");
            }
            System.out.println("]");
        }
        System.out.println("__________________");
    }

    /**
     * this is where the bulk of the game happens
     * it will loop through the different functions of ticktacktoe until the game is done
     * functions called within: move, displayBoard, and wonYet
     * it also changes whose turn it is, switching X and O when needed
     */
    public void playGame(){
        boolean keepPlaying;

        do {
            displayBoard();
            move();
            keepPlaying=wonYet();
            //System.out.println(Arrays.deepToString(board));
            if (whoTurn.equals("X")) {
                whoTurn = "O";
            } else {
                whoTurn = "X";
            }
        }while(!keepPlaying);
    }


    //these are just getters, only needed for testing, and left in so they can be used
    //if someone else wants to borrow the code
    public String[][] getBoard() {
        return board;
    }

    public String getWhoTurn() {
        return whoTurn;
    }
}

public class Main {
    public static void main(String[] args) {
        //this is just a test game that I have running
        TicTakToe test1= new TicTakToe();
        test1.playGame();
    }
}
