package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;

public class Serialization implements Serializable {

    /**
     * read a object in file with the path and class name
     * @param path path to file
     * @param className class of object
     * @param <C> element generic
     * @return a specific element from a Json file
     */
    public static <C> C readJson(String path, Class<C> className) {
        C object = null;
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(path))){
            Gson gson = new Gson();
            object = gson.fromJson(in , className);
        } catch (IOException e) {
            return object;
        }
        return object;
    }

    /**
     * read a object in file with the file and class name
     * @param file file where the element is
     * @param className class of object
     * @param <C> element generic
     * @return a specific element from a Json file
     */
    public static <C> C readJson(File file, Class<C> className) {
        return readJson(file.getPath(), className);
    }

    /**
     * write a object in file with the path and object of class
     * @param path path to file
     * @param object element that we want to write to the file
     * @param <C> Class generic
     */
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
