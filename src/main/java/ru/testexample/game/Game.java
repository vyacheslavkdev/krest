package ru.testexample.game;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {

    static int[] emptyBoard = {2,2,2,2,2,2,2,2,2};

    static int[] board =  { 0,2,1,
                            2,1,2,
                            0,2,0};

    public static void main(String[] args) {
        printExampleBoard();
        printBoard(emptyBoard);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean game = true;
        boolean cross = true;
        int maxStep = 0;
        while (game){
            maxStep++;
            try {
                if (cross) {
                    System.out.print("Ход игрока X: ");
                } else {
                    System.out.print("Ход игрока O: ");
                }


                String sumbol = reader.readLine();
                int index = Integer.valueOf(sumbol) -1;

                if(emptyBoard[index] !=2){
                    System.out.println("Выберите другое поле!");
                } else{
                    if (cross) {
                        emptyBoard[index] = 1;
                        cross = false;
                    } else {
                        emptyBoard[index] = 0;
                        cross = true;
                    }
                    printBoard(emptyBoard);
                    game = !winTest(emptyBoard);
                }
                if (maxStep == 9){
                    game = false;
                }

            } catch (Exception e) {
                game = false;
                System.out.println("Game Over");
            }
        }
    }

    private static boolean winTest(int[] board) {
        boolean win = false;

        if (board[0] ==1 & board[1] ==1 & board[2] ==1
         || board[0] ==0 & board[1] ==0 & board[2] ==0){
            win = true;
        } else
        if (board[3] ==1 & board[4] ==1 & board[5] ==1
         || board[3] ==0 & board[4] ==0 & board[5] ==0){
            win = true;
        } else
        if (board[6] ==1 & board[7] ==1 & board[8] ==1
         || board[6] ==0 & board[7] ==0 & board[8] ==0){
            win = true;
        } else
        if (board[0] ==1 & board[3] ==1 & board[6] ==1
         || board[0] ==0 & board[3] ==0 & board[6] ==0){
            win = true;
        } else
        if (board[1] ==1 & board[4] ==1 & board[7] ==1
         || board[1] ==0 & board[4] ==0 & board[7] ==0){
            win = true;
        } else
        if (board[2] ==1 & board[5] ==1 & board[8] ==1
         || board[2] ==0 & board[5] ==0 & board[8] ==0){
            win = true;
        } else
        if (board[0] ==1 & board[4] ==1 & board[8] ==1
         || board[0] ==0 & board[4] ==0 & board[8] ==0){
            win = true;
        } else
        if (board[2] ==1 & board[4] ==1 & board[6] ==1
         || board[2] ==0 & board[4] ==0 & board[6] ==0){
            win = true;
        }
        return win;
    }

    private static void printBoard(int[] board) {
        int j = 0;
        for (int i = 0; i <9; i++){
            if(board[i] == 1){
                System.out.print(" x ");
            } else if(board[i] == 2){
                System.out.print(" _ ");
            } else if(board[i] == 0){
                System.out.print(" o ");
            }
            j++;
            if (j == 3 ){
                j=0;
                System.out.println();
            }
        }
    }
    private static void printExampleBoard() {
        System.out.println("Номера ячеек для хода:");
        System.out.println(" 1  2  3 ");
        System.out.println(" 4  5  6 ");
        System.out.println(" 7  8  9 ");
        System.out.println("---------");
        System.out.println("Let the mortal begun!");
    }
}
