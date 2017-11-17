
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Controller {
    private LinkedList<NetComponent> dataTable;
    private Map<String, Integer> distanceTable;


    public Controller(){
        this.setDataTable(new LinkedList<NetComponent>());
        this.setDistanceTable(new TreeMap<String, Integer>());
    }


    public void start(){
        //Split into 2 main threads: Speaking and listening threads
        ListenThread thread = new ListenThread(this);
        thread.start();
        /*----------------------------------------------------------------------------------*/

        connectToDispatcher();
        Scanner scanner = new Scanner(System.in);
        String messageToSend;
        String destinyIP;

        while(true) {
            System.out.println("Please input the desired message to send, or type exit to finish the execution");
            messageToSend = scanner.nextLine();
            if (messageToSend.equals("exit")) {
                System.exit(0);
            }

            System.out.println("Please input the target ip");
            destinyIP = scanner.nextLine();


            String bestRouteIP = this.getBestRoute(destinyIP);


            this.sendMessageToIP(destinyIP, bestRouteIP, messageToSend);
        }
    }



    private void connectToDispatcher(){
        Client client = new Client(SystemVar.dispatcherPort, SystemVar.dispatcherIP);
        //nodeIP, dispatcherIP, 7 (action type), nodePort
        client.sendToClient(SystemVar.myFakeIP + '\n' + SystemVar.dispatcherIP + '\n' + "7" + '\n' + SystemVar.myPort);
    }

    private String getBestRoute(String destinyIP){

            //Client client = new Client(getDataTable().get(0).getPort(), getDataTable().get(0).getRealIp());
            //Message message1 = new Message(SystemVar.myFakeIP, getDataTable().get(0).getFakeIp(), 2, destinyIP);
            //client.sendToClient(message1.toString());


            //Client client2 = new Client(getDataTable().get(1).getPort(), getDataTable().get(1).getRealIp());
            //Message message2 = new Message(SystemVar.myFakeIP, getDataTable().get(1).getFakeIp(), 2, destinyIP);
            //client2.sendToClient(message2.toString());

        this.distanceTable.put(dataTable.get(0).getFakeIp(), 2);
        this.distanceTable.put(dataTable.get(1).getFakeIp(), 5);


        String ip1 = dataTable.get(0).getFakeIp();
        String ip2 = dataTable.get(1).getFakeIp();
        if(distanceTable.get(ip1) > distanceTable.get(ip2)){
            return ip2;
        }
        else{
            return ip1;
        }

    }

    public void sendMessageToIP(String destinyIP, String routerIP, String message){
        NetComponent comp = getNetComponentByIP(routerIP);
        Client client = new Client(comp.getPort(), comp.getRealIp());

        String finalMessage =   comp.getFakeIp() + '\n' +
                                SystemVar.myFakeIP + '\n' +
                                SystemVar. myFakeIP + '\n' +
                                destinyIP + '\n' +
                                "0" +'\n' +
                                message;
        client.sendToClient(finalMessage);

    }

    private NetComponent getNetComponentByIP(String inputIP){
        for (int i = 0; i < dataTable.size(); i++) {
            if(dataTable.get(i).getFakeIp().equals(inputIP)){
                return dataTable.get(i);
            }
        }
        //should never reach this point
        return null;
    }

    public LinkedList<NetComponent> getDataTable() {
        return dataTable;
    }

    public void setDataTable(LinkedList<NetComponent> dataTable) {
        this.dataTable = dataTable;
    }


    public Map<String, Integer> getDistanceTable() {
        return distanceTable;
    }

    public void setDistanceTable(Map<String, Integer> distanceTable) {
        this.distanceTable = distanceTable;
    }
}
