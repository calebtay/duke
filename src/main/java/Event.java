public class Event extends Task {
    private String event = null;

    Event(String name, String event) {
        super(name);
        this.event = event;
    }

    public String checkEvent(){
        return event;
    }

}