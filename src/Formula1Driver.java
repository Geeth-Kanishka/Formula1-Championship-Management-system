import java.io.Serializable;

public class Formula1Driver extends Driver implements Comparable<Formula1Driver>, Serializable {
    private int FirstPosition=0;
    private int SecPosition=0;
    private int ThirdPosition=0;
    private int Points=0;
    private int NoOfRaces;
    private int[] pointSchema={25,18,15,12,10,8,6,4,2,1};

    Formula1Driver(String name,String teamName,String location){
        super(name,teamName,location);
    }

    Formula1Driver(String name,String teamName,String location,int first,int sec,int third){
        super(name,teamName,location);
        this.FirstPosition=first;
        this.SecPosition=sec;
        this.ThirdPosition=third;
    }

    public int compareTo(Formula1Driver other){
        if(getPoints() == other.getPoints()){
            return 0;
        }
        else if(getPoints() > other.getPoints()){
            return 1;
        }
        else{
            return -1;
        }
    }

    public void calPoint(int[] positions) {
        for(int i=0;i<positions.length;i++){
            int point=positions[i]*pointSchema[i];
            Points+=point;
        }
    }

    public void addPoints(int position){
        NoOfRaces++;
        switch(position){
            case 1:{
                FirstPosition = getFirstPosition() + 1;
                setPoints(getPoints()+25);
                break; }
            case 2:{
                SecPosition = getSecPosition() + 1;
                setPoints(getPoints() + 18);
                break; }
            case 3:{
                ThirdPosition = getThirdPosition() + 1;
                setPoints(getPoints() + 15);
                break; }
        }
        if(position>=4){
            setPoints(getPoints() + pointSchema[position-1]);
        }
    }

    public void printStats(){
        System.out.println("\nName: "+name+"\nTeam: "+teamName+"\nLocation: "+location) ;
        System.out.println("\nFirst positions: "+ getFirstPosition());
        System.out.println("Second positions: "+ getSecPosition());
        System.out.println("Third positions: "+ getThirdPosition());
        System.out.println("Points: "+ getPoints());
        System.out.println("No. of Races: "+ NoOfRaces);
        System.out.println(".................................................");
    }


    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getFirstPosition() {
        return FirstPosition;
    }

    public int getSecPosition() {
        return SecPosition;
    }

    public int getThirdPosition() {
        return ThirdPosition;
    }
}
