package main.cz.cuni.milanenm.programs.pieces;

import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.player.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Knight piece in a chess game.
 * The Knight moves in an L-shape.
 */
public class Knight extends Piece {

    /**
     * Creates a new Knight piece.
     *
     * @param team  The team (white or black) this Knight belongs to.
     * @param cells The chess board represented as a 2D array of Cells.
     */
    public Knight(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(399, 0, 133, 133);
        else image = tileset.getSubimage(399, 133, 133, 133);
    }

    /**
     * Gets the available cells this Knight can move to.
     *
     * @return A list of available cells.
     */
    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        // Moves in an L-shape to the right
        if(y-2 >= 0 && x+1 < 8 && (cells[y-2][x+1].getPiece() == null
                || cells[y-2][x+1].getPiece().getTeam() != team)) list.add(cells[y-2][x+1]);
        if(y+1 < 8 && x+2 < 8 && (cells[y+1][x+2].getPiece() == null
                || cells[y+1][x+2].getPiece().getTeam() != team)) list.add(cells[y+1][x+2]);
        if(y+2 < 8 && x-1 >= 0 && (cells[y+2][x-1].getPiece() == null
                || cells[y+2][x-1].getPiece().getTeam() != team)) list.add(cells[y+2][x-1]);
        if(y-1 >= 0 && x-2 >= 0 && (cells[y-1][x-2].getPiece() == null
                || cells[y-1][x-2].getPiece().getTeam() != team)) list.add(cells[y-1][x-2]);
        // Moves in an L-shape to the left
        if(y-2 >= 0 && x-1 >= 0 && (cells[y-2][x-1].getPiece() == null
                || cells[y-2][x-1].getPiece().getTeam() != team)) list.add(cells[y-2][x-1]);
        if(y-1 >= 0 && x+2 < 8 && (cells[y-1][x+2].getPiece() == null
                || cells[y-1][x+2].getPiece().getTeam() != team)) list.add(cells[y-1][x+2]);
        if(y+2 < 8 && x+1 < 8 && (cells[y+2][x+1].getPiece() == null
                || cells[y+2][x+1].getPiece().getTeam() != team)) list.add(cells[y+2][x+1]);
        if(y+1 < 8 && x-2 >= 0 && (cells[y+1][x-2].getPiece() == null
                || cells[y+1][x-2].getPiece().getTeam() != team)) list.add(cells[y+1][x-2]);
        return list;
    }
}
