package wasdev.sample.servlet;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marksalako on 24/02/2017.
 */

    @WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
    @MultipartConfig
    public class FileUploadServlet extends HttpServlet {

        private final static Logger LOGGER =
                Logger.getLogger(FileUploadServlet.class.getCanonicalName());

        protected void processRequest(HttpServletRequest request,
                                      HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");

            // Create path components to save the file
            final String path = request.getParameter("destination");
            final Part filePart = request.getPart("file");
            final String fileName = getFileName(filePart);

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                File file = new File(path + File.separator
                        + fileName);
                out = new FileOutputStream(file);
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                writer.println("New file " + fileName + " created at " + path);
                LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                        new Object[]{fileName, path});

                OCRAlgo ocrAlgo = new OCRAlgo();
                VisualClassification result = ocrAlgo.applyOCR(file);

                writer.println("got result for file" + fileName + " result " + result.toString());
            } catch (FileNotFoundException fne) {
                writer.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());

                LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                        new Object[]{fne.getMessage()});
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }

        private String getFileName(final Part part) {
            final String partHeader = part.getHeader("content-disposition");
            LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(
                            content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
        }


    }

