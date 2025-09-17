package main.cz.cuni.milanenm.programs.pieces;

import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.player.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pawn piece in a chess game.
 * The Pawn moves forward one square, with an option to move two squares on its first move.
 * It captures diagonally.
 */
public class Pawn extends Piece {
    private boolean goUp;

    /**
     * Creates a new Pawn piece.
     *
     * @param team  The team (white or black) this Pawn belongs to.
     * @param cells The chess board represented as a 2D array of Cells.
     * @param goUp  Direction of movement; true if the Pawn moves up, false if down.
     */
    public Pawn(Team team, Cell[][] cells, boolean goUp) {
        super(team, cells);
        this.goUp = goUp;
        if(team == Team.WHITE) image = tileset.getSubimage(665, 0, 133, 133);
        else image = tileset.getSubimage(665, 133, 133, 133);
    }

    /**
     * Gets the available cells this Pawn can move to.
     *
     * @return A list of available cells.
     */
    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        if(goUp) {
            if(y > 0 && x < 7 && cells[y-1][x+1].getPiece() != null &&
                    cells[y-1][x+1].getPiece().getTeam() != team) list.add(cells[y-1][x+1]);
            if(y > 0 && x > 0 && cells[y-1][x-1].getPiece() != null &&
                    cells[y-1][x-1].getPiece().getTeam() != team) list.add(cells[y-1][x-1]);
            if(y > 0 && cells[y-1][x].getPiece() == null) {
                list.add(cells[y-1][x]);
                if (y == 6 && cells[y - 2][x].getPiece() == null) list.add(cells[y-2][x]);
            }
        } else {
            if (x < 7 && y < 7 && cells[y + 1][x + 1].getPiece() != null &&
                    cells[y + 1][x + 1].getPiece().getTeam() != team) list.add(cells[y + 1][x + 1]);
            if (x > 0 && y < 7 && cells[y + 1][x - 1].getPiece() != null &&
                    cells[y + 1][x - 1].getPiece().getTeam() != team) list.add(cells[y + 1][x - 1]);
            if (y < 7 && cells[y + 1][x].getPiece() == null) {
                list.add(cells[y + 1][x]);
                if (y == 1 && cells[y + 2][x].getPiece() == null) list.add(cells[y + 2][x]);
            }
        }
        return list;
    }
}
