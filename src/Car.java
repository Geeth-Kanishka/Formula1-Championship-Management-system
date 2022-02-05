public class Car {
   private String name;
   private Formula1Driver driver;
   private String dName;


   public Car(String tname){
       this.name=tname;
       dName= "empty";

   }

   public  void addDriver(Formula1Driver Driver){
      this.driver=Driver;
      dName= driver.getName();
   }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdName() {

        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
    public void printinfo(){
        System.out.println(name+dName);
    }
}
