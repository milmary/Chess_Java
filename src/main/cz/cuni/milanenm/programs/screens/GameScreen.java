package main.cz.cuni.milanenm.programs.screens;

import java.awt.*;
import java.awt.event.MouseEvent;
import main.cz.cuni.milanenm.programs.manage.Board;
import main.cz.cuni.milanenm.programs.manage.GameManager;
import main.cz.cuni.milanenm.framework.Screen;
import main.cz.cuni.milanenm.util.Game;

/**
 * Represents the main game screen where the chess board and game actions are displayed.
 */
public class GameScreen implements Screen {
    private Board board;
    private GameManager manager;
    private final int gameMode;

    private Rectangle backButtonRect;

    /**
     * Constructs a GameScreen with a specified game mode.
     * @param gameMode The mode of the game (e.g., single player or multiplayer).
     */
    public GameScreen(int gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Initializes the game screen, creating the game manager and board.
     */
    @Override
    public void create() {
        manager = new GameManager(gameMode);
        board = manager.getBoard();
        backButtonRect = new Rectangle(Game.conf.getWidth() - 200, Game.conf.getHeight() - 100, 150, 50);
    }

    /**
     * Updates the game screen, handling game logic and interactions.
     */
    @Override
    public void update() {
        manager.update();
        if (backButtonRect.contains(Game.input.getMouseX(), Game.input.getMouseY()) && Game.input.isMouseDown(MouseEvent.BUTTON1)) {
            Game.application.setScreen(new MenuScreen());
        }
    }

    /**
     * Draws the game screen, including the board, pieces, and UI elements.
     * @param g The graphics context used for drawing.
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Game.conf.getWidth(), Game.conf.getHeight());

        Color green = Color.decode("#167536");
        Color yellow = Color.decode("#DEE395");
        Color color = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i % 2 == 0 && j == 0) color = yellow;
                else if(i % 2 != 0 && j == 0) color = green;
                g.setColor(color);
                g.fillRect(j*75 + 25, i*75 + 25, 75, 75);
                if (color.equals(green)) color = yellow;
                else color = green;
            }
        }

        Font font = g.getFont().deriveFont(Font.BOLD, 25f);
        g.setFont(font);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            g.drawString((char)('A'+i) + "", 55 + i*75, 22);
            g.drawString((i+1)+"", 5, 75 + i*75);
        }

        g.setColor(new Color(0, 0, 0, 60));
        if(Game.input.getMouseX() < 625 && Game.input.getMouseY() < 625)
            g.fillRect((Game.input.getMouseX()-25)/75*75+25,
                    (Game.input.getMouseY()-25)/75*75+25, 75, 75);

        g.setColor(new Color(0, 0, 255, 50));
        if(manager.activeCell != null)
            g.fillRect(manager.activeCell.getX()*75+25, manager.activeCell.getY()*75+25, 75, 75);

        board.draw(g);
        manager.draw(g);

        // Draw the back button
        g.setColor(Color.DARK_GRAY.darker());
        if(backButtonRect.contains(Game.input.getMouseX(), Game.input.getMouseY())){
            g.fillRect(backButtonRect.x-7, backButtonRect.y-7, backButtonRect.width+14, backButtonRect.height+14);
        }
        g.setColor(Color.DARK_GRAY);
        g.fillRect(backButtonRect.x, backButtonRect.y, backButtonRect.width, backButtonRect.height);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Back to Menu", backButtonRect.x + 10, backButtonRect.y + 30);
    }
}