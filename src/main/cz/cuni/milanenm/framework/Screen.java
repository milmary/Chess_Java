package main.cz.cuni.milanenm.framework;

import java.awt.*;

// Interface for game screens
public interface Screen {
    void create();
    void update();
    void draw(Graphics2D g);
}