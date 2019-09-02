import java.io.IOException;

public class Command {
    private taskType type;
    private String info;

    public Command(taskType type, String info){
        this.type = type;
        this.info = info;
    }

    public Command(taskType type){
        this.type = type;
        this.info = null;
    }

    public void executes(TaskList tasks, Storage storage, UI ui) throws IOException, DukeException {
        switch (type) {
            case todo:
                TaskList.addTask(info);
                break;
            case deadline:
                TaskList.addDeadline(info);
                break;
            case event:
                TaskList.addEvent(info);
                break;
            case done:
                TaskList.completedTask(Integer.parseInt(info));
                break;
            case delete:
                TaskList.deleteTask(Integer.parseInt(info));
                break;
            case find:
                TaskList.find(info);
                break;
            case list:
                TaskList.listTasks();
                break;
            case bye:
                ui.exitText();
                break;
            default:
                throw new DukeException("Unexpected value: ", type);
        }
    }

    public Boolean isExit(){
        return type == taskType.bye;
    }
}
