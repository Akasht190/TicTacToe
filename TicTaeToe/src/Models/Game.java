package Models;
import exception.DuplicateSymbolForPlayer;
import exception.MoreThanOneBotException;
import exception.PlayerAndBoardCountException;
import strategy.WinningStrategy;

import  java.util.*;
public class Game {

    private List<Player> playerList;
    private  Board board;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    private  Player winner;

    private Game(List<Player> playerList,int dimension,List<WinningStrategy> winningStrategies) {
        this.nextPlayerIndex=0;
        this.gameState=GameState.INPROGRESS;
        this.moves=new ArrayList<>();
        this.playerList=playerList;
        this.board=new Board(dimension);
        this.winningStrategies=winningStrategies;
    }

    private  GameState gameState;

    private  int nextPlayerIndex;

    private  List<Move> moves;

    private  List<WinningStrategy> winningStrategies;

    public  static  Builder getBuilder(){
        return  new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player = playerList.get(nextPlayerIndex);
        Cell cell=player.makeMove(board);
        Move move=new Move(cell,player);
        moves.add(move);

        if(checkWinner(move,board)){
            gameState=GameState.CONCLUDED;
            winner=player;
            return;
        }

        if(moves.size()== board.getDimension()* board.getDimension()){
            gameState=GameState.DRAW;
            return ;
        }
        nextPlayerIndex++;
        nextPlayerIndex=nextPlayerIndex%playerList.size();
    }

    private boolean checkWinner(Move move, Board board) {
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return  true;
            }
        }
        return  false;
    }

    public void undo() {
        if(moves.size()==0){
            System.out.println("No moves to undo");
            return;
        }

        Move lastMove=moves.get(moves.size()-1);
        moves.remove(lastMove);

        Cell cell=lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);

        for(WinningStrategy winningStrategy:winningStrategies){
            winningStrategy.handleUndo(board,lastMove);
        }

        if(nextPlayerIndex!=0) {
            nextPlayerIndex--;
        }
        else{
            nextPlayerIndex=playerList.size()-1;

        }

    }

    public  static  class Builder{
        private  List<Player> players;

        private  int dimension;

        private  List<WinningStrategy> winningStrategies;

        private  Builder(){
            this.dimension=0;
            this.players=new ArrayList<>();
        }

        public  Builder setDimension(int dimension){
            this.dimension=dimension;
            return  this;
        }

        public  Builder setPlayers(List<Player> players){
            this.players=players;
            return  this;
        }

        public  Builder setWinnerStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies=winningStrategies;
            return  this;
        }

        public  Game build() throws MoreThanOneBotException, PlayerAndBoardCountException, DuplicateSymbolForPlayer {
            validateBotCount();
            validateDimensionAndPlayerCount();
            validateUniqueSymbolForEachPlayer();
            return  new Game(players,dimension,winningStrategies);
        }

        private void validateUniqueSymbolForEachPlayer() throws DuplicateSymbolForPlayer {
            HashSet<Character> symbols=new HashSet<>();
            for(Player player:players){
                if(symbols.contains(player.getSymbol())){
                    throw new DuplicateSymbolForPlayer();
                }
                symbols.add(player.getSymbol());
            }
        }

        private void validateDimensionAndPlayerCount() throws PlayerAndBoardCountException {
            if(players.size()!=(dimension-1)){
                throw new PlayerAndBoardCountException();
            }
        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.Bot)){
                    botCount++;
                }
                if(botCount>1){
                    throw  new MoreThanOneBotException();
                }

            }
        }

    }




}
