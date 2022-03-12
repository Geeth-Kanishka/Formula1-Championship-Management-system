import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    static Scanner input= new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static void main(String[] args) throws Exception {
        String option;
        ArrayList<Car> constructors = new ArrayList<Car>();
        ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>();
        ArrayList<Race> races = new ArrayList<Race>();
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
        do{System.out.println("\nSelect an Option\n" +
                "1 :  Create a new Driver\n"+
                "2 :  Delete a Driver\n"+
                "3 :  Change the driver for an existing constructor team\n"+
                "4 :  Display the various statistics for a Driver\n"+
                "5 :  Display the Formula 1 Driver Table\n"+
                "6 :  Add a race\n"+
                "7 :  Save Data\n"+
                "8 :  Restore Data\n"+
                "9 :  Launch GUI\n"+
                "10:  Exit the Program \n"
        );
            System.out.println("Enter your choice:");
            option=input.next();
            input.nextLine();
            switch (option){
                case "1": {
                    manager.createDriver(drivers,constructors);
                    break; }
                case "2": {
                    manager.deleteDriver(drivers,constructors);
                    break; }
                case "3": {
                    manager.changeDriver(drivers,constructors);
                    break; }
                case "4": {
                    manager.displayStats(drivers);
                    break; }
                case "5": {
                    manager.driverTable(drivers);
                    break; }
                case "6": {
                    manager.addRace(drivers,races);
                    break;}
                case "7": {
                    manager.saveData(drivers,races,constructors);
                    break;
                }
                case "8": {
                    try
                    {
                        FileInputStream file = new FileInputStream("driverData.txt");
                        ObjectInputStream fileInput = new ObjectInputStream(file);
                        drivers = (ArrayList<Formula1Driver>) fileInput.readObject();
                        races=(ArrayList<Race>)fileInput.readObject();
                        constructors=(ArrayList<Car>)fileInput.readObject();
                        fileInput.close();
                        file.close();
                        System.out.println("data loaded");
                    }
                    catch (IOException |ClassNotFoundException e){}
                    break;
                }
                case "9": {
                    GUI.createGUI(drivers,races);
                    break;
                }
                default:
                    if (!option.equals("10")){
                        System.out.println("Invalid option\n");
                    }
            }
        }
        while (!option.equals("10"));
    }

    public void createDriver( ArrayList<Formula1Driver> drivers, ArrayList<Car> constructors){
        /*Create a new driver with unique team
          @param drivers Arraylist of Formula1Driver objects
          @param constructors Arraylist of Car objects */
        System.out.println("Enter team name:");
        String teamName=input.next();
        Car team = null;
        for(Car constructor:constructors){
            if(constructor.getName().equals(teamName)){
                team=constructor;
            }
        }
        if(!constructors.contains(team)){
            team = new Car(teamName);
            Formula1Driver driver;
            input.nextLine();
            System.out.println("Enter drivers name:");
            String driverName=input.nextLine();
            System.out.println("Enter location:");
            String driverLocation=input.nextLine();
            System.out.println("Enter positions Y/N?");
            String answer=input.nextLine();
            if(answer.equalsIgnoreCase("y")) {
                int[] positions = new int[10];
                for (int i = 0; i < 10; i++) {
                    System.out.println("Enter no of " + (i + 1) + " positions");
                    int position = input.nextInt();
                    input.nextLine();
                    positions[i] = position;
                }
                driver = new Formula1Driver(driverName,team.getName(), driverLocation, positions[0], positions[1], positions[2]);
                driver.calPoint(positions);
            }
            else {
                driver = new Formula1Driver(driverName,team.getName(),driverLocation);
            }
            team.setDriver(driver);
            drivers.add(driver);
            constructors.add(team);
            System.out.println("driver added");
        }
        else{
            System.out.println("Team has a driver");
        }
    }

    public void deleteDriver(ArrayList<Formula1Driver> drivers, ArrayList<Car> constructors){
        /*Delete driver and their team
          @param drivers Arraylist of Formula1Driver objects
          @param constructors Arraylist of Car objects */
        System.out.println("Enter drivers name:");
        String driverName=input.nextLine();
        Iterator<Car> iterator = constructors.iterator();
        while (iterator.hasNext()) {
            Car team = iterator.next();
            if (team.getDriver().getName().equals(driverName)){
                drivers.remove(team.getDriver());
                iterator.remove();
                System.out.println("driver,team removed!");
            }
        }
    }

    public void changeDriver(ArrayList<Formula1Driver> drivers, ArrayList<Car> constructors){
        /*Change the driver of a team
          @param drivers Arraylist of Formula1Driver objects
          @param constructors Arraylist of Car objects */
        System.out.println("Enter team name");
        String teamName= input.nextLine();
        boolean test=true;
        for (Car constructor:constructors){
            if(constructor.getName().equals(teamName)){
                System.out.println("Enter new drivers name:");
                String driverName=input.nextLine();
                System.out.println("Enter new drivers location:");
                String location=input.nextLine();
                int index= drivers.indexOf(constructor.getDriver());
                Formula1Driver driver=new Formula1Driver(driverName,constructor.getName(),location);
                constructor.setDriver(driver);
                drivers.set(index,driver);
                System.out.println("Driver changed successfully");
                test=false;
                    }
                }
        if(test){
            System.out.println("team doesn't exist!.Create a new driver");
        }
    }

    public void displayStats(ArrayList<Formula1Driver> drivers){
        /*display information about a driver
          @param drivers Arraylist of Formula1Driver objects */
        System.out.println("Enter drivers name: ");
        String name=input.nextLine();
        boolean flag=true;
        for (Formula1Driver driver:drivers){
            if(driver.getName().equals(name)){
                driver.printStats();
                flag=false;
                break;
            }
        }
        if(flag){
            System.out.println("Driver not available");
        }
    }

    public void driverTable(ArrayList<Formula1Driver> drivers){
        /*display info about all the drivers(descending order)
          @param drivers Arraylist of Formula1Driver objects*/
        ArrayList<Formula1Driver> newList=new ArrayList<>(drivers);
        Collections.sort(newList,Collections.reverseOrder());
        for (int i = 0; i < newList.size(); i++) {
            for (int j = i+1; j < newList.size(); j++) {
                if(newList.get(i).getPoints()==newList.get(j).getPoints()){
                    if(newList.get(i).getFirstPosition()<newList.get(j).getFirstPosition()){
                        Collections.swap(newList,i,j);
                    }
                }
            }
        }
        for (Formula1Driver driver : newList){
            driver.printStats();
        }
    }

    public void addRace(ArrayList<Formula1Driver> drivers,ArrayList<Race> races) {
        /*Create a new race
          @param drivers Arraylist of Formula1Driver objects
          @param races Arraylist of Race objects */
        System.out.println("Enter date(dd/MM/yyyy):");
        Date date=null;
        while (date == null) {
            String userDate = input.nextLine();
            try {
                date = dateFormat.parse(userDate);
            } catch (ParseException e) {
                System.out.println("Invalid date!!.PLease enter again");
            }
        }
        System.out.println(drivers.size()+" Positions available");
        ArrayList<Integer> takenPositions= new ArrayList<Integer>();
        String[] raceList= new String[drivers.size()];
        int position=0;
        for (Formula1Driver driver:drivers){
           while(true) {
               System.out.println("Enter position of --" + driver.getName() + "--");
               position = input.nextInt();
               if (position <= drivers.size() && position != 0) {
                   if (!takenPositions.contains(position)) {
                       takenPositions.add(position);
                       driver.addPoints(position);
                       raceList[position-1]= driver.getName();
                       break;
                   } else {
                       System.out.println("Position already taken!");
                   }
               }
               else {
                   System.out.println("invalid position");
               }
           }
         }
        Race race= new Race(date,raceList);
        races.add(race);
    }
    public void saveData(ArrayList<Formula1Driver> drivers,ArrayList<Race> races, ArrayList<Car> constructors) throws Exception {
        /*Save program to a file
          @param drivers Arraylist of Formula1Driver objects
          @param constructors Arraylist of Car objects
          @param races Arraylist of Race objects*/
        FileOutputStream file= new FileOutputStream("driverData.txt");
        ObjectOutputStream output=new ObjectOutputStream(file);
        output.writeObject(drivers);
        output.writeObject(races);
        output.writeObject(constructors);
        output.close();
        file.close();
        System.out.println("data saved");

    }

}
