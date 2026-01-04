package controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class AiApiController {

    public String callGeminiAPI(String mychatapp_Ai_prompt) {

        String mychatapp_Ai_apiKey = "AIzaSyBm8cH3UGUHLyLDaETixr4lv-IfDCXl1TI";
        StringBuilder mychatapp_Ai_response = new StringBuilder();

        try {
            URL mychatapp_Ai_url = new URL(
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                            + mychatapp_Ai_apiKey);

            HttpURLConnection mychatapp_Ai_conn =
                    (HttpURLConnection) mychatapp_Ai_url.openConnection();

            mychatapp_Ai_conn.setRequestMethod("POST");
            mychatapp_Ai_conn.setRequestProperty(
                    "Content-Type", "application/json; charset=UTF-8");
            mychatapp_Ai_conn.setDoOutput(true);

            JSONObject mychatapp_Ai_jsonRequest = new JSONObject()
                    .put("contents", List.of(
                            Map.of("parts", List.of(
                                    Map.of("text",
                                            mychatapp_Ai_prompt + " Give this in 3 to 4 lines")
                            ))
                    ));

            try (OutputStream os = mychatapp_Ai_conn.getOutputStream()) {
                os.write(mychatapp_Ai_jsonRequest.toString()
                        .getBytes(StandardCharsets.UTF_8));
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            mychatapp_Ai_conn.getInputStream(),
                            StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                mychatapp_Ai_response.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject mychatapp_Ai_jsonResponse =
                new JSONObject(mychatapp_Ai_response.toString());

        return mychatapp_Ai_jsonResponse
                .getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");
    }
}
