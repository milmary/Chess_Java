package main.cz.cuni.milanenm.programs.manage;

import java.awt.*;
import main.cz.cuni.milanenm.programs.pieces.Piece;

public class Cell {
    private int x, y;
    private Piece piece;
    private Rectangle rect;

    /**
     * Getter for x-coordinate
     * @return The x-coordinate of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y-coordinate
     * @return The y-coordinate of the cell
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for the piece on the cell
     * @return The piece on the cell, or null if empty
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Setter for the piece on the cell
     * @param piece The new piece to place on the cell
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Getter for rectangle
     * @return The rectangle of the cell
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Constructor to initialize the cell with its coordinates and piece
     * @param x The x-coordinate of the cell
     * @param y The y-coordinate of the cell
     * @param piece The piece on the cell (null if empty)
     */
    public Cell(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        if(piece != null) {
            piece.setX(x);
            piece.setY(y);
        }
        rect = new Rectangle(x*75+25, y*75+25, 75, 75);
    }
}
