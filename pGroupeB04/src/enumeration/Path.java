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
    MAIN_SOUND("/asset/audio/ANW3225_08_That-Way-You-Look-At-Me.wav");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
