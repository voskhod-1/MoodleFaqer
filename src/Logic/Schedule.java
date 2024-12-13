package Logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Schedule {

    private Map<String, Map<String, Map<String, String>>> classes;

    public Map<String, Map<String, Map<String, String>>> getPeriod() {
        return classes;
    }

    public Map<String, Map<String, String>> getWeek(String weekType) {
        weekType = weekType.toLowerCase();
        return classes.get(weekType);
    }

    public Map<String, String> getDay(String weekType, String dayType) {
        weekType = weekType.toLowerCase();
        dayType = dayType.toUpperCase();
        return classes.get(weekType).get(dayType);
    }

    public void setClasses(Map<String, Map<String, Map<String, String>>> classes) {
        this.classes = classes;
    }

    public List<String> getAllDays(String weekType) {
        weekType = weekType.toLowerCase();
        Set<String> allDays = new HashSet<>();
        allDays.addAll(classes.get(weekType).keySet());
        return allDays.stream().map(String::toUpperCase).toList();
    }

    public List<String> getAllTime(String weekType, String dayOfWeek) {
        weekType = weekType.toLowerCase();
        dayOfWeek = dayOfWeek.toUpperCase();
        Set<String> allTime = new HashSet<>();
        allTime.addAll(classes.get(weekType).get(dayOfWeek).keySet());
        return allTime.stream().toList();
    }

    public String getNowClassLink(String weekType, String dayOfWeek, String time) {
        weekType = weekType.toLowerCase();
        dayOfWeek = dayOfWeek.toUpperCase();
        time = time.trim();
        if (getAllDays(weekType).contains(dayOfWeek)) {
            if (getAllTime(weekType, dayOfWeek).contains(time)) {
                System.out.print("Ссылка ");
                return getPeriod().get(weekType).get(dayOfWeek).get(time);
            }
        }
        return null;
    }

    public boolean removeClass(String weekType, String dayOfWeek, String time) {
        weekType = weekType.toLowerCase();  // Приведение к нижнему регистру для соответствия ключам
        dayOfWeek = dayOfWeek.toUpperCase();  // Приведение к верхнему регистру для соответствия ключам
        time = time.trim();

        // Проверяем, существует ли тип недели
        if (classes.containsKey(weekType)) {
            Map<String, Map<String, String>> week = classes.get(weekType);

            // Проверяем, существует ли день недели
            if (week.containsKey(dayOfWeek)) {
                Map<String, String> day = week.get(dayOfWeek);

                // Проверяем, существует ли запись на указанное время
                if (day.containsKey(time)) {
                    day.remove(time);  // Удаляем занятие по времени
                    System.out.printf("Занятие в %s на %s (%s) удалено.\n", dayOfWeek, time, weekType);

                    // Если после удаления времени день пуст, удаляем день
                    if (day.isEmpty()) {
                        week.remove(dayOfWeek);
                        System.out.printf("День %s удален, так как больше не содержит занятий.\n", dayOfWeek);
                    }

                    // Если после удаления дня неделя пустая, удаляем неделю
                    if (week.isEmpty()) {
                        classes.remove(weekType);
                        System.out.printf("Тип недели %s удален, так как больше не содержит дней.\n", weekType);
                    }
                    try {
                        JsonIO.writeStringToFile(this.saveClassesToString(), "classes.json");
                        System.out.println("Сохранено");
                    }catch (IOException e) {
                        new Error(e.toString());
                    }
                    return true;  // Успешное удаление
                }
            }
        }

        System.out.printf("Занятие в %s на %s (%s) не найдено.\n", dayOfWeek, time, weekType);
        return false;  // Удаление не выполнено
    }

    public boolean addClass(String weekType, String dayOfWeek, String time, String link) {
        weekType = weekType.toLowerCase();  // Приведение к нижнему регистру для соответствия ключам
        dayOfWeek = dayOfWeek.toUpperCase();  // Приведение к верхнему регистру для соответствия ключам
        time = time.trim();

        // Если `classes` еще не инициализировано, создаем его
        if (classes == null) {
            classes = new HashMap<>();
        }

        // Если тип недели не существует, создаем его
        if (!classes.containsKey(weekType)) {
            classes.put(weekType, new HashMap<>());
        }

        // Получаем или создаем карту для недели
        Map<String, Map<String, String>> week = classes.get(weekType);

        // Если день недели не существует, создаем его
        if (!week.containsKey(dayOfWeek)) {
            week.put(dayOfWeek, new HashMap<>());
        }

        // Получаем или создаем карту для дня
        Map<String, String> day = week.get(dayOfWeek);

        // Если в указанное время уже есть занятие, возвращаем false (занятие не добавлено)
        if (day.containsKey(time)) {
            System.out.printf("Ошибка: В %s на %s (%s) уже есть занятие: %s.\n",
                    dayOfWeek, time, weekType, day.get(time));
            return false;
        }

        // Добавляем новое занятие
        day.put(time, link);
        System.out.printf("Занятие \"%s\" добавлено в %s на %s (%s).\n", link, dayOfWeek, time, weekType);
        try {
            JsonIO.writeStringToFile(this.saveClassesToString(), "classes.json");
            System.out.println("Сохранено");
        }catch (IOException e) {
            new Error(e.toString());
        }
        return true;
    }

    private String saveClassesToString() {
        if (classes == null) {
            return "{}"; // Возвращаем пустой JSON, если classes не инициализирован
        }

        // Создаем экземпляр Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String output = "{\n\t\"classes\": " + gson.toJson(classes)+"\n}";
        // Преобразуем объект classes в строку JSON
        System.out.println(output);
        return output;
    }
    
}
