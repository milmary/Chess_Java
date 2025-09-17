package main.cz.cuni.milanenm.util;

public class Configuration {
    private int width, height;
    private int FPS;
    private String title;
    private boolean showFPS;

    /**
     * Default constructor initializing configuration with default values.
     */
    public Configuration() {
        width = 320;
        height = 240;
        FPS = 60;
        title = "Screen";
        showFPS = false;
    }

    /**
     * Gets the width of the configuration.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the configuration.
     *
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the configuration.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the configuration.
     *
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the frames per second (FPS) of the configuration.
     *
     * @return the FPS
     */
    public int getFPS() {
        return FPS;
    }

    /**
     * Sets the frames per second (FPS) of the configuration.
     *
     * @param FPS the FPS to set
     */
    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    /**
     * Gets the title of the configuration.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the configuration.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if FPS display is enabled in the configuration.
     *
     * @return true if FPS display is enabled, false otherwise
     */
    public boolean isShowFPS() {
        return showFPS;
    }

    /**
     * Sets the FPS display status in the configuration.
     *
     * @param showFPS true to show FPS, false to hide FPS
     */
    public void setShowFPS(boolean showFPS) {
        this.showFPS = showFPS;
    }
}
