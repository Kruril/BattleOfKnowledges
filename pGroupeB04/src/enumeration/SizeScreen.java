package enumeration;

public enum SizeScreen {

    SD("1080*720"),
    HDREADY("1280*720"),
    ELEVENINCH("1280*800"),
    SQUARE("1280*1024"),
    FULLHD("1920*1080"),
    FULLSCREENWBORDER("Fullscreen with border"),
    FULLSCREENWOUTBORDER("Fullscreen without border");

    private String valeur;

    SizeScreen(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}
