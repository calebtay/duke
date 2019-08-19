import java.util.*;

public class TaskList {
    static List Tasks = new ArrayList();

    public static void addTask(String task){
        Tasks.add(task);
    }

    public static void listTasks(){
        int count = 1;
        for(Object output : Tasks){
            System.out.println( count + ". " + output);
            count++;
        }
    }
}
