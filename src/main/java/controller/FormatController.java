package controller;

import org.fxmisc.richtext.InlineCssTextArea;

public class FormatController {

    public void formatAndDisplayAIResponse(
            InlineCssTextArea mychatapp_Ai_area,
            String mychatapp_Ai_response) {

        mychatapp_Ai_area.clear();

        for (String mychatapp_Ai_line : mychatapp_Ai_response.split("\n")) {

            mychatapp_Ai_line = mychatapp_Ai_line.trim();

            if (mychatapp_Ai_line.isEmpty()) {
                mychatapp_Ai_area.appendText("\n");
                continue;
            }

            if (mychatapp_Ai_line.endsWith(":") && !mychatapp_Ai_line.contains("*")) {
                mychatapp_Ai_area.appendText(mychatapp_Ai_line + "\n");
                mychatapp_Ai_area.setStyle(
                        mychatapp_Ai_area.getLength() - mychatapp_Ai_line.length() - 1,
                        mychatapp_Ai_area.getLength(),
                        "-fx-font-weight: bold; -fx-font-size: 14px; -fx-fill:rgb(255, 255, 255);"
                );
                continue;
            }

            if (mychatapp_Ai_line.startsWith("*")) {
                String clean = mychatapp_Ai_line.replaceFirst("\\*+", "").trim();
                mychatapp_Ai_area.appendText("• " + clean + "\n");
                mychatapp_Ai_area.setStyle(
                        mychatapp_Ai_area.getLength() - clean.length() - 2,
                        mychatapp_Ai_area.getLength(),
                        "-fx-fill:rgb(255, 255, 255); -fx-font-size: 14px;"
                );
                continue;
            }

            mychatapp_Ai_area.appendText(mychatapp_Ai_line + "\n");
            mychatapp_Ai_area.setStyle(
                    mychatapp_Ai_area.getLength() - mychatapp_Ai_line.length() - 1,
                    mychatapp_Ai_area.getLength(),
                    "-fx-fill: rgb(255, 255, 255); -fx-font-size: 14px;"
            );
        }
    }
}
