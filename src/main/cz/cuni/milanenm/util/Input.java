package main.cz.cuni.milanenm.util;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private boolean[] keys = new boolean[256];
    private boolean[] releasedKeys = new boolean[256];
    private boolean[] buttons = new boolean[5];
    private boolean[] releasedButtons = new boolean[5];
    private int mouseX = 0, mouseY = 0;

    /**
     * Updates the input states, resetting the released keys and buttons.
     */
    public void updateInput() {
        for (int i = 0; i < 256; i++) {
            releasedKeys[i] = false;
        }
        for (int i = 0; i < 5; i++) {
            releasedButtons[i] = false;
        }
    }

    /**
     * Checks if a key is currently pressed down.
     *
     * @param code the key code to check
     * @return true if the key is pressed, false otherwise
     */
    public boolean isKeyDown(int code) {
        return keys[code];
    }

    /**
     * Checks if a mouse button is currently pressed down.
     *
     * @param code the mouse button code to check
     * @return true if the mouse button is pressed, false otherwise
     */
    public boolean isMouseDown(int code) {
        return buttons[code];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        releasedKeys[e.getKeyCode()] = true;
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasedButtons[e.getButton()] = true;
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Gets the current X position of the mouse cursor.
     *
     * @return the X position of the mouse cursor
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Gets the current Y position of the mouse cursor.
     *
     * @return the Y position of the mouse cursor
     */
    public int getMouseY() {
        return mouseY;
    }
}