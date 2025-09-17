package main.cz.cuni.milanenm.programs.manage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import main.cz.cuni.milanenm.programs.pieces.Piece;
import main.cz.cuni.milanenm.programs.player.AIController;
import main.cz.cuni.milanenm.programs.player.Move;
import main.cz.cuni.milanenm.programs.player.Player;
import main.cz.cuni.milanenm.programs.player.Team;
import main.cz.cuni.milanenm.programs.screens.MenuScreen;
import main.cz.cuni.milanenm.util.Game;

public class GameManager {
    private final Board board;
    public Cell activeCell;
    private final Cell[][] cells;
    private final MoveController controller;
    private final AIController aiController;
    public Player currentPlayer;
    private final Player player;
    private final Player opponent;
    private Player winner = null;
    private int gameOverTimer;
    private int playerChangeTimer;
    private final int gameMode;
    private int movesCount;

    /**
     * Getter for the board.
     * @return the board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for the move controller.
     * @return the move controller object
     */
    public MoveController getController() {
        return controller;
    }

    /**
     * Method to get the opponent of a given player.
     * @param player the player for whom the opponent is needed
     * @return the opponent player
     */
    public Player getOpponent(Player player) {
        if(player == this.player) return opponent;
        else return this.player;
    }

    /**
     * Constructor to initialize the game manager with the specified game mode.
     * @param gameMode the game mode (e.g., single-player, multiplayer)
     */
    public GameManager(int gameMode) {
        this.gameMode = gameMode;

        player = new Player(Team.WHITE);
        opponent = new Player(Team.BLACK);

        board = new Board(player, opponent, this);
        cells = board.getCells();

        controller = new MoveController(this);

        activeCell = null;
        currentPlayer = player;

        aiController = new AIController(this);

        gameOverTimer = -1;
        playerChangeTimer = 0;
        movesCount = 0;
    }

    /**
     * Method to update the game state.
     * It checks for user input, manages timers, and handles player turns and moves.
     */
    public void update() {
        // Check if ESC key is pressed to return to menu
        if(Game.input.isKeyDown(KeyEvent.VK_ESCAPE))
            Game.application.setScreen(new MenuScreen());

        // Manage player change timer
        if(playerChangeTimer != 0) {
            playerChangeTimer--;
            return;
        }

        // Manage game over timer
        if(gameOverTimer > 0) {
            gameOverTimer--;
            return;
        }

        // Single player mode logic
        if(gameMode == Utils.SINGLE_PLAYER_MODE) {
            if (currentPlayer.equals(player)) {
                inputUpdate();
            } else {
                Move move = aiController.getMove(currentPlayer);

                if(move != null) {
                    controller.move(move.from, move.to, currentPlayer);
                    currentPlayer.lastMoveFrom = move.from;
                    currentPlayer.lastMoveTo = move.to;
                } else winner = getOpponent(currentPlayer);

                currentPlayer = getOpponent(currentPlayer);
                playerChangeTimer = 10;
            }
        } else {
            inputUpdate();
        }

        // Check if current player is in check
        List<Piece> list = currentPlayer.getPieces();
        boolean b = true;
        for (int i = 0; i < list.size(); i++) {
            Piece fig = list.get(i);
            if(!controller.filterByCheck(currentPlayer, fig.getAvailableCells(),
                    controller.findCellByFigure(fig, cells)).isEmpty()) {
                b = false;
                break;
            }
        }
        if(b) winner = getOpponent(currentPlayer);

        if(winner != null && gameOverTimer == -1) gameOverTimer = 200;
    }

    /**
     * Method to draw game elements on the screen.
     * @param g the graphics context
     */
    public void draw(Graphics2D g) {

        // Draw board background
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        if(controller.isCheck(currentPlayer) && winner == null) g.drawString("CHECK", 650, 100);
        if(winner != null) g.drawString("Player " + (winner.equals(player) ? "1 (white)" : "2 (black)") + " WON", 650, 200);

        g.setColor(Color.BLACK);
        String s = currentPlayer.equals(player) ? "YOUR" : "OPPONENT";
        if(gameOverTimer != 0) g.drawString("TURN: " + s, 650, 50);

        // Highlight last move
        if(getOpponent(currentPlayer).lastMoveTo != null) {
            g.setColor(new Color(0x66991702, true));
            g.fill(getOpponent(currentPlayer).lastMoveFrom.getRect());
            g.fill(getOpponent(currentPlayer).lastMoveTo.getRect());
        }

        if(activeCell == null) return;

        // Highlight an active cell
        g.setColor(new Color(0, 0, 255, 50));
        g.fillRect(activeCell.getX()*75+25, activeCell.getY()*75+25, 75, 75);

        // Highlight possible moves
        List<Cell> list = controller.filterByCheck(
                currentPlayer, activeCell.getPiece().getAvailableCells(), activeCell);
        g.setColor(Color.GREEN);
        for(Cell cell : list)
            g.fillOval(cell.getX()*75+45, cell.getY()*75+45, 35, 35);
    }

    /**
     * Method to handle user input for moving pieces.
     */
    private void inputUpdate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j].getRect().contains(Game.input.getMouseX(), Game.input.getMouseY())
                        && Game.input.isMouseDown(MouseEvent.BUTTON1))
                    if(currentPlayer.getPieces().contains(
                            cells[i][j].getPiece())) activeCell = board.getCells()[i][j];
            }
        }

        // Handle piece movement
        if (activeCell != null && Game.input.isMouseDown(MouseEvent.BUTTON1)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (cells[i][j].getRect().contains(Game.input.getMouseX(), Game.input.getMouseY())) {
                        List<Cell> list = controller.filterByCheck(
                                currentPlayer, activeCell.getPiece().getAvailableCells(), activeCell);
                        if (list.contains(cells[i][j])) {
                            controller.move(activeCell, cells[i][j], currentPlayer);

                            currentPlayer.lastMoveFrom = activeCell;
                            currentPlayer.lastMoveTo = cells[i][j];

                            controller.checkPawn(currentPlayer.lastMoveTo, currentPlayer);

                            currentPlayer = getOpponent(currentPlayer);
                            playerChangeTimer = 20;
                            activeCell = null;
                        }
                    }
                }
            }
        }
    }
}