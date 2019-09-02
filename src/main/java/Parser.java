public class Parser {

    public static Command parse(String fullCommand) {
        try {
            String[] inputString = fullCommand.split(" ", 2);
            taskType curr = taskType.valueOf(inputString[0]);
            Command c = new Command(curr, inputString[1]);
            return c;
        } catch (ArrayIndexOutOfBoundsException a){
            Command c = new Command(taskType.valueOf(fullCommand));
            return c;
        }

    }

}
