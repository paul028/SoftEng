package mnnp.homecontrol.model;

/**
 * Created by paulv on 9/14/2017.
 */
public class Logs {
    String date_time,sensor_name,status,name;

    public Logs() {

    }

    public Logs(String date_time,String sensor_name, String status,String name)
    {
        this.date_time = date_time;
        this.sensor_name = sensor_name;
        this.status = status;
        this.name = name;
    }
    public void setName(String name){ this.name = name;}

    public void setSensor_Name(String sensor_name) {this.sensor_name = sensor_name;}

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }



    public String getSensor_Name() {
        return sensor_name;
    }

    public String getStatus() {
        return status;
    }

    public String getDate_time()
    {
        return date_time;
    }

    public String getName() {return name;}
}
