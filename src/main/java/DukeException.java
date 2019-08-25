public class DukeException extends Exception {
    DukeException(String error, taskType type){
        if( error.equals("empty_description")){
            System.out.println("     ☹ OOPS!!! The description of a "  + type + " cannot be empty.");
        }
    }

}
