/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package strategy;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements  WinningStrategy {
     Map<Character,Integer> leftDiaMpa=new HashMap<>();
     Map<Character,Integer> righDiaMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        Character symbol=move.getPlayer().getSymbol();

        //check if a symbol in a left diagonal
        if(row==col){
            if(!leftDiaMpa.containsKey(symbol)){
                leftDiaMpa.put(symbol,0);
            }
            leftDiaMpa.put(symbol,leftDiaMpa.get(symbol)+1);
            if(leftDiaMpa.get(symbol).equals(board.getDimension())){
                return  true;
            }
        }

        if(row+col==(board.getDimension()-1)){
            if(!righDiaMap.containsKey(symbol)){
                righDiaMap.put(symbol,0);
            }
            righDiaMap.put(symbol,righDiaMap.get(symbol)+1);
            if(righDiaMap.get(symbol).equals(board.getDimension())){
                return true;
            }
        }

          return  false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        Character symbol = lastMove.getPlayer().getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        // check if move was part of left diagonal
        if(row==col){
            leftDiaMpa.put(symbol, leftDiaMpa.get(symbol)-1);
        }

        // check if move was part of right diagonal
        if(row+col==(board.getDimension()-1)){
            righDiaMap.put(symbol, righDiaMap.get(symbol)-1);
        }
    }
}
