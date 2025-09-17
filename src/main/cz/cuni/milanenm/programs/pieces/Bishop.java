package main.cz.cuni.milanenm.programs.pieces;

import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.player.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bishop piece in a chess game.
 * The Bishop moves diagonally on the board.
 */
public class Bishop extends Piece {

    /**
     * Creates a new Bishop piece.
     *
     * @param team  The team (white or black) this Bishop belongs to.
     * @param cells The chess board represented as a 2D array of Cells.
     */
    public Bishop(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(266, 0, 133, 133);
        else image = tileset.getSubimage(266, 133, 133, 133);
    }

    /**
     * Gets the available cells this Bishop can move to.
     *
     * @return A list of available cells.
     */
    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        for (int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--) {
            Cell cell = cells[j][i];
            if(addPiece(list, cell)) break;
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0; i++, j--) {
            Cell cell = cells[j][i];
            if(addPiece(list, cell)) break;
        }
        for (int i = y+1, j = x-1; i < 8 && j >= 0; i++, j--) {
            Cell cell = cells[i][j];
            if(addPiece(list, cell)) break;
        }
        for (int i = y+1, j = x+1; i < 8 && j < 8; i++, j++) {
            Cell cell = cells[i][j];
            if(addPiece(list, cell)) break;
        }
        return list;
    }
}