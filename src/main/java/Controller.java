public class Controller {


    public Controller(){

    }


    public void start(){
        connectToDispatcher();
    }



    public void connectToDispatcher(){
        Client client = new Client(ConnectionType.CLIENT, SystemVar.dispatcherPort, SystemVar.dispatcherIP);
        client.sendToClient("1" + '\n' + SystemVar.myRealIP + '\n' + SystemVar.myFakeIP  + '\n' + SystemVar.mySendPort);
        Server server = new Server(SystemVar.mySendPort, "localhost");
        server.startServer(true);
    }
}
