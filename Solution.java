import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /* Head ends here */
	
	public static void sudoku_solve(int[][] board)
    {
        solve(0,0,board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+((j==8)?"\n":" "));
            }
        }
    }

    public static int solve(int i, int j, int[][] board) {
        i %= 9;
        if (i == 9) {
            i = 0;
            if (j == 8)
                return 1;
	    j ++;
        }
        if (board[i][j] != 0) 
            return solve(i+1,j,board);

        for (int val = 1; val <= 9; val++) {
            if (isValid(i,j,val,board)) {
                board[i][j] = val;
                if (solve(i+1,j,board) == 1)
                    return 1;
            }
        }
        board[i][j] = 0; // backtrack failed
        return 0;
    }

    public static boolean isValid(int i, int j, int n, int[][] board) {
        for (int k = 0; k < 9; k++)  // row
            if (n == board[k][j])
                return false;

        for (int k = 0; k < 9; k++) // col
            if (n == board[i][k])
                return false;

        int boxX = (i / 3)*3;
        int boxY = (j / 3)*3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (n == board[boxX+k][boxY+m])
                    return false;

        return true; // valid guess
    }
	
	public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("sudoku.txt"));
        int n;
        int board[][] = new int[9][9];

        n = in.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 9; j++) {
                for(int k = 0; k < 9; k++) {
                    board[j][k] = in.nextInt();
                }
            }
            sudoku_solve(board);
        }
    }
	
	/* Tail starts here */
	
	/*
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        int board[][] = new int[9][9];

        n = in.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 9; j++) {
                for(int k = 0; k < 9; k++) {
                    board[j][k] = in.nextInt();
                }
            }
            sudoku_solve(board);
        }
    }
	
	*/

}