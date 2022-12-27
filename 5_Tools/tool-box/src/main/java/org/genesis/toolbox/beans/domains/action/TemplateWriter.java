package org.genesis.toolbox.beans.domains.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: TemplateWriter
 * @Package org.genesis.toolbox.beans.domains.action
 * @Description: template writer
 * @date 2018/6/8 17:39
 */
public class TemplateWriter {
    public static void writeTempLine(String tempLocation, String templateFile, String placeHolder, String content, BufferedWriter destWriter) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        TemplateWriter.class.getResourceAsStream(tempLocation + templateFile))) ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.replace(placeHolder, content);

                destWriter.write(line);
                destWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTempLine(String tempLocation, String templateFile, HashMap<String, String> replaceMap, BufferedWriter destWriter) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        TemplateWriter.class.getResourceAsStream(tempLocation + templateFile))) ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                for (String key:replaceMap.keySet()) {
                    line = line.replace(key, replaceMap.get(key));
                }

                destWriter.write(line);
                destWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
