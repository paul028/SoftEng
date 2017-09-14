package mnnp.homecontrol.model;

/**
 * Created by paulv on 9/12/2017.
 */

public class Switches {
    String switch_name;
    String switch_description;
    int switch_state;

    public Switches() {

    }

    public Switches(String name, int state, String description)
    {
        this.switch_name = name;
        this.switch_state = state;
        this.switch_description = description;
    }

    public void setName(String name) {
        this.switch_name = name;
    }


    public void setState(int state) {
        this.switch_state = state;
    }

    public void setDescription(String description) {
        this.switch_description = description;
    }

    public String getName() {
        return switch_name;
    }

    public int getState() {
        return switch_state;
    }

    public String getDescription() {
        return switch_description;
    }
}
