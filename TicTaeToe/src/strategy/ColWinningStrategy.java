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

public class ColWinningStrategy implements  WinningStrategy{
    Map<Integer, Map<Character, Integer>> colMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();

        if (!colMaps.containsKey(col)) {
            colMaps.put(col, new HashMap<>());
        }

        Map<Character, Integer> colMap = colMaps.get(col);

        if (!colMap.containsKey(symbol)) {
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol) + 1);

        if (colMap.get(symbol).equals(board.getDimension())) {
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        Character symbol = lastMove.getPlayer().getSymbol();
        int col = lastMove.getCell().getCol();

        Map<Character, Integer> colMap = colMaps.get(col);
        colMap.put(symbol, colMap.get(symbol)-1);
    }
}
