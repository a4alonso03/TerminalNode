import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Server extends Connection {

    Controller controller;

    public Server (int port, String host, Controller controller) {
        super(ConnectionType.SERVER, port, host);
        this.controller = controller;
    }

    public void startServer(){
        try{
            while(true) {
                System.out.println("Server waiting... ");
                clientSocket = serverSocket.accept();
                System.out.println("Server connection accepted");
                clientOutStream = new DataInputStream(this.clientSocket.getInputStream());
                String message = this.clientOutStream.readUTF();
                System.out.println("Server message received:" + message);
                this.checkAction(message.split("\n"));
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated from startServer method on server Class");
        }

    }

    private void checkAction(String[] splitMessage){
        int actionType = Integer.parseInt(splitMessage[2].trim());
        switch (actionType){
            case 0://Normal message
                this.receiveMessage(splitMessage);
                break;
            case 3://I do know that IP, its me (mario)
                break;

            case 4://I do know a way towards that ip
                this.getDistanceData(splitMessage);
                break;

            case 7://Dispatcher functionality
                String data = splitMessage[3];
                this.dispatcherMessage(data);
                break;

        }
    }


    private void dispatcherMessage(String input){
        String[] netComponents = input.split("#");
        String[] aux;
        for(String netComponent : netComponents){
            aux = netComponent.split(",");
            NetComponent comp = new NetComponent(aux);
            if(!comp.getRealIp().equals(SystemVar.myRealIP)) {
                this.controller.getDataTable().add(comp);
            }
        }

    }

    private void getDistanceData(String[] splitMessage){
        //IP fuente
        //Ip destino
        //acción
        //mensaje (distancia)
        this.controller.getDistanceTable().put(splitMessage[0], Integer.parseInt(splitMessage[3].trim()));
    }

    private void receiveMessage(String[] data){
        System.out.println(data[3]);
    }
}
