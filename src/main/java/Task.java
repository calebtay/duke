class Task {
    public String name;
    public Boolean completed;
    private static int no_Tasks;

    Task(String name){
        this.name = name;
        this.completed = false;
        int i = ++no_Tasks;
    }

    public void completed(){
        completed = true;
    }

}
