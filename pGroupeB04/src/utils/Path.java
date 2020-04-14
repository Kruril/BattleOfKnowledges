package utils;

public enum  Path {
    /**
     * path to different json
     */
    FILE_THEME("json/theme/theme.json"),
    FILE_RESOLUTION("json/resolution/resolution.json");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
