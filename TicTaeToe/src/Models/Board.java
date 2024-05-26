package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //This is how to create a empty board
    public Board(int dimension) {
        this.dimension = dimension;
        this.board =new ArrayList<>();

        for(int i=0;i<dimension;i++){
            board.add(new ArrayList<>());//row

            for(int j=0;j<dimension;j++){
                board.get(i).add(new Cell(i,j));//for every row we create a new cell in column
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    private int dimension;
    private List<List<Cell>> board;

    public void printBoard() {
        for(List<Cell> row:board){
            for(Cell cell:row){
                cell.printCell();
            }
            System.out.println();
        }
    }
}
