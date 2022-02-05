public class Formula1Driver extends Driver{
    private int FirstPosition;
    private int SecPosition;
    private int ThirdPosition;
    private double Points;
    private int NoOfRaces;

    public Formula1Driver(String name,String Team,String location){
        setName(name);
        setTeam(Team);
        setLocation(location);
    }


    public void AddPoint( int position){
        if(position<=10){
            if (position==1){
                FirstPosition++;
                Points+=25;
            }
            else if (position==2){
                SecPosition++;
                Points+=18;
            }
            else if (position==3){
                ThirdPosition++;
                Points+=15;
            }
            else if (position==4){
                Points+=12;
            }
            else if (position==5){
                Points+=10;
            }
            else if (position==6){
                Points+=8;
            }
            else if (position==7){
                Points+=6;
            }
            else if (position==8){
                Points+=4;
            }
            else if (position==9){
                Points+=2;
            }
            else{Points+=1;}

        }

    }
    public void printinfo(){
        System.out.println(super.getName()+super.getTeam()+super.getLocation());

    }


}
