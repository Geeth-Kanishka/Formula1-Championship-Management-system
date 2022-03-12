import java.text.ParseException;
import java.util.ArrayList;

public interface ChampionshipManager {
    void createDriver(ArrayList<Formula1Driver> drivers, ArrayList<Car> constructors);
    void deleteDriver(ArrayList<Formula1Driver> drivers, ArrayList<Car> constructors);
    void changeDriver(ArrayList<Formula1Driver> drivers, ArrayList<Car> constructors);
    void displayStats(ArrayList<Formula1Driver> drivers);
    void driverTable(ArrayList<Formula1Driver> drivers);
    void addRace(ArrayList<Formula1Driver> drivers,ArrayList<Race> races) throws ParseException;
    void saveData(ArrayList<Formula1Driver> drivers,ArrayList<Race> races, ArrayList<Car> constructors) throws Exception;
}


