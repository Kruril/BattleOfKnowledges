package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Question;

import java.io.*;

public class LectureEcriture implements Serializable {

    public static Question readString(String fichier) {
        Question question = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(fichier))){
            Gson gson = new Gson();
            question = gson.fromJson(in , Question.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static void writeString(String fichier, Question question) {
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fichier))){
            new GsonBuilder()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(question, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
