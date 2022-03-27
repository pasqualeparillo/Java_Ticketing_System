package Model;

/**
 * Customers class
 */
public class Customers extends Employee {
    private int Customer_ID;
    private String Customer_Name;
    private String Email;
    private String Department;
    private String Phone;

    public Customers(int customer_ID, String customer_Name, String email, String department, String phone) {
        setId(customer_ID);
        setName(customer_Name);
        setEmail(email);
        Department = department;
        Phone = phone;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
