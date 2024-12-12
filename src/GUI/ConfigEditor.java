package GUI;

import Logic.JsonIO;
import com.google.gson.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ConfigEditor extends JFrame {
    private JList weekType;
    private JPanel panel1;
    private JList dayOfWeekType;
    private JTextArea timeArea;
    private JTree tree1;
    private JButton delClassBtn;
    private JButton добавитьПаруButton;
    private JButton exitBtn;

    public ConfigEditor() {
        super("Config Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 500);
        try {
            tree1 = new JTree(readStringJsonAsTree(JsonIO.readStringFromFile("classes.json")));
            tree1.setVisible(true);
        }catch (Exception ex) {
            new Error(ex.toString());
        }
    }

    public static void main(String[] args) {
        try {
            new ConfigEditor();
        } catch (Exception ex) {
            new Error(ex.toString());
        }
    }

    public static DefaultMutableTreeNode readStringJsonAsTree(String jsonString) {
            // Пример JSON-строки
            try {
                // Парсим JSON через Gson
                JsonElement jsonElement = JsonParser.parseString(jsonString);

                // Создаем корневой узел
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("classes.json");
                parseJson(jsonElement, root);
                return root;

            } catch (Exception e) {
                throw e;
            }
    }

    /**
     * Рекурсивный метод для разбора JSON с использованием Gson
     *
     * @param jsonElement текущий JSON элемент
     * @param parent      текущий родительский узел
     */
    private static void parseJson(JsonElement jsonElement, DefaultMutableTreeNode parent) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (String key : jsonObject.keySet()) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(key);
                parent.add(child);
                parseJson(jsonObject.get(key), child);
            }
        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode("[" + i + "]");
                parent.add(child);
                parseJson(jsonArray.get(i), child);
            }
        } else if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            parent.add(new DefaultMutableTreeNode(jsonPrimitive.toString()));
        } else if (jsonElement.isJsonNull()) {
            parent.add(new DefaultMutableTreeNode("null"));
        }
    }

    /*public static void main(String[] args) {
        try {
            Schedule schedule = JsonIO.stringAsSchedule(JsonIO.readStringFromFile("classes.json"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

     */
}
