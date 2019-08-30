import java.io.*;
import java.time.temporal.TemporalAccessor;
import java.util.*;

class TaskList {
    private static ArrayList<Object> Tasks = new ArrayList<>();

    static void addTask(String task, taskType type) throws IOException {
        Task input = new Task(task);
        Tasks.add(input);
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][✗] " + task);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
        SaveFile();
    }

    static void addDeadline(String task, taskType type) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.print("  [D][✗] ");
        Deadline input = new Deadline(task.split(" /by ")[0] , task.split(" /by ")[1] );
        System.out.println(task.split(" /by ")[0] + " (by: " + task.split(" /by ")[1] + ")");
        Tasks.add(input);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
        SaveFile();
    }

    static void addEvent(String task, taskType type) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.print("  [E][✗] ");
        Event input = new Event(task.split(" /at ")[0] , task.split(" /at ")[1] );
        System.out.println(task.split(" /at ")[0] + " (at: " + task.split(" /at ")[1] + ")");
        Tasks.add(input);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
        SaveFile();
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
        SaveFile();
    }

    static void deleteTask(int index) throws IOException {
        System.out.println("Noted. I've removed this task:");
        Object obj = (Object) Tasks.get(index-1);
        printINFO(obj);

        Tasks.remove(obj);

        SaveFile();
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

    private static void SaveFile() throws IOException {
        FileWriter fileWriter = new FileWriter("T:/CS2113T/duke/src/main/data/datafile.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Object obj : Tasks) {

            taskType curType;
            String input = null;
            if( obj instanceof Deadline ){
                input = "D";
                curType = taskType.deadline;
            } else if( obj instanceof Event ){
                input = "E";
                curType = taskType.event;
            } else {
                input = "T";
                curType = taskType.todo;
            }
            input = input + " | ";
            Task curTask = (Task) obj;
            if( curTask.completed ){
                input = input + "1";
            } else input = input + "0";

            input = input + " | ";
            input = input + curTask.name;

            if (curType == taskType.deadline) {
                input = input + " | " + ((Deadline) obj).checkDeadline();
            } else if (curType == taskType.event) {
                input = input + " | " + ((Event) obj).checkEvent();
            }

            printWriter.println(input);
        }
        printWriter.close();
    }

    public static void ReadFile() throws IOException{
        File file = new File("T:/CS2113T/duke/src/main/data/datafile.txt");
        Scanner sc = new Scanner(file);
        //System.out.println(sc.nextLine());

        while(sc.hasNextLine()){
            String[] output = sc.nextLine().split(" \\| ");
            if( output[0].equals("D") ){
                Deadline curr = new Deadline(output[2], output[3]);
                if( output[1].equals("1") ) curr.completed = true;
                Tasks.add(curr);
            } else if( output[0].equals("E") ){
                Event curr = new Event(output[2], output[3]);
                if( output[1].equals("1") ) curr.completed = true;
                Tasks.add(curr);
            } else {
                Task curr = new Task(output[2]);
                if( output[1].equals("1") ) curr.completed = true;
                Tasks.add(curr);
            }
        }

    }

}
