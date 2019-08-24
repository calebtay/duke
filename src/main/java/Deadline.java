public class Deadline extends Task {
    private String deadline = null;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String checkDeadline(){
        return deadline;
    }

}
