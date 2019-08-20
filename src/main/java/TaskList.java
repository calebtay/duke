import java.util.*;

public class TaskList {
    static ArrayList<Task> Tasks = new ArrayList<Task>();

    public static void addTask(String task){
        Task input = new Task(task, false);
        Tasks.add(input);
    }

    public static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        Iterator<Task> ite = Tasks.iterator();
        while(ite.hasNext()){
            System.out.print(count + ".");

            Task curTask = ite.next();
            if(curTask.completed){
                System.out.print("[✓] ");
            } else {
                System.out.print("[✗] ");
            }
            System.out.println(curTask.name);
            count++;
        }
    }

    public static void completedTask(int index){
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  [✓] ");
        int count = 1;
        Iterator<Task> ite = Tasks.iterator();
        while(ite.hasNext()){
            Task curTask = ite.next();
            if(count == index){
                System.out.println(curTask.name);
                curTask.completed = true;
            }
            count++;
        }
    }
}
