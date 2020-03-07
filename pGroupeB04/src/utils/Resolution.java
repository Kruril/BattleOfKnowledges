package utils;

import application.Main;
import serialisation.LectureEcriture;

public class Resolution {

    private double width, height;
    private boolean maximize, fullscreen;

    public Resolution() {
    }

    private Resolution(double width, double height) {
        this.width = width;
        this.height = height;
        this.maximize = Main.getStage().isMaximized();
        this.fullscreen = Main.getStage().isFullScreen();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private void setResolution(Resolution resolution) {
        height = resolution.getHeight();
        width = resolution.getWidth();
        maximize = resolution.isMaximize();
        fullscreen = resolution.isFullscreen();
    }

    public void toJson(Double width, Double height) {
        setResolution(new Resolution(width,height));
        LectureEcriture.writeStringResolu(this);
    }

    public void fromJson() {
        this.setResolution(LectureEcriture.readStringResolu());
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
