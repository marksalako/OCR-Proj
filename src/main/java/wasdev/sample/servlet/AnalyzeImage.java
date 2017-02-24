package wasdev.sample.servlet;

import com.baidu.aip.ocr.AipOcr;
import java.util.*;
import org.json.JSONObject;
/**
 * Created by yeshu on 24/2/2017.
 */
public class AnalyzeImage {
    public static final String APP_ID = "9320361";
    public static final String API_KEY = "72r6V7PGubdTwgwPek2dSGb5";
    public static final String SECRET_KEY = "Rj0k181Uko4uobYuM1MPCKGnh7uRw60o";

    public String analyze(String filePath) {
        // 初始化一个OcrClient
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        String genFilePath = filePath;
        org.json.JSONObject genRes = client.general(genFilePath, new HashMap<String, String>());
        return genRes.toString(2);
    }

}
