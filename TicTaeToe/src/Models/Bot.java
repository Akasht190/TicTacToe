/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Models;

import factories.BotPlayingStrategyFactory;
import strategy.BotPlayingStrategy;

public class Bot extends  Player {
    public Bot(char symbol, String name, int id, PlayerType playerType, DifficultyLevel difficultyLevel) {
        super(symbol, name, id, playerType);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    private  DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    @Override
    public Cell makeMove(Board board) {
        System.out.println("Bot is making the move");
        Cell cell=botPlayingStrategy.makeMove(board);
        cell.setCellState(CellState.FILLED);;
        cell.setPlayer(this);
        return cell;
    }
}
