package Logic;

import java.util.*;

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
        if (getAllDays(weekType).contains(dayOfWeek)) {
            if (getAllTime(weekType, dayOfWeek).contains(time)) {
                System.out.printf("Ссылка ");
                return getPeriod().get(weekType).get(dayOfWeek).get(time);
            }
        }
        return null;
    }

}
