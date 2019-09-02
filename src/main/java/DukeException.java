import java.io.IOException;

public class DukeException extends Exception {
    DukeException(String error, taskType type){
        if( error.equals("empty_description")){
            System.out.println("     ☹ OOPS!!! The description of a "  + type + " cannot be empty.");
        }
    }

    DukeException(String error){
        if( error.equals("unknown_command") ){
            System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
