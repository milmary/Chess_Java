package main.cz.cuni.milanenm.programs.manage;

import java.awt.*;
import main.cz.cuni.milanenm.programs.pieces.*;
import main.cz.cuni.milanenm.programs.player.Player;
import main.cz.cuni.milanenm.programs.player.Team;

public class Board {
    private final Cell[][] cells;
    private final Player player;
    private final Player opponent;
    private GameManager manager;


    /**
     * Getter for cells array
     * @return The 2D array of cells on the board
     */
    public Cell[][] getCells() {
        return cells;
    }


    /**
     * Constructor to initialize board with players and game manager
     * @param player The player controlling the white pieces
     * @param opponent The player controlling the black pieces
     * @param manager The game manager
     */
    public Board(Player player, Player opponent, GameManager manager) {
        this.player = player;
        this.opponent = opponent;
        this.manager = manager;
        cells = new Cell[8][8];
        initBoard();

        // Assign pieces to players
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j].getPiece() != null) {
                    if(cells[i][j].getPiece().getTeam() == player.getTeam())
                        player.getPieces().add(cells[i][j].getPiece());
                    else opponent.getPieces().add(cells[i][j].getPiece());
                }
            }
        }
    }

    /**
     * Draw the board and pieces
     * @param g The Graphics2D object for drawing
     */
    public void draw(Graphics2D g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j].getPiece() != null) {
                    Piece piece = cells[i][j].getPiece();
                    g.drawImage(piece.getImage(),
                            piece.getX()*75+25, piece.getY()*75+25,75,75, null);
                }
            }
        }
    }


    /**
     * Initialize the board with pieces in starting positions
     */
    private void initBoard() {
        // Initialize pawns
        for (int i = 0; i < 8; i++) {
            cells[1][i] = new Cell(i, 1, new Pawn(opponent.getTeam(), cells, false));
            cells[6][i] = new Cell(i, 6, new Pawn(player.getTeam(), cells, true));
        }
        // Initialize other pieces
        for (int i = 0; i < 2; i++) {
            Team team = i == 0 ? opponent.getTeam() : player.getTeam();
            int y = i == 0 ? 0 : 7;
            cells[y][0] = new Cell(0, y, new Rook(team, cells));
            cells[y][1] = new Cell(1, y, new Knight(team, cells));
            cells[y][2] = new Cell(2, y, new Bishop(team, cells));
            cells[y][3] = new Cell(3, y, new Queen(team, cells));
            cells[y][4] = new Cell(4, y, new King(team, cells));
            cells[y][5] = new Cell(5, y, new Bishop(team, cells));
            cells[y][6] = new Cell(6, y, new Knight(team, cells));
            cells[y][7] = new Cell(7, y, new Rook(team, cells));
        }
        // Initialize empty cells
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i+2][j] = new Cell(j, i+2, null);
            }
        }
    }
}
