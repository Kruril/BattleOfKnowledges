package enumeration;

public enum  Path {
    /**
     * path to different json
     */

    // Theme
    FILE_THEME("json/theme/theme.json"),

    //Settings
    FILE_RESOLUTION("json/resolution/resolution.json"),
    FILE_CONNECTION("json/user/"),

    //Audio
    MAIN_SOUND("/asset/audio/main_music.wav");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
