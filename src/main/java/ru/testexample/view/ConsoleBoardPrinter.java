package ru.testexample.view;

public class ConsoleBoardPrinter implements BoardPrinter {

    public ConsoleBoardPrinter(){
        printExampleBoard();
    }

    public void printBoard(int[] board) {
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

    public void printExampleBoard() {
        System.out.println("Номера ячеек для хода:");
        System.out.println(" 1  2  3 ");
        System.out.println(" 4  5  6 ");
        System.out.println(" 7  8  9 ");
        System.out.println("---------");
        System.out.println("Let the mortal begun!");
    }
}
