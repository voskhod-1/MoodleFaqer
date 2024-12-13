package GUI;

import Logic.JsonIO;
import Logic.MoodleUser;
import com.google.gson.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ConfigEditor extends JFrame {
    private JList<String> weekType; // Должен быть связан с компонентом в GUI Designer
    private JPanel panel1;         // Панель, связанная с Designer
    private JTree tree;
    private JList<String> dayOfWeekType; // Должен быть связан с компонентом в GUI Designer
    private JTextField timeField;
    private JTextField linkField;
    private JButton delClassBtn;
    private JButton addClassButton;
    private JButton exitBtn;

    static String[] weekTypes = {"numerator", "denominator"};
    static String[] dayOfWeekTypes = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

    public ConfigEditor(MoodleUser user) {
        super("Config Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 800);
        setContentPane(panel1);

        try {
            // Устанавливаем модели данных для JList
            weekType.setListData(weekTypes);
            dayOfWeekType.setListData(dayOfWeekTypes);

            Logic.Schedule schedule = JsonIO.stringAsSchedule(JsonIO.readStringFromFile("classes.json"));
            tree.setModel(new javax.swing.tree.DefaultTreeModel(readStringJsonAsTree(JsonIO.readStringFromFile("classes.json"))));
            System.out.println(readStringJsonAsTree(JsonIO.readStringFromFile("classes.json")));

            List<String> pathList = new ArrayList<>();

            // Слушатель для выбора узлов в дереве
            tree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    // Очищаем список pathList
                    pathList.clear();

                    // Проверяем, что есть выделенный путь
                    if (tree.getSelectionPath() != null) {
                        pathList.addAll(
                                Arrays.stream(tree.getSelectionPath()
                                                .toString()
                                                .replace("[", "")
                                                .replace("]", "")
                                                .split(","))
                                        .map(String::trim)
                                        .toList()
                        );
                        System.out.println(pathList); // Выводим путь для отладки
                    } else {
                        System.out.println("Ничего не выделено");
                    }
                }
            });

            // Обработчик кнопки удаления
            delClassBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Удаление");

                    // Проверяем, что есть выделенный путь и он содержит как минимум 5 элементов
                    if (pathList.size() >= 5) {
                        schedule.removeClass(pathList.get(2), pathList.get(3), pathList.get(4));
                        try {
                            tree.setModel(new javax.swing.tree.DefaultTreeModel(
                                    readStringJsonAsTree(JsonIO.readStringFromFile("classes.json"))
                            ));
                        } catch (Exception ex) {
                            new Error(ex.toString());
                        }
                    } else {
                        System.out.println("Недостаточно данных для удаления. Проверьте выделение.");
                    }
                }
            });

            // Обработчик кнопки добавления
            addClassButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Добавление");
                    try {
                        // Получаем выбранные элементы из списков
                        String selectedWeekType = weekType.getSelectedValue();
                        String selectedDayOfWeek = dayOfWeekType.getSelectedValue();

                        if (selectedWeekType == null || selectedDayOfWeek == null || timeField.getText().isEmpty() || linkField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(ConfigEditor.this, "Заполните все поля", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Добавляем занятие
                        schedule.addClass(selectedWeekType, selectedDayOfWeek, timeField.getText(), linkField.getText());

                        // Обновляем модель дерева
                        tree.setModel(new javax.swing.tree.DefaultTreeModel(readStringJsonAsTree(JsonIO.readStringFromFile("classes.json"))));
                    } catch (Exception ex) {
                        new Error(ex.toString());
                    }
                }
            });

            // Обработчик кнопки выхода
            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Menu(user);
                }
            });

        } catch (Exception ex) {
            new Error(ex.toString());
        }
    }
    public static DefaultMutableTreeNode readStringJsonAsTree(String jsonString) {
        try {
            JsonElement jsonElement = JsonParser.parseString(jsonString);
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("classes.json");
            parseJson(jsonElement, root);
            return root;
        } catch (Exception e) {
            throw e;
        }
    }

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
}