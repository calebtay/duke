import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filepath;

    public Storage(String filepath) throws IOException{
        Storage.filepath = filepath;
    }

    public static void ReadFile() throws IOException, DukeException, ArrayIndexOutOfBoundsException {
        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            String[] output = sc.nextLine().split(" \\| ");
            if( output[0].equals("D") ){
                Deadline curr = new Deadline(output[2], output[3]);
                if( output[1].equals("1") ) curr.completed = true;
                TaskList.addDeadline(curr);
            } else if( output[0].equals("E") ){
                Event curr = new Event(output[2], output[3]);
                if( output[1].equals("1") ) curr.completed = true;
                TaskList.addEvent(curr);
            } else {
                Task curr = new Task(output[2]);
                if( output[1].equals("1") ) curr.completed = true;
                TaskList.addTask(curr);
            }
        }
    }

    public static void SaveFile() throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Object obj : TaskList.Tasks ) {

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
            //System.out.println(input);
            printWriter.println(input);
        }
        printWriter.close();
    }

    public Storage load(){
        return this;
    }

    public String getFilepath(){
        return filepath;
    }
}
