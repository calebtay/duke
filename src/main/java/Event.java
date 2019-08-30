public class Event extends Task {
    private String event = null;
    private Date date = null;
    public Boolean isDate = false;

    Event(String name, String event) {
        super(name);
        if(Date.isDate(event)){
            this.event = event;
            isDate = true;
            date = new Date(event);
        } else this.event = event;
    }

    public String checkEvent(){
        return event;
    }

    public String returnDate(){
        return date.returnDate();
    }

}