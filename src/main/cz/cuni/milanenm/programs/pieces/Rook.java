package main.cz.cuni.milanenm.programs.pieces;

import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.player.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Rook piece in a chess game.
 * The Rook moves vertically and horizontally.
 */
public class Rook extends Piece {

    /**
     * Constructor to initialize the Rook piece with its team and the board cells
     * @param team The team to which the piece belongs (WHITE or BLACK)
     * @param cells The 2D array of board cells
     */
    public Rook(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(532, 0, 133, 133);
        else image = tileset.getSubimage(532, 133, 133, 133);
    }

    /**
     * Method to get the list of cells available for the Rook's move
     * @return A list of available cells for the Rook to move
     */
    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        for (int i = x-1; i >= 0; i--) {
            Cell cell = cells[y][i];
            if(addPiece(list, cell)) break;
        }
        for (int i = x+1; i < 8; i++) {
            Cell cell = cells[y][i];
            if(addPiece(list, cell)) break;
        }
        for (int i = y-1; i >= 0; i--) {
            Cell cell = cells[i][x];
            if(addPiece(list, cell)) break;
        }
        for (int i = y+1; i < 8; i++) {
            Cell cell = cells[i][x];
            if(addPiece(list, cell)) break;
        }
        return list;
    }
}
