package wasdev.sample.servlet;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

import java.io.File;

/**
 * Created by marksalako on 24/02/2017.
 */
public class OCRAlgo {

    public VisualClassification applyOCR(File file) {
        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("<api-key>");

        System.out.println("Classify an image");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(file)
                .build();
        VisualClassification result = service.classify(options).execute();
        System.out.println(result);

        return result;
    }




}
