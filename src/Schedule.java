import java.util.*;

public class Schedule {

    private Map<String, Map<String, Map<String, String>>> classes;

    public Map<String, Map<String, Map<String, String>>> getPeriod() {
        return classes;
    }

    public Map<String, Map<String, String>> getWeek(String weekType){
        return classes.get(weekType);
    }

    public Map<String, String> getDay(String weekType,String dayType){
        return classes.get(weekType).get(dayType);
    }

    public void setClasses(Map<String, Map<String, Map<String, String>>> classes) {
        this.classes = classes;
    }

    public List<String> getAllDays(String weekType){
        Set<String> allDays = new HashSet<>();
        allDays.addAll(classes.get(weekType).keySet());
        return allDays.stream().toList();
    }

    public List<String> getAllTime(String weekType, String day){
        Set<String> allTime = new HashSet<>();
        allTime.addAll(classes.get(weekType).get(day).keySet());
        return allTime.stream().toList();
    }

    public String getNowClassLink(String weekType, String dayOfWeek, String time){
        if(getAllDays(weekType).contains(dayOfWeek)){
            if(getAllTime(weekType,dayOfWeek).contains(time)){
                System.out.println("Ссылка");
                return getPeriod().get(weekType).get(dayOfWeek).get(time);
            }
        }
        return null;
    }
}
