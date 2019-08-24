import java.util.*;

class TaskList {
    private static ArrayList<Object> Tasks = new ArrayList<>();

    static void addTask(String task, taskType type){
        Task input = new Task(task);
        Tasks.add(input);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
    }

    static void addDeadline(String task, taskType type){
        Deadline input = new Deadline(task.split(" /by ")[0] , task.split(" /by ")[1] );
        System.out.println(task.split(" /by ")[0] + " (by: " + task.split(" /by ")[1] + ")");
        Tasks.add(input);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
    }

    static void addEvent(String task, taskType type){
        Event input = new Event(task.split(" /at ")[0] , task.split(" /at ")[1] );
        System.out.println(task.split(" /at ")[0] + " (at: " + task.split(" /at ")[1] + ")");
        Tasks.add(input);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
    }

    static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Object obj : Tasks) {
            System.out.print(count + ".");

            taskType curType;

            if( obj instanceof Deadline ){
                System.out.print("[D]");
                curType = taskType.deadline;
            } else if( obj instanceof Event ){
                System.out.print("[E]");
                curType = taskType.event;
            } else {
                System.out.print("[T]");
                curType = taskType.todo;
            }

            Task curTask = (Task) obj;
            if( curTask.completed ){
                System.out.print("[✓] ");
            } else System.out.print("[✗] ");
            System.out.print(curTask.name);

            if (curType == taskType.deadline) {
                System.out.println(" (by: " + ((Deadline) obj).checkDeadline() + ")");
            } else if (curType == taskType.event) {
                System.out.println(" (at: " + ((Event) obj).checkEvent() + ")");
            } else {
                System.out.println("");
            }
            ++count;
        }
    }

    static void completedTask(int index){
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  [✓] ");
        Task curTask = (Task) Tasks.get(index-1);
        System.out.println( curTask.name );
        curTask.completed();
    }
}
