public class Main {
    public static void main(String[] args) {
        Client client = new Client(ConnectionType.CLIENT, SystemVar.clientPort, SystemVar.serverIP);
        client.sendToClient("hola");
    }
}
