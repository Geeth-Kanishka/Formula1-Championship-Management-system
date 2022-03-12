import java.io.Serializable;

public class Car implements Serializable {
    private String name;
    private Formula1Driver driver;

    Car(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Formula1Driver getDriver() {
        return driver;
    }

    public void setDriver(Formula1Driver driver) {
        this.driver = driver;
    }
}
