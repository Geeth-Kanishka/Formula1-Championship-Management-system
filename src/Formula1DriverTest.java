//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class Formula1DriverTest {
//    @Test
//    void compareTo() {
//        Formula1Driver driver= new Formula1Driver("name","team","location");
//        driver.addPoints(2);
//        Formula1Driver driver2= new Formula1Driver("name","team","location");
//        driver2.addPoints(4);
//        assertEquals(1,driver.compareTo(driver2));
//    }
//
//    @Test
//    void calPoint() {
//        Formula1Driver driver= new Formula1Driver("name","team","location");
//        int[] positions= {1, 2, 3, 1, 1, 1, 2, 1, 1, 1};//155
//        driver.calPoint(positions);
//        assertEquals(155,driver.getPoints());
//    }
//
//    @Test
//    void addPoints() {
//        int[] pointSchema={25,18,15,12,10,8,6,4,2,1};
//        for(int i=0;i< pointSchema.length;i++){
//            Formula1Driver driver= new Formula1Driver("name","team","location");
//            driver.addPoints(i+1);
//            assertEquals(pointSchema[i],driver.getPoints());
//            System.out.println("position "+(i+1)+"="+driver.getPoints());
//        }
//    }
//}