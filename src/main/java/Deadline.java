public class Deadline extends Task {
    private String deadline = null;
    private Date date = null;

    Deadline(String name, String deadline) throws ArrayIndexOutOfBoundsException{
        super(name);
        if(Date.isDate(deadline)){
            this.deadline = "DATE";
            date = new Date(deadline);
        } else this.deadline = deadline;
    }

    public String checkDeadline(){
        return deadline;
    }

    public String returnDate(){
        return date.returnDate();
    }

}
