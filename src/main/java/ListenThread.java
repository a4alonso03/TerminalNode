import java.util.LinkedList;

public class ListenThread extends Thread {
    Controller controller;

    public ListenThread(Controller controller){
        this.controller = controller;
    }

    public void run(){
        Server listenServer = new Server(SystemVar.myPort, "localhost", this.controller);
        listenServer.startServer();
    }
}
