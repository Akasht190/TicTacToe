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

public class RowWinningStrategy implements  WinningStrategy{

    Map<Integer,Map<Character,Integer>> rowMaps=new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        Character symbol=move.getPlayer().getSymbol();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row,new HashMap<>());
        }

        Map<Character,Integer> rowMap=rowMaps.get(row);

        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol,rowMap.get(symbol)+1);

        if(rowMap.get(symbol).equals(board.getDimension())){
            return true;
        }
        return  false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        Character symbol = lastMove.getPlayer().getSymbol();

        Map<Character, Integer> rowMap = rowMaps.get(row);
        rowMap.put(symbol, rowMap.get(symbol)-1);
    }
}
