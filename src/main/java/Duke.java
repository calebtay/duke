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
            Scanner S = new Scanner(System.in);
            String inputString = S. nextLine();

            if(inputString.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                exit = true;
            } else if(inputString.equals("list")){
                TaskList.listTasks();
            } else if(inputString.contains("done")){
                String[] words = inputString.split(" ", 2 );
                if( words[0].equals("done") && isNumeric(words[1]) ){
                    TaskList.completedTask(Integer.parseInt(words[1]));
                } else {
                    System.out.println( "added: " + inputString);
                    TaskList.addTask(inputString);
                }
            } else {
                System.out.println( "added: " + inputString);
                TaskList.addTask(inputString);
            }
        }
    }

    private static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
