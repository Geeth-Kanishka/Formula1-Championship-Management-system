//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Random;
//
//class GUITest {
//    @Test
//    void randomStartRace() {
//        ArrayList<String> tempDrivers = new ArrayList<String>( Arrays.asList("driver1", "driver2", "driver3"));
//        int a;
//        Iterator<String> iterator = tempDrivers.iterator();
//        while (iterator.hasNext()) {
//            String driver = iterator.next();
//            a = new Random().nextInt(100);
//            System.out.println("selected random number: "+a);
//            if(tempDrivers.indexOf(driver)==0){
//                if(a<40){
//                    iterator.remove();
//                    System.out.println("driver 1 got 1st place ---(random less than 40)");
//                    break;
//                }
//            }
//            else if(tempDrivers.indexOf(driver)==1){
//                if(a<30){
//                    iterator.remove();
//                    System.out.println("driver 2 got 1st place ---(random less than 30)");
//                    break;
//                }
//            }
//            else if(tempDrivers.indexOf(driver)==2||tempDrivers.indexOf(driver)==3){
//                if(a<10){
//                    iterator.remove();
//                    System.out.println("driver 3/4 got 1st place ---(random less than 10)");
//                    break;
//                }
//            }
//            else if(tempDrivers.indexOf(driver)<10){
//                if(a<2){
//                    iterator.remove();
//                    System.out.println("driver ? got 1st place ---(random less than 2)");
//                    break;
//                }
//            }
//
//        }
//    }
//}