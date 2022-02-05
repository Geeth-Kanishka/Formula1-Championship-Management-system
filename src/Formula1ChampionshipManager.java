import java.util.ArrayList;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String option;
        ArrayList<Car> Car= new ArrayList<Car>();
        ArrayList<Formula1Driver> Drivers= new ArrayList<Formula1Driver>();
        do{System.out.println("\nSelect an Option\n" +
                "1:  Create a new Driver\n"+
                "2:  Delete a Driver\n"+
                "3:  Change the driver for an existing constructor team\n"+
                "4:  Display the various statistics for a Driver\n"+
                "5:  Display the Formula 1 Driver Table\n"+
                "6:  Add a race\n"+
                "7:  Save Data\n"+
                "8:  Restore Data\n"+
                "9:  Exit the Program \n"

        );
        System.out.println("Enter your choice:");
        option=input.nextLine().toUpperCase();
        switch (option){
            case "1": {
                CreateDriver(Drivers,Car);

                break; }
            case "2": {
                deleteDriver(Drivers,Car);

                break; }
            case "3": {
                changeDriver(Drivers,Car);

                break; }
            case "4": {
                displayStats(Drivers);

                break; }
            case "5": {

                break; }
            case "6": {

                break;}
            case "7": {

                break;}
            case "8": {

                break;}
            default:
                if (!option.equals("9")){
                    System.out.println("Invalid option\n");}
        }}
        while (!option.equals("999")&&!option.equals("EXT"));
}

    public static void CreateDriver( ArrayList<Formula1Driver> Drivers,ArrayList<Car> Car){
        System.out.println("Select/add a Constructor");
        String driverteam= input.nextLine();
        int index = 0;
        boolean flag=true;
        for (Car s :Car){
            String tname=s.getname();
            if(driverteam.equals(tname)){
                index=Car.indexOf(s);
                flag=false;
                break;
            }
        }

        if(flag){

            Car newTeam=new Car(driverteam);
            Car.add(newTeam);
            index= Car.indexOf(newTeam);
        }
        if(Car.get(index).getdName().equals("empty")){
            System.out.println("Enter name");
            String name=input.nextLine();
            System.out.println("location");
            String location=input.nextLine();
            Formula1Driver driver=new Formula1Driver(name,driverteam,location);
            Drivers.add(driver);
            Car.get(index).addDriver(driver);
            System.out.println("Driver addded");
        }
        else{
            System.out.println("team has driver");
        }
        for (Formula1Driver s : Drivers){
           s.printinfo();
        }
        for (Car s : Car){
            s.printinfo();
        }

    }
    public static void deleteDriver( ArrayList<Formula1Driver> Drivers,ArrayList<Car> Car){
        System.out.println("Enter driver name");
        String name=input.nextLine();
        for (Formula1Driver s : Drivers) {
            String dname=s.getName();
            if(name.equals(dname)){
                int index =Drivers.indexOf(s);
                Car.remove(index);
                Drivers.remove(index);
                System.out.println("removed!!!");
                break;

            }
        }
    }
    public static void changeDriver(ArrayList<Formula1Driver> Drivers,ArrayList<Car> Car){
        System.out.println("Enter constructor");
        String teamName=input.nextLine();
        for (Car s :Car){
            if (teamName.equals(s.getname())){
                System.out.println("Enter new driver name");
                String name=input.nextLine();
                System.out.println("Enter new driver location");
                String location=input.nextLine();
                Formula1Driver tempDriver=new Formula1Driver(name,location,s.getname());
                s.addDriver(tempDriver);
                int index= Car.indexOf(s);
                Drivers.set(index, tempDriver);
            };
        }



    }
    public static void displayStats( ArrayList<Formula1Driver> Drivers){
        System.out.println("enter driver name to view stats");
        String name=input.nextLine();
        for (Formula1Driver s : Drivers){
            if (name.equals(s.getName())){
                s.printinfo();
            }
        }


    }



}
