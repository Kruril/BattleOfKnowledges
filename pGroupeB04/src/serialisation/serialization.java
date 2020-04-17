package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;

public class serialization implements Serializable {

    public static <C> C readJson(String path, Class<C> className) {
        C object = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(path))){
            Gson gson = new Gson();
            object = gson.fromJson(in , className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static <C> C readJson(File file, Class<C> className) {
        return readJson(file.getPath(), className);
    }

    public static <C> void writeJson(String path, C object) {
        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path))){
            new GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create()
                    .toJson(object, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
