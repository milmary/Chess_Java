package main.cz.cuni.milanenm.programs.pieces;

import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.player.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a King piece in a chess game.
 * The King can move one square in any direction.
 */
public class King extends Piece {

    /**
     * Creates a new King piece.
     *
     * @param team  The team (white or black) this King belongs to.
     * @param cells The chess board represented as a 2D array of Cells.
     */
    public King(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(0, 0, 133, 133);
        else image = tileset.getSubimage(0, 133, 133, 133);
    }

    /**
     * Gets the available cells this King can move to.
     *
     * @return A list of available cells.
     */
    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        if(y > 0 && x < 7 && (cells[y-1][x+1].getPiece() == null
                || cells[y-1][x+1].getPiece().getTeam() != team)) list.add(cells[y-1][x+1]);
        if(x < 7 && (cells[y][x+1].getPiece() == null
                || cells[y][x+1].getPiece().getTeam() != team)) list.add(cells[y][x+1]);
        if(y < 7 && x < 7 && (cells[y+1][x+1].getPiece() == null
                || cells[y+1][x+1].getPiece().getTeam() != team)) list.add(cells[y+1][x+1]);
        if(y < 7 && (cells[y+1][x].getPiece() == null
                || cells[y+1][x].getPiece().getTeam() != team)) list.add(cells[y+1][x]);
        if(y < 7 && x > 0 && (cells[y+1][x-1].getPiece() == null
                || cells[y+1][x-1].getPiece().getTeam() != team)) list.add(cells[y+1][x-1]);
        if(x > 0 && (cells[y][x-1].getPiece() == null
                || cells[y][x-1].getPiece().getTeam() != team)) list.add(cells[y][x-1]);
        if(y > 0 && x > 0 && (cells[y-1][x-1].getPiece() == null
                || cells[y-1][x-1].getPiece().getTeam() != team)) list.add(cells[y-1][x-1]);
        if(y > 0 && (cells[y-1][x].getPiece() == null
                || cells[y-1][x].getPiece().getTeam() != team)) list.add(cells[y-1][x]);
        return list;
    }
}