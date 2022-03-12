import java.util.Comparator;
public class firstPositionCompare implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Formula1Driver f1=(Formula1Driver)o1;
        Formula1Driver f2=(Formula1Driver)o2;
        if(f1.getFirstPosition()==f2.getFirstPosition())
            return 0;
        else if(f1.getFirstPosition()>f2.getFirstPosition())
            return 1;
        else
            return -1;
    }
}

