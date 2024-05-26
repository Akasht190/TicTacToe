/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package strategy;

import Models.Board;
import Models.Cell;
import Models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements  BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row:board.getBoard()){
            for(Cell cell:row){
                if(CellState.EMPTY.equals(cell.getCellState())){
                    return  cell;
                }
            }
        }
        return  null;
    }
}
