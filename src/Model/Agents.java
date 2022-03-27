package Model;

/**
 * Users class
 */
public class Agents extends Employee {
    private int Agent_ID;
    private String Agent_Name;
    private String Password;
    /**
     * Users Contructor
     */
    public Agents(int agent_ID, String agent_Name, String password, String email) {
        setId(agent_ID);
        setName(agent_Name);
        setEmail(email);
        Agent_ID = agent_ID;
        Agent_Name = agent_Name;
        Password = password;
    };

    public int getAgent_ID() {
        return Agent_ID;
    }

    public void setAgent_ID(int agent_ID) {
        Agent_ID = agent_ID;
    }

    public String getAgent_Name() {
        return Agent_Name;
    }

    public void setAgent_Name(String agent_Name) {
        Agent_Name = agent_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
