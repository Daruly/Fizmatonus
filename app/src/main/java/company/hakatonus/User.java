package company.hakatonus;

public class User {
    private String fullName;
    private String email;
    private String number;
    private String liter;
    private String location = null;
    private User() {}

    public User(String fullName, String email, String number, String liter, String location) {
        this.fullName = fullName;
        this.email = email;
        this.number = number;
        this.liter = liter;
        this.location = location;
    }
    public User(String fullName, String email, String number, String liter) {
        this.fullName = fullName;
        this.email = email;
        this.number = number;
        this.liter = liter;
    }
    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        number = number;
    }

    public void setIin(String iin) {
        liter = iin;
    }

    public void setLocation(String location) {
        location = location;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNumber() {
        return number;
    }

    public String getLiter() {
        return liter;
    }

    public String getLocation() {
        return location;
    }
}