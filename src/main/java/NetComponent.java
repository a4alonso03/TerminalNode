public class NetComponent {
    private String realIp;
    private String fakeIp;
    private Integer port;


    @Override
    public String toString() {
        return realIp + '\n' + fakeIp + '\n' + port;
    }

    public NetComponent(String[] data) {
        //type (0) + realIP (1) + fakeIP (2) + port (3)
        this.realIp = data[0];
        this.fakeIp = data[1];
        this.port = Integer.parseInt(data[2]);
    }

    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public String getFakeIp() {
        return fakeIp;
    }

    public void setFakeIp(String fakeIp) {
        this.fakeIp = fakeIp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
