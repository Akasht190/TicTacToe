/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Models;

import Controller.GameController;
import exception.DuplicateSymbolForPlayer;
import exception.MoreThanOneBotException;
import exception.PlayerAndBoardCountException;
import strategy.ColWinningStrategy;
import strategy.DiagonalWinningStrategy;
import strategy.RowWinningStrategy;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws PlayerAndBoardCountException, DuplicateSymbolForPlayer, MoreThanOneBotException {
        GameController gameController=new GameController();
        Scanner scanner=new Scanner(System.in);
        int dimension=3;

        ArrayList<Player> players=new ArrayList<>();
        players.add(new HumanPlayer('X',"Akash",1,PlayerType.Human,scanner));
        players.add(new Bot('0',"Hardik",2,PlayerType.Bot,DifficultyLevel.EASY));

        ArrayList<WinningStrategy> winningStrategies=new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game=gameController.createGame(dimension,players,winningStrategies);
        while (GameState.INPROGRESS.equals(game.getGameState())) {
            gameController.printBoard(game);

            System.out.println("Does anyone want to undo ? (y/n");
            String undo=scanner.next();

            if(undo.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }

        if(GameState.CONCLUDED.equals(game.getGameState())){
            System.out.println(game.getWinner().getName()+"has won the Game");
        }

        if(GameState.DRAW.equals(game.getGameState())){
            System.out.println("It is a draw game");
        }

    }
}
