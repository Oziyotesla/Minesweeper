//package communication;

public class StartData {
    private int xsize = 0;
    private int ysize = 0;
    private boolean[][] bombMap;
    private int difficulty = 0;
    public void setDifficulty(int diff){
        difficulty = diff;
    }
    public void setXsize(int x){
        xsize = x;
    }
    public void setYsize(int y){
        ysize = y;
    }
    public void setBombMap(boolean[][] b){
        for(int x_d=0; x_d < xsize ; x_d++){
            for(int y_d=0; y_d < xsize ; y_d++){
                bombMap[x_d][y_d] = b[x_d][y_d];
            }
        }
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getXsize(){
        return xsize;
    }
    public int getYsize(){
        return ysize;
    }
    public boolean[][] getBombMap(){
        return bombMap;
    }
}
