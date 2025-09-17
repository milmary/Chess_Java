package main.cz.cuni.milanenm.programs.player;

import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.manage.GameManager;
import main.cz.cuni.milanenm.programs.manage.MoveController;
import main.cz.cuni.milanenm.programs.manage.Utils;
import main.cz.cuni.milanenm.programs.pieces.*;

/**
 * AIController is responsible for determining the best move for the AI player using the Minimax algorithm with alpha-beta pruning.
 */
public class AIController {

    private final GameManager manager;
    private final Cell[][] cells;
    private final MoveController moveController;
    private int movesCount;

    /**
     * Constructor initializes the AIController with the game manager and board state.
     * @param manager The GameManager object managing the game state.
     */
    public AIController(GameManager manager) {
        this.manager = manager;
        this.cells = manager.getBoard().getCells();
        this.moveController = manager.getController();
    }

    /**
     * Determines the best move for the given player.
     * @param player The player (AI) for whom the move is being determined.
     * @return The best move determined by the Minimax algorithm.
     */
    public Move getMove(Player player) {
        return rootMinimax(4, player);
    }

    /**
     * Root level of the Minimax algorithm to find the best move for the player.
     * @param level Depth level for the Minimax algorithm.
     * @param player The current player.
     * @return The best move for the player.
     */
    private Move rootMinimax(int level, Player player) {

        movesCount = 0;

        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        // Iterate over each piece of the player
        for(Piece piece : player.getPieces()) {
            // Iterate over each possible move for the piece
            for(Cell cell : moveController.filterByCheck(player,
                    piece.getAvailableCells(), moveController.findCellByFigure(piece, cells))) {
                Move move = new Move(moveController.findCellByFigure(piece, cells), cell, player);

                // Simulate the move
                Piece tempFigure = move.to.getPiece();
                int index = manager.getOpponent(player).getPieces().indexOf(tempFigure);

                moveController.move(move.from, move.to, move.player);

                // Recursive call to minimax to evaluate the move
                int value = minimax(level-1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, manager.getOpponent(player));
                // Update the best move if a better value is found
                if(value > bestValue) {
                    bestValue = value;
                    bestMove = move;
                }
                // Undo the move
                moveController.move(move.to, move.from, move.player);

                if(index != -1) manager.getOpponent(player).getPieces().add(index, tempFigure);
                move.to.setPiece(tempFigure);
            }
        }
        System.out.println(movesCount);
        return bestMove;
    }

    /**
     * Minimax algorithm with alpha-beta pruning to evaluate the game tree.
     * @param level Depth level for the Minimax algorithm.
     * @param alpha Alpha value for pruning.
     * @param beta Beta value for pruning.
     * @param isMaximizer Boolean indicating if the current player is the maximizer.
     * @param player The current player.
     * @return The evaluation value of the board state.
     */
    private int minimax(int level, int alpha, int beta, boolean isMaximizer, Player player) {
        if(level == 0) {
            if(isMaximizer) return evaluationFunction(player);
            else return evaluationFunction(manager.getOpponent(player));
        }

        int minmax = isMaximizer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Player opponent = manager.getOpponent(player);

        boolean alphaBetaCut = false;
        for(Piece figure : player.getPieces()) {
            if(alphaBetaCut) break;

            for(Cell cell : moveController.filterByCheck(player,
                    figure.getAvailableCells(), moveController.findCellByFigure(figure, cells))) {
                Move move = new Move(moveController.findCellByFigure(figure, cells), cell, player);

                // Simulate the move
                Piece tempFigure = move.to.getPiece();
                int index = opponent.getPieces().indexOf(tempFigure);

                moveController.move(move.from, move.to, move.player);
                if(isMaximizer) {
                    minmax = Math.max(minimax(level-1, alpha, beta, false, opponent), minmax);
                    alpha = Math.max(alpha, minmax);
                } else {
                    minmax = Math.min(minimax(level-1, alpha, beta, true, opponent), minmax);
                    beta = Math.min(beta, minmax);
                }
                if(beta <= alpha) alphaBetaCut = true;
                // Undo the move
                moveController.move(move.to, move.from, move.player);

                if(index != -1) opponent.getPieces().add(index, tempFigure);
                move.to.setPiece(tempFigure);
            }
        }
        return minmax;
    }

    /**
     * Evaluates the board state from the perspective of the given player.
     * @param player The player for whom the evaluation is being done.
     * @return The evaluation score of the board.
     */
    public int evaluationFunction(Player player) {
        movesCount++;
        int sum = 0;

        // Evaluate each piece on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Cell cell = cells[i][j];
                if(cell.getPiece() != null) {
                    Piece figure = cell.getPiece();
                    if(figure instanceof Pawn)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.PAWN : -Utils.PAWN) +
                                (figure.getTeam() == Team.WHITE ? -Utils.PAWN_BONUS[i][j] : Utils.PAWN_BONUS[7 - i][7 - j]);
                    else if(figure instanceof Knight)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.KNIGHT : -Utils.KNIGHT) +
                                (figure.getTeam() == Team.WHITE ? -Utils.KNIGHT_BONUS[i][j] : Utils.KNIGHT_BONUS[7-i][7-j]);
                    else if(figure instanceof Bishop)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.BISHOP : -Utils.BISHOP) +
                                (figure.getTeam() == Team.WHITE ? -Utils.BISHOP_BONUS[i][j] : Utils.BISHOP_BONUS[7-i][7-j]);
                    else if(figure instanceof Rook)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.ROOK : -Utils.ROOK) +
                                (figure.getTeam() == Team.WHITE ? -Utils.ROOK_BONUS[i][j] : Utils.ROOK_BONUS[7-i][7-j]);
                    else if(figure instanceof Queen)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.QUEEN : -Utils.QUEEN) +
                                (figure.getTeam() == Team.WHITE ? -Utils.QUEEN_BONUS[i][j] : Utils.QUEEN_BONUS[7-i][7-j]);
                    else if(figure instanceof King)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.KING : -Utils.KING) +
                                (figure.getTeam() == Team.WHITE ? -Utils.KING_BONUS[i][j] : Utils.KING_BONUS[7-i][7-j]);
                }
            }
        }
        if(moveController.isCheck(manager.getOpponent(player))) sum += 20000;
        return sum;
    }
}