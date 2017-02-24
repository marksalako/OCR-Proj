package wasdev.sample.servlet;

import com.google.gson.*;

/**
 * Created by marksalako on 24/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        AnalyzeImage image = new AnalyzeImage();
        String s = image.analyze("/Users/marksalako/Documents/OCR Proj/OCR+Proj/OCR-Proj/src/main/webapp/images/someChinese.jpg");

        //System.out.println("ressult: " +s);

        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        JsonArray words_result = jsonObject.getAsJsonArray("words_result");
        for (JsonElement jsonElement : words_result) {
            JsonObject word2 = jsonElement.getAsJsonObject();
            JsonPrimitive words =(JsonPrimitive) word2.get("words");
            String s4 = words.getAsString();
            System.out.println(s4);
        }


    }
}
