package main.cz.cuni.milanenm.programs.manage;

import java.util.List;
import main.cz.cuni.milanenm.programs.pieces.Piece;
import main.cz.cuni.milanenm.programs.pieces.King;
import main.cz.cuni.milanenm.programs.pieces.Queen;
import main.cz.cuni.milanenm.programs.pieces.Pawn;
import main.cz.cuni.milanenm.programs.player.Team;
import main.cz.cuni.milanenm.programs.player.Player;

public class MoveController {
    private final GameManager manager;

    /**
     * Constructor to initialize the MoveController with the specified GameManager.
     * @param manager the game manager object
     */
    public MoveController(GameManager manager) {
        this.manager = manager;
    }

    /**
     * Filter moves to avoid checks.
     * @param player the current player
     * @param list the list of possible cells to move to
     * @param from the starting cell
     * @return the filtered list of cells
     */
    public List<Cell> filterByCheck(Player player, List<Cell> list, Cell from) {
        Piece tempFigure = null;
        for (int i = 0; i < list.size(); i++) {
            Cell cell = list.get(i);
            boolean wasRemoved = false;
            if(cell.getPiece() != null) {
                tempFigure = cell.getPiece();
                manager.getOpponent(player).getPieces().remove(tempFigure);
                wasRemoved = true;
            }
            move(from, cell, player);

            if(isCheck(player)) {
                list.remove(cell);
                i--;
            }
            move(cell, from, player);
            cell.setPiece(tempFigure);
            if(wasRemoved) manager.getOpponent(player).getPieces().add(tempFigure);
            tempFigure = null;
        }
        return list;
    }

    /**
     * Move a piece from one cell to another.
     * @param from the starting cell
     * @param to the destination cell
     * @param player the player making the move
     */
    public void move(Cell from, Cell to, Player player) {
        if(from == null || to == null || from.getPiece() == null) return;

        if(from.getPiece().getTeam() == player.getTeam()) {
            if(to.getPiece() != null
                    && to.getPiece().getTeam() == manager.getOpponent(player).getTeam())
                manager.getOpponent(player).getPieces().remove(to.getPiece());

            from.getPiece().setX(to.getX());
            from.getPiece().setY(to.getY());
            to.setPiece(from.getPiece());
            from.setPiece(null);
        }
    }

    /**
     * Check and handle pawn promotion.
     * @param cell the cell containing the pawn to be promoted
     * @param player the player owning the pawn
     */
    public void checkPawn(Cell cell, Player player){
        if (cell.getPiece() instanceof Pawn) {
            if (cell.getPiece().getTeam() == Team.WHITE && cell.getY() == 0) {
                promotePawn(cell, player);
            } else if (cell.getPiece().getTeam() == Team.BLACK && cell.getY() == 7) {
                promotePawn(cell, player);
            }
        }
    }

    /**
     * Handle pawn promotion.
     * @param cell the cell containing the pawn to be promoted
     * @param player the player owning the pawn
     */
    private void promotePawn(Cell cell, Player player) {

        Piece newQueen = new Queen(cell.getPiece().getTeam(), manager.getBoard().getCells());
        newQueen.setX(cell.getX());
        newQueen.setY(cell.getY());

        // Replace the pawn in the player's pieces list
        player.getPieces().remove(cell.getPiece());
        player.getPieces().add(newQueen);

        // Set the new queen on the board
        cell.setPiece(newQueen);
    }

    /**
     * Check if a player is in check.
     * @param player the player to check
     * @return true if the player is in check, false otherwise
     */
    public boolean isCheck(Player player) {
        List<Piece> opponentFigures = manager.getOpponent(player).getPieces();
        for (int i = 0; i < opponentFigures.size(); i++) {
            Piece piece = opponentFigures.get(i);
            for(Cell cell : piece.getAvailableCells()) {
                if(cell.getPiece() != null && cell.getPiece().getTeam() == player.getTeam() &&
                        cell.getPiece() instanceof King) return true;
            }
        }
        return false;
    }

     /**
     * Find the cell containing a specific piece.
     * @param piece the piece to find
     * @param cells the array of cells
     * @return the cell containing the piece
     */
    public Cell findCellByFigure(Piece piece, Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if(cells[i][j].getPiece() != null &&
                        cells[i][j].getPiece().equals(piece)) return cells[i][j];
            }
        }
        return null;
    }
}