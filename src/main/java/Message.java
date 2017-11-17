public class Message {
    private String sourceIP;
    private String destinyIP;
    private Integer action;
    private String data;


    @Override
    public String toString() {
        return  sourceIP + '\n' + destinyIP + '\n' + action + '\n' + data;
    }

    public Message(String[] data){
        this.sourceIP = data[0];
        this.destinyIP = data[1];
        this.action = Integer.parseInt(data[2].trim());
        this.data = data[3];
    }

    public Message(String sourceIP, String destinyIP, Integer action, String data){
        this.sourceIP = sourceIP;
        this.destinyIP = destinyIP;
        this.action = action;
        this.data = data;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getDestinyIP() {
        return destinyIP;
    }

    public void setDestinyIP(String destinyIP) {
        this.destinyIP = destinyIP;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
