import java.io.*;
import java.util.*;

class TaskList {
    public static ArrayList<Object> Tasks = new ArrayList<>();

    TaskList(Storage storage) throws DukeException, IOException {
        Storage.ReadFile();
    }

    TaskList(){
    }

    public static ArrayList<Object> getTasks(){
        return Tasks;
    }

    public static void addTask(String task) throws IOException, DukeException {

        if( isBlank(task) ){
            throw new DukeException("empty_description", taskType.todo);
        } else {
            UI.AddTask();
            System.out.println("  [T][✗] " + task);
            Task input = new Task(task);
            Tasks.add(input);
            System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
            Storage.SaveFile();
        }

    }

    public static void addDeadline(String task) throws IOException, DukeException {

        if( isBlank(task) ){
            throw new DukeException("empty_description");
        } else {
            UI.AddTask();
            System.out.print("  [D][✗] ");
            Deadline input = new Deadline(task.split(" /by ")[0] , task.split(" /by ")[1] );
            if( input.isDate ){
                System.out.println(task.split(" /by ")[0] + " (by: " + input.returnDate() + ")");
            } else {
                System.out.println(task.split(" /by ")[0] + " (by: " + task.split(" /by ")[1] + ")");
            }
            Tasks.add(input);
            Storage.SaveFile();
        }

    }

    public static void addEvent(String task) throws IOException, DukeException {

        if( isBlank(task) ){
            throw new DukeException("empty_description");
        } else {
            UI.AddTask();
            System.out.print("  [E][✗] ");
            Event input = new Event(task.split(" /at ")[0] , task.split(" /at ")[1] );
            if( input.isDate ){
                System.out.println(task.split(" /at ")[0] + " (at: " + input.returnDate() + ")");
            } else {
                System.out.println(task.split(" /at ")[0] + " (at: " + task.split(" /at ")[1] + ")");
            }
            Tasks.add(input);
            System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
            Storage.SaveFile();
        }

    }

    public static void addDeadline(Deadline d){ Tasks.add(d); }

    public static void addEvent(Event e){ Tasks.add(e); }

    public static void addTask(Task t){
        Tasks.add(t);
    }

    private static void printINFO(Object obj){
        taskType curType;

        if( obj instanceof Deadline ){
            System.out.print("  [D]");
            curType = taskType.deadline;
        } else if( obj instanceof Event ){
            System.out.print("  [E]");
            curType = taskType.event;
        } else {
            System.out.print("  [T]");
            curType = taskType.todo;
        }

        Task curTask = (Task) obj;
        if( curTask.completed ){
            System.out.print("[✓] ");
        } else System.out.print("[✗] ");
        System.out.print(curTask.name);

        if (curType == taskType.deadline) {
            if(((Deadline) obj).isDate){
                System.out.println(" (by: " + ((Deadline) obj).returnDate() + ")");
            } else {
                System.out.println(" (by: " + ((Deadline) obj).checkDeadline() + ")");
            }
        } else if (curType == taskType.event) {
            if(((Event) obj).isDate){
                System.out.println(" (at: " + ((Event) obj).returnDate() + ")");
            } else {
                System.out.println(" (at: " + ((Event) obj).checkEvent() + ")");
            }
        } else {
            System.out.println("");
        }
    }

    static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Object obj : Tasks) {
            System.out.print(count + ".");
            printINFO(obj);
            ++count;
        }
    }

    static void completedTask(int index) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  [✓] ");
        Task curTask = (Task) Tasks.get(index-1);
        System.out.println( curTask.name );
        curTask.completed();
        Storage.SaveFile();
    }

    static void deleteTask(int index) throws IOException {
        System.out.println("Noted. I've removed this task:");
        Object obj = Tasks.get(index-1);
        printINFO(obj);

        Tasks.remove(obj);

        Storage.SaveFile();
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
    }

    static void find(String word){
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Object obj : Tasks) {
            Task currTask = (Task) obj;
            if( currTask.name.contains(word) ){
                System.out.print(count + ".");
                printINFO(obj);
                ++count;
            }
        }
    }

    private static Boolean isBlank(String s){
        if (s == null) return true;

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
