package model;

public class Courier {
    private int id;
    private String sender;
    private String receiver;
    private String origin;
    private String destination;
    private String status;

    public Courier(int id, String sender, String receiver, String origin, String destination, String status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public Courier(String sender, String receiver, String origin, String destination, String status) {
        this.sender = sender;
        this.receiver = receiver;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public int getId() { return id; }
    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getStatus() { return status; }

    public String toString() {
        return "ID: " + id + ", Sender: " + sender + ", Receiver: " + receiver +
               ", Origin: " + origin + ", Destination: " + destination + ", Status: " + status;
    }
}
