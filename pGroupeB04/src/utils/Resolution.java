package utils;

import application.Main;
import enumeration.Path;
import serialisation.Serialization;

public class Resolution {

    /**
     * Class to remember the screen size the next time the game is restarted.
     */

    private double width, height, x, y;
    private boolean maximize, fullscreen;

    public Resolution() {
    }

    private Resolution(double width, double height) {
        this.width = width;
        this.height = height;
        this.maximize = Main.getStage().isMaximized();
        this.fullscreen = Main.getStage().isFullScreen();
        this.x = Main.getStage().getX();
        this.y = Main.getStage().getY();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Set the different variable of Resolution
     * @param resolution Resolution from json
     */
    private void setResolution(Resolution resolution) {
        height = resolution.getHeight();
        width = resolution.getWidth();
        maximize = resolution.isMaximize();
        fullscreen = resolution.isFullscreen();
        x = resolution.getX();
        y = resolution.getY();
    }

    /**
     * transforms a resolution object into json format
     * @param width stage width
     * @param height stage height
     */
    public void toJson(Double width, Double height) {
        setResolution(new Resolution(width,height));
        Serialization.writeJson(Path.FILE_RESOLUTION.getPath(),this);
    }

    /**
     * transforms a json into a resolution object
     */
    public void fromJson() {
        this.setResolution(Serialization.readJson(Path.FILE_RESOLUTION.getPath(), this.getClass()));
    }

    @Override
    public String toString() {
        return width + "*" + height ;
    }

    public boolean isMaximize() {
        return maximize;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }
}
