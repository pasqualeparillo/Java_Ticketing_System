package Model;

/**
 * Users class
 */
public class Agents extends Employee {
    private String Password;
    /**
     * Users Contructor
     */
    public Agents(int agent_ID, String agent_Name, String password, String email) {
        setId(agent_ID);
        setName(agent_Name);
        setEmail(email);
        Password = password;
    };

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
