import java.io.Serializable;

abstract class Driver implements Serializable {
     String name;
     String location;
     String teamName;

     Driver(String name,String teamName,String location){
          this.name=name;
          this.location=location;
          this.teamName=teamName;
     }

     abstract void calPoint(int[] positions);
     abstract void addPoints(int position);
     abstract void printStats();
     String getName(){
          return name;
     }
     String getTeam(){
          return teamName;
     }
     String getLocation(){return location;}
}
