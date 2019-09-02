import java.util.Scanner;

public class UI {
    String logo =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void showWelcome(){
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine(){
        System.out.println("    ____________________________________________________________");
    }

    public String readCommand(){
        Scanner S = new Scanner(System.in);
        return S.nextLine();
    }

    public String showLoadingError(){
        return "gg";
    }

    public void exitText(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void AddTask(){ System.out.println("Got it. I've added this task:"); }

    public static void printNoTasks(){

    }

}
