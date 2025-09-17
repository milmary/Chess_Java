package main.cz.cuni.milanenm.framework;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import main.cz.cuni.milanenm.util.Configuration;
import main.cz.cuni.milanenm.util.Files;
import main.cz.cuni.milanenm.util.Game;
import main.cz.cuni.milanenm.util.Input;

public class Application extends JPanel implements Runnable {
    public Configuration config;
    private Screen screen;
    public boolean running;
    private static BufferedImage image;
    private static Graphics2D g;

    // Constructor to initialize application settings
    public Application(Configuration configuration) {
        super();
        this.config = configuration;

        // Set preferred size and focus for the panel
        setPreferredSize(new Dimension(configuration.getWidth(), configuration.getHeight()));
        setFocusable(true);
        requestFocus();

        // Create and set up the JFrame
        JFrame window = new JFrame(config.getTitle());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Initialize the image and graphics context
        image = new BufferedImage(configuration.getWidth(),
                configuration.getHeight(), BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
    }

    // Method called when the component is added to a container
    public void addNotify() {
        super.addNotify();

        // Initialize game components
        Game.input = new Input();
        Game.files = new Files();
        Game.conf = config;
        Game.application = this;

        // Add input listeners
        addKeyListener(Game.input);
        addMouseListener(Game.input);
        addMouseMotionListener(Game.input);

        new Thread(this, "Main Game Loop").start();
    }

    // Main game loop
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double FPS = (double) config.getFPS();
        double targetTime = 1000000000 / FPS;
        double delta = 0;
        int frames = 0;
        running = true;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / targetTime;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta--;
            }

            draw();
            drawToScreen();
            frames++;

            // Update FPS counter every second
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                if(config.isShowFPS()) System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

    // Set the current screen and call its create method
    public void setScreen(Screen screen) {
        screen.create();
        this.screen = screen;
    }

    // Update game state
    public void update() {
        if(screen != null) screen.update();
        Game.input.updateInput();
    }

    // Draw the current screen
    public void draw() {
        if(screen != null) screen.draw(g);
    }

    // Draw the buffered image to the screen
    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}