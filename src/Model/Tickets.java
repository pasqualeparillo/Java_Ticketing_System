package Model;

import java.sql.Timestamp;

/**
 * Appointment Class
 */
public class Tickets {
    private int Ticket_ID;
    private String Title;
    private String Description;
    private String Note;
    private String Type;
    private String Status;
    private String Priority;
    private int Customer_ID;
    private int Agent_ID;

    public Tickets(int ticket_ID, String title, String description, String note, String type, String status, String priority, int customer_ID, int agent_ID) {
        Ticket_ID = ticket_ID;
        Title = title;
        Description = description;
        Note = note;
        Type = type;
        Status = status;
        Priority = priority;
        Customer_ID = customer_ID;
        Agent_ID = agent_ID;
    }

    public int getTicket_ID() {
        return Ticket_ID;
    }

    public void setTicket_ID(int ticket_ID) {
        Ticket_ID = ticket_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public int getAgent_ID() {
        return Agent_ID;
    }

    public void setAgent_ID(int agent_ID) {
        Agent_ID = agent_ID;
    }
}