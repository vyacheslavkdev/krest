package ru.testexample.console.game;

public class GamaResult {

    private int[][] win0 =  {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}};
    private boolean playerX = true;
    private boolean win = false;

    public  boolean winTest(int[] board) {

        for (int i=0; i < 8; i++){
                if(board[win0[i][0]] == 1
                 & board[win0[i][1]] == 1
                 & board[win0[i][2]] == 1){
                    win = true;
                } else
                if(board[win0[i][0]] == 0
                 & board[win0[i][1]] == 0
                 & board[win0[i][2]] == 0){
                    win = true;
                    playerX = false;
                }
            }
        return win;
        }

    public boolean whoWin(){
        return playerX;
    }

    public boolean isWin() {
        return win;
    }
}
