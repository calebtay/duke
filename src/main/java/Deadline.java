public class Deadline extends Task {
    private String deadline = null;
    private Date date = null;
    public Boolean isDate = false;

    Deadline(String name, String deadline) throws ArrayIndexOutOfBoundsException{
        super(name);
        if(Date.isDate(deadline)){
            this.deadline = deadline;
            isDate = true;
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
