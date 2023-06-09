import java.io.Serializable;

public class ClickData implements Serializable {
    public int x_cord;
    public int y_cord;
    public boolean flag;
    public ClickData(int x, int y, boolean f){
        x_cord = x;
        y_cord = y;
        flag = f;
    }
}
