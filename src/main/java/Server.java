import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Server extends Connection {

    public Server (int port, String host) {
        super(ConnectionType.SERVER, port, host);
    }

    public void startServer(boolean isDispatcher){
        try{
            System.out.println("Server waiting... ");
            clientSocket = serverSocket.accept();
            clientOutStream = new DataInputStream(this.clientSocket.getInputStream());
            String message = this.clientOutStream.readUTF();
            if(isDispatcher){
                splitMessageDispatcher(message);
            }
            else{
                splitMessage(message);
            }
            System.out.println("Mensaje recibido: " + message);

        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated from startServer method on server Class");
        }

    }

    private void splitMessageDispatcher(String message){


        String[] lines = message.split("\n");
        for (String line : lines) {
            String[] aux = line.split(",");


        }



    }

    private void splitMessage(String message){

    }
}
