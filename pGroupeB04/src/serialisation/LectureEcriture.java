package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Deck;
import model.Question;


import java.io.*;

public class LectureEcriture implements Serializable {

    public static Deck readString(String fichier) {
        Deck deck = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(fichier))){
            Gson gson = new Gson();
            deck = gson.fromJson(in , Deck.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deck;
    }

    public static void writeString(String fichier, Deck deck) {
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fichier))){
            new GsonBuilder()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(deck, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
