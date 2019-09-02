import java.io.IOException;

public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private static Boolean exit = false;

    private Duke(String filepath) throws IOException, DukeException {
        try {
            ui = new UI();
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (IOException e){
            tasks = new TaskList();
        }
    }

    private void run() throws DukeException, IllegalArgumentException {

        ui.showWelcome();

        while(!exit) {
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.executes(tasks, storage, ui);
                exit = c.isExit();

            } catch (IllegalArgumentException a){
                throw new DukeException("unknown_command");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DukeException g) {
                g.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException, DukeException {

        new Duke("T:/CS2113T/duke/src/main/data/datafile.txt").run();

    }

}
