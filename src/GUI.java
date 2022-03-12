import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class GUI {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static Date date= new Date();
    public static void createGUI(ArrayList<Formula1Driver> drivers,ArrayList<Race> races){
        JFrame frame = new JFrame("Formula1 GUI");
        JButton button1= new JButton("Driver Table");
        JButton button2= new JButton("Add race(random)");
        JButton button3= new JButton("Race Table");
        JButton button4= new JButton("Add race(random start)");
        JButton button5= new JButton("Search driver");
        button1.setBounds(110,100,200,50);
        button2.setBounds(110,200,200,50);
        button3.setBounds(110,300,200,50);
        button4.setBounds(110,400,200,50);
        button5.setBounds(110,500,200,50);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.setSize(450, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground( Color.gray );
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArrayList<Formula1Driver> newList=driverTableFrame.pointsDes(drivers);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new driverInfo(drivers,races);
                frame.setVisible(false);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomStartRace(drivers,races);
                JOptionPane.showMessageDialog(frame, "Added random race(random start) .");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new raceTableFrame(drivers,races);
                frame.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 randomRace(drivers,races);
                JOptionPane.showMessageDialog(frame, "Added random race.");
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new driverTableFrame(newList,races);
                frame.setVisible(false);
            }
        });
    }

    public static void randomRace(ArrayList<Formula1Driver> drivers,ArrayList<Race> races)  {
        /*Create a random race with random positions
          @param drivers Arraylist of Formula1Driver objects
          @param races Arraylist of Race objects */
        ArrayList<Integer> availablePositions=new ArrayList<Integer>();
        String[] raceList= new String[drivers.size()];
        for(int i = 1; i <= drivers.size(); i++)
        {
            availablePositions.add(i);
        }
        Collections.shuffle(availablePositions);
        int i=0;
        for(Formula1Driver driver:drivers){
            driver.addPoints(availablePositions.get(i));
            raceList[availablePositions.get(i)-1]= driver.getName();
            i++;
        }
        dateFormat.format(date);
        Race race=new Race(date,raceList);
        races.add(race);
    }

    public static void randomStartRace(ArrayList<Formula1Driver> drivers,ArrayList<Race> races){
        /*Create a random race with random starting positions
          @param drivers Arraylist of Formula1Driver objects
          @param races Arraylist of Race objects */
        ArrayList<Formula1Driver> tempDrivers=new ArrayList<Formula1Driver>(drivers);
        Collections.shuffle(tempDrivers);
        int random;
        String[] raceList= new String[drivers.size()];
        Iterator<Formula1Driver> iterator = tempDrivers.iterator();
        while (iterator.hasNext()) {
            Formula1Driver driver = iterator.next();
            random = new Random().nextInt(100);
            if(tempDrivers.indexOf(driver)==0){
                if(random<40){
                    driver.addPoints(1);
                    raceList[0]= driver.getName();
                    iterator.remove();
                    break;
                }
            }
            else if(tempDrivers.indexOf(driver)==1){
                if(random<30){
                    driver.addPoints(1);
                    raceList[0]= driver.getName();
                    iterator.remove();
                    break;
                }
            }
           else if(tempDrivers.indexOf(driver)==2||tempDrivers.indexOf(driver)==3){
                if(random<10){
                    driver.addPoints(1);
                    raceList[0]= driver.getName();
                    iterator.remove();
                    break;
                }
            }
           else if(tempDrivers.indexOf(driver)<10){
                if(random<2){
                    driver.addPoints(1);
                    raceList[0]= driver.getName();
                    iterator.remove();
                    break;
                }
            }
        }
        ArrayList<Integer> availablePositions=new ArrayList<Integer>();
        if(tempDrivers.size()< drivers.size()){
            for(int i = 0; i < tempDrivers.size(); i++)
                {
                    availablePositions.add(i+2);
                }
        }
        else{
            for(int i = 1; i <= drivers.size(); i++)
            {
                availablePositions.add(i);
            }
        }
        Collections.shuffle(availablePositions);
        int x=0;
        for(Formula1Driver driver:tempDrivers){
            driver.addPoints(availablePositions.get(x));
            raceList[availablePositions.get(x)-1]= driver.getName();
            x++;
        }
        dateFormat.format(date);
        Race race=new Race(date,raceList);
        races.add(race);
    }
}
class driverInfo{
    //Get driver race information
    JFrame frame;
    driverInfo(ArrayList<Formula1Driver> drivers,ArrayList<Race> races){
        frame=new JFrame();
        JLabel l1=new JLabel("Find race");
        l1.setBounds(200,10, 100,30);
        JLabel l2=new JLabel("Enter Drivers name:");
        l2.setBounds(50,70, 150,30);
        JTextField field1=new JTextField();
        field1.setBounds(50,100,200,30);
        JTextArea field2=new JTextArea();
        field2.setBounds(50,190,200,350);
        JButton button3= new JButton("Enter");
        button3.setBounds(300,100,100,20);
        JButton button4= new JButton("Back");
        button4.setBounds(300,200,100,20);
        frame.add(field2);
        frame.add(field1);
        frame.add(l2);
        frame.add(l1);
        frame.add(button3);
        frame.add(button4);
        frame.setLayout(null);
        frame.setBounds(200,300,500,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field2.setText("");
                String driverName=field1.getText();
                for(Race race:races){
                    for(int i=0;i<race.getPositions().length;i++){
                        if (race.getPositions()[i].equalsIgnoreCase(driverName)){
                            field2.append("\ndate: "+race.getDate()+"  position:" +  (i+1));
                            break;
                        }
                    }
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.createGUI(drivers,races);
                frame.setVisible(false);
            }
        });
    }
}
class raceTableFrame{
    //Get data about the races
    JFrame frame;
    raceTableFrame(ArrayList<Formula1Driver> drivers,ArrayList<Race> races){
        frame=new JFrame();
        JPanel panel=new JPanel();
        JLabel l1=new JLabel("Race Table");
        l1.setBounds(260,10, 100,30);
        JLabel l2=new JLabel("~Position~");
        l2.setBounds(260,35, 100,30);
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(0, 0);
        model.addColumn("Date");
        for (int i=1;i<=drivers.size()+2;i++){
            model.addColumn(i);
        }
        table.setModel(model);
        for(Race race:races){
            Object[] data= race.rowData();
            model.addRow(data);
        }
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);
        JButton button1= new JButton("Back");
        JButton button2= new JButton("Sort by date(Ascending)");
        panel.setBounds(1,50,600,500);
        button1.setBounds(100,400,100,20);
        button2.setBounds(300,400,200,20);
        frame.add(l1);
        frame.add(l2);
        frame.add(button1);
        frame.add(button2);
        frame.setLayout(null);
        frame.add(panel);
        frame.setBounds(200,300,650,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(races);
                new raceTableFrame(drivers,races);
                frame.setVisible(false);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.createGUI(drivers,races);
                frame.setVisible(false);
            }
        });
    }
}

class driverTableFrame {
    //driver statistics table
    JFrame frame;
    driverTableFrame(ArrayList<Formula1Driver> drivers,ArrayList<Race> races){
        frame=new JFrame();
        JPanel panel=new JPanel();
        JLabel l1=new JLabel("Driver Table");
        l1.setBounds(260,10, 100,30);
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(0, 0);
        String header[] = new String[] { "Driver", "Team","Location", "1st P", "2nd P", "3rd P","Points"};
        model.setColumnIdentifiers(header);
        table.setModel(model);
        for(Formula1Driver driver:drivers){
            model.addRow(new Object[] { driver.getName(),driver.getTeam(),driver.getLocation(),driver.getFirstPosition(),driver.getSecPosition(),driver.getThirdPosition(), driver.getPoints() });
        }
        JScrollPane sp=new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder());
        panel.add(sp);
        JButton button1= new JButton("Asc. Order");
        JButton button2= new JButton("Des. Order");
        JButton button3= new JButton("first Order");
        JButton button4= new JButton("Back");
        panel.setBounds(1,50,600,200);
        button1.setBounds(210,200,100,20);
        button2.setBounds(100,200,100,20);
        button3.setBounds(320,200,100,20);
        button4.setBounds(430,200,100,20);
        frame.add(l1);
        frame.add(button4);
        frame.add(button3);
        frame.add(button2);
        frame.add(button1);
        frame.setLayout(null);
        frame.add(panel);
        frame.setBounds(200,300,650,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArrayList<Formula1Driver> tempDrivers=new ArrayList<Formula1Driver>(drivers);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.createGUI(drivers,races);
                frame.setVisible(false);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(tempDrivers,new firstPositionCompare().reversed());
                new driverTableFrame(tempDrivers,races);
                frame.setVisible(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new driverTableFrame(pointsDes(drivers),races);
                frame.setVisible(false);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new driverTableFrame(pointsAsc(tempDrivers),races);
                frame.setVisible(false);
            }
        });
    }
    public static ArrayList pointsAsc( ArrayList<Formula1Driver> drivers){
        /*Sort in ascending order
          @param drivers Arraylist of Formula1Driver objects */
        Collections.sort(drivers);
        return drivers;
    }
    public static ArrayList pointsDes( ArrayList<Formula1Driver> drivers){
        /*Sort in descending order
          @param drivers Arraylist of Formula1Driver objects */
        ArrayList<Formula1Driver> tempDrivers=new ArrayList<Formula1Driver>(drivers);
        Collections.sort(tempDrivers,Collections.reverseOrder());
        return tempDrivers;
    }
}