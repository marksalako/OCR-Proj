package wasdev.sample.servlet;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("Hello Commerzbank!");

        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("<api-key>");
        try {
        File file = new File(System.getProperty("user.dir"));
response.getWriter().println(file.getAbsolutePath());
        response.getWriter().println(Arrays.asList(file.list()));
        System.out.println("Classify an image");


            ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                    .images(new File("classes/IMG_3543.JPG"))
                    .build();
            VisualClassification result = service.classify(options).execute();
            response.getWriter().print(result);
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }

    }

}
