import java.util.*;

public class Duke {

    public static void main(String[] args) {
        String logo =  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean exit = false;

        while(!exit){
            System.out.println("    ____________________________________________________________");
            Scanner S = new Scanner(System.in);
            String[] inputString = S.nextLine().split(" ", 2 );
            taskType curr = taskType.valueOf(inputString[0]);
            System.out.println("    ____________________________________________________________");


           switch(curr){
               case todo:
                   System.out.println("Got it. I've added this task:");
                   System.out.println("  [T][✗] " + inputString[1]);
                   TaskList.addTask(inputString[1], curr);
                   break;

               case list:
                   TaskList.listTasks();
                  break;

               case deadline:
                   System.out.println("Got it. I've added this task:");
                   System.out.print("  [D][✗] ");
                   TaskList.addDeadline(inputString[1], curr);
                   break;

               case event:
                   System.out.println("Got it. I've added this task:");
                   System.out.print("  [E][✗] ");
                   TaskList.addEvent(inputString[1], curr);
                   break;

               case done:
                   TaskList.completedTask(Integer.parseInt(inputString[1]));
                   break;

               case bye:
                   System.out.println("Bye. Hope to see you again soon!");
                   exit =true;
                   break;

               default:
                   System.out.println("error");
                   throw new IllegalStateException("Unexpected value: " + curr);
           }
        }

    }

}
