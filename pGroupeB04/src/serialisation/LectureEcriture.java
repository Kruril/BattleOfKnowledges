package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Deck;
import utils.JsonManager;
import utils.Resolution;


import java.io.*;

public class LectureEcriture implements Serializable {

    /**
     * method that reads a deck from a json file
     * @return an object of type Deck
     */
    public static Deck readStringDeck() {
        Deck deck = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(JsonManager.FILE_THEME))){
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
     * @param deck object to be transformed
     */
    public static void writeStringDeck(Deck deck) {
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(JsonManager.FILE_THEME))){
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
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(JsonManager.FILE_RESOLUTION))){
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
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(JsonManager.FILE_RESOLUTION))){
            new GsonBuilder()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(resolution, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
