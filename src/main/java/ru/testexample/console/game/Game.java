package ru.testexample.console.game;


import ru.testexample.console.input.ConsoleStepInput;
import ru.testexample.console.input.StepInput;
import ru.testexample.console.view.BoardPrinter;
import ru.testexample.console.view.ConsoleBoardPrinter;

import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    static int[] emptyBoard = {2,2,2,2,2,2,2,2,2};

    private BoardPrinter boardPrinter;
    private StepInput stepInput;
    private GamaResult gamaResult;

    public Game(){
        boardPrinter = new ConsoleBoardPrinter();
        stepInput = new ConsoleStepInput(new InputStreamReader(System.in));
        gamaResult = new GamaResult();
    }

    public void startGame(){
        boolean game = true;
        boolean cross = true;
        int maxStep = 0;

        boardPrinter.printBoard(emptyBoard);

        while(game){
            maxStep++;

            if (cross) {
                System.out.print("Ход игрока X: ");
            } else {
                System.out.print("Ход игрока O: ");
            }

           int index = stepInput.inputStep();
            if (index == -1){
                System.out.println("Game Over");
                break;
            }

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
                boardPrinter.printBoard(emptyBoard);
                game = !gamaResult.winTest(emptyBoard);
            }
            if (maxStep == 9){
                game = false;
                System.out.println("Ходы закончились. Game Over!");
            }
        }
        if (gamaResult.isWin()){
            if(gamaResult.whoWin()){
                System.out.println("Победил игрок X");
            } else {
            System.out.println("Победил игрок O");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.startGame();
    }
}
