package mnnp.homecontrol.model;

/**
 * Created by paulv on 9/18/2017.
 */

public class Loginlist {
    String name;
    String role;
    public Loginlist() {

    }

    public Loginlist(String name, String role)
    {
        this.name = name;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }


}

