import java.util.*;

public class Duke {

    private static Boolean exit = false;

    public static void main(String[] args) {
        String logo =  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(!exit) {
            System.out.println("    ____________________________________________________________");
            try{
                readInput();
            } catch (IllegalArgumentException a){
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void readInput(){
        Scanner S = new Scanner(System.in);
        String[] inputString = S.nextLine().split(" ", 2);
        taskType curr = taskType.valueOf(inputString[0]);

        System.out.println("    ____________________________________________________________");


        try {
            if(inputString[1].startsWith(" ") || inputString[1].isEmpty()){
                throw new DukeException("empty_description", curr);
            }

            switch (curr) {
                case todo:
                    TaskList.addTask(inputString[1], curr);
                    break;

                case list:
                    TaskList.listTasks();
                    break;

                case deadline:
                    TaskList.addDeadline(inputString[1], curr);
                    break;

                case event:
                    TaskList.addEvent(inputString[1], curr);
                    break;

                case done:
                    TaskList.completedTask(Integer.parseInt(inputString[1]));
                    break;

                case bye:
                    System.out.println("Bye. Hope to see you again soon!");
                    exit = true;
                    break;

                default:
                    throw new DukeException("Unexpected value: ", curr);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("     ☹ OOPS!!! The description of a " + curr + " cannot be empty.");
        } catch (DukeException ignored){

        }
    }

}
