package main.cz.cuni.milanenm.programs;

import main.cz.cuni.milanenm.programs.screens.MenuScreen;
import main.cz.cuni.milanenm.framework.Application;
import main.cz.cuni.milanenm.util.Configuration;

public class Main {
    /**
     * The main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setFPS(60);
        config.setWidth(875);
        config.setHeight(650);
        config.setShowFPS(false);
        config.setTitle("Chess");
        new Application(config).setScreen(new MenuScreen());
    }
}