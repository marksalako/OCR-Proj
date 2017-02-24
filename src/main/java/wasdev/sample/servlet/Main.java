package wasdev.sample.servlet;

/**
 * Created by marksalako on 24/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        AnalyzeImage image = new AnalyzeImage();
        String s = image.analyze("/Users/marksalako/Documents/OCR Proj/OCR+Proj/OCR-Proj/src/main/webapp/images/IMG_3543.JPG");

        System.out.println("ressult: " +s);


    }
}
