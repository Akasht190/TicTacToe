/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Controller;
import  java.util.*;

import Models.Game;
import Models.Player;
import exception.DuplicateSymbolForPlayer;
import exception.MoreThanOneBotException;
import exception.PlayerAndBoardCountException;
import strategy.WinningStrategy;

public class GameController {
    public Game createGame(int dimension, List<Player> playerList, List<WinningStrategy> winningStrategies) throws PlayerAndBoardCountException, DuplicateSymbolForPlayer, MoreThanOneBotException {
        return  Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(playerList)
                .setWinnerStrategies(winningStrategies)
                .build();

    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public  void printBoard(Game game){
        game.printBoard();
    }

    public void undo(Game game) {
        game.undo();
    }
}
