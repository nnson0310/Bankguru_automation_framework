package serverconfig;

public class InvalidServerNameException extends RuntimeException {
    public InvalidServerNameException(String serverName) {
        super(serverName + " server name is not supported");
    }
}
