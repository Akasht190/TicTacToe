package Models;

public  abstract  class Player {
    private char symbol;
    private  String name;

    public Player(char symbol, String name, int id, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.id = id;
        this.playerType = playerType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private  int id;

    private  PlayerType playerType;

    public abstract Cell makeMove(Board board);




//    public Cell makeMove(Board board) {
//
//    }
}
