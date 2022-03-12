import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Race implements Serializable,Comparable<Race> {
    private Date date;
    private String[] positions;

    Race(Date date,String[] positions){
        this.date=date;
        this.positions=positions;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateNew= dateFormat.format(date);
        return dateNew;
    }

    public String[] rowData(){
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(positions));
        list.add(0, getDate());
        String[] rowData = list.toArray(new String[list.size()]);
        return rowData;
    }

    public String[] getPositions() {
        return positions;
    }

    @Override
    public int compareTo(Race o) {
        return date.compareTo(o.date);
    }
}
