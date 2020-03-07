package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Deck;
import utils.Resolution;


import java.io.*;

public class LectureEcriture implements Serializable {

    public static Deck readStringDeck(String fichier) {
        Deck deck = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(fichier))){
            Gson gson = new Gson();
            deck = gson.fromJson(in , Deck.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deck;
    }

    public static void writeStringDeck(String fichier, Deck deck) {
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fichier))){
            new GsonBuilder()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(deck, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Resolution readStringResolu() {
        Resolution resolution = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream("pGroupeB04/src/json/resolution/resolution.json"))){
            Gson gson = new Gson();
            resolution = gson.fromJson(in , Resolution.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resolution;
    }

    public static void writeStringResolu(Resolution resolution) {
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("pGroupeB04/src/json/resolution/resolution.json"))){
            new GsonBuilder()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(resolution, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
