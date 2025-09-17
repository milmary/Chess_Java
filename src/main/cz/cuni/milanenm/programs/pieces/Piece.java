package main.cz.cuni.milanenm.programs.pieces;

import java.awt.image.BufferedImage;
import java.util.List;
import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.player.Team;
import main.cz.cuni.milanenm.util.Game;

/**
 * Abstract base class for all chess pieces.
 * Defines common properties and methods for all pieces.
 */
public abstract class Piece {
    protected int x, y;
    protected BufferedImage image;
    protected Team team;
    protected Cell[][] cells;
    protected BufferedImage tileset = Game.files.loadImage("/pics/piecespic.png");

    /**
     * Creates a new Piece.
     *
     * @param team  The team (white or black) this piece belongs to.
     * @param cells The chess board represented as a 2D array of Cells.
     */
    public Piece(Team team, Cell[][] cells) {
        this.team = team;
        this.cells = cells;
    }

    /**
     * Gets the available cells this piece can move to.
     *
     * @return A list of available cells.
     */
    public abstract List<Cell> getAvailableCells();

    /**
     * Adds a cell to the list of available cells if it is empty or contains an opponent's piece.
     *
     * @param list The list of available cells.
     * @param cell The cell to check and potentially add.
     * @return True if a piece is present (either friendly or opponent), otherwise false.
     */
    protected boolean addPiece(List<Cell> list, Cell cell) {
        if(cell.getPiece() == null) list.add(cell);
        else {
            if(cell.getPiece().getTeam() == team) return true;
            else {
                list.add(cell);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the image representing this piece.
     *
     * @return The image of the piece.
     */
    public BufferedImage getImage() { return image; }

    /**
     * Gets the X-coordinate of this piece on the board.
     *
     * @return The X-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-coordinate of this piece on the board.
     *
     * @param x The new X-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y-coordinate of this piece on the board.
     *
     * @return The Y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate of this piece on the board.
     *
     * @param y The new Y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the team this piece belongs to.
     *
     * @return The team (white or black).
     */
    public Team getTeam() {
        return team;
    }
}