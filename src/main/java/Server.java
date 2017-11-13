import java.io.DataInputStream;
import java.io.IOException;

public class Server extends Connection {

    public Server (int port, String host) {
        super(ConnectionType.SERVER, port, host);
    }

    public void startServer(){
        try{
            clientSocket = serverSocket.accept();
            clientOutStream = new DataInputStream(this.clientSocket.getInputStream());
            String message = this.clientOutStream.readUTF();
            System.out.println("Mensaje recibido: " + message);

        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated from startServer method on server Class");
        }

    }
}
