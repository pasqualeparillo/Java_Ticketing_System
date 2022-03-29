package Model;

/**
 * Customers class
 */
public class Customers extends Employee {
    private String Department;
    private String Phone;

    public Customers(int customer_ID, String customer_Name, String email, String department, String phone) {
        setId(customer_ID);
        setName(customer_Name);
        setEmail(email);
        Department = department;
        Phone = phone;
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
