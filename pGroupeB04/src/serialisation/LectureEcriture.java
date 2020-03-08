package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Deck;
import utils.Resolution;


import java.io.*;

public class LectureEcriture implements Serializable {

    /**
     * method that reads a deck from a json file
     * @param fichier location and filename
     * @return an object of type Deck
     */
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

    /**
     * method that will transform a Deck object to json format by specifying
     * the file name
     * @param fichier location and filename
     * @param deck object to be transformed
     */
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


    /**
     * method that reads a resolution from a json file
     * @return an object of type Resolution
     */
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


    /**
     *method that will transform a Resolution object to json format by specifying
     *the file name
     * @param resolution object to be transformed
     */
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
