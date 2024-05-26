/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Models;

import java.util.Scanner;

public class HumanPlayer  extends  Player{

    public Scanner scanner;

    public HumanPlayer(char symbol, String name, int id, PlayerType playerType, Scanner scanner) {
        super(symbol, name, id, playerType);
        this.scanner = scanner;
    }

//    public HumanPlayer(char symbol, String name, int id, PlayerType playerType) {
//        super(symbol, name, id, playerType);
//    }

    @Override
    public Cell makeMove(Board board) {
        System.out.print(this.getName()+",It's your turn to make the move ,enter row and col");

        int row=scanner.nextInt();
        int col=scanner.nextInt();

        while (!validateRowAndCol(row,col,board)){
            System.out.print(this.getName()+",Invalid move ,please enter row and col");

             row=scanner.nextInt();
             col=scanner.nextInt();
        }

        Cell cell=board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }

    private boolean validateRowAndCol(int row, int col, Board board) {
        if(row>=board.getDimension() || row<0){
            return  false;
        }
        if(col>=board.getDimension() || col<0){
            return false;
        }
        if(CellState.FILLED.equals(board.getBoard().get(row).get(col).getCellState())){
            return false;
        }
        return true;
    }
}
