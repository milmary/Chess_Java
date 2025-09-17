package main.cz.cuni.milanenm.programs.screens;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import main.cz.cuni.milanenm.framework.Screen;
import main.cz.cuni.milanenm.util.Game;

/**
 * Represents the menu screen where players can start a game, view rules, or exit.
 */
public class MenuScreen implements Screen  {
    private Rectangle singlePlayerRect;
    private Rectangle multiPlayerRect;
    private Rectangle rulesRect;
    private Rectangle exitRect;

    private final Color green = new Color(0x167536);
    private final Color yellow = new Color(0xDEE395);

    private int inputDelay;
    private boolean rulesButtonPressed;

    /**
     * Initializes the menu screen, setting up button positions and initial states.
     */
    @Override
    public void create() {
        singlePlayerRect = new Rectangle(262, 210, 350, 50);
        multiPlayerRect = new Rectangle(262, 320, 350, 50);
        rulesRect = new Rectangle(337, 430, 200, 50);
        exitRect = new Rectangle(337, 540, 200, 50);
        inputDelay = 20;
        rulesButtonPressed = false;
    }

    /**
     * Updates the menu screen, handling input delay.
     */
    @Override
    public void update() {
        if (inputDelay != 0) {
            inputDelay--;
        }
    }

    /**
     * Draws the menu screen, including buttons and text.
     * @param g The graphics context used for drawing.
     */
    @Override
    public void draw(Graphics2D g) {

        g.setColor(green);
        g.fillRect(0, 0, Game.conf.getWidth(), Game.conf.getHeight());

        g.setColor(yellow.brighter());
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 42));
        g.drawString("Chess Game", 310, 100);

        g.setColor(yellow.darker());
        if(singlePlayerRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) Game.application.setScreen(new GameScreen(1));
            g.fillRect(singlePlayerRect.x-7, singlePlayerRect.y-7, 365, 65);
        } else if(multiPlayerRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) Game.application.setScreen(new GameScreen(2));
            g.fillRect(multiPlayerRect.x-7, multiPlayerRect.y-7, 365, 65);
        } else if(rulesRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) {
                if (!rulesButtonPressed) {
                    rulesButtonPressed = true;
                    try {
                        java.awt.Desktop.getDesktop().browse(URI.create("https://en.wikipedia.org/wiki/Chess"));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                rulesButtonPressed = false;
            }
            g.fillRect(rulesRect.x-7, rulesRect.y-7, 215, 65);
        } else if(exitRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) System.exit(1);
            g.fillRect(exitRect.x-7, exitRect.y-7, 215, 65);
        }

        g.setColor(yellow);
        g.fill(singlePlayerRect);
        g.fill(multiPlayerRect);
        g.fill(rulesRect);
        g.fill(exitRect);

        g.setColor(green.brighter());
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 37));
        g.drawString("Play with a bot", 302, 250);
        g.drawString("Play with a friend", 283, 360);
        g.drawString("Rules", 387, 470);
        g.drawString("Exit", 400, 580);
    }
}
