public class Map {
    Block[][] map;
    Shape[] wait;
    boolean[] waitSelected;
    boolean waitingBlockSelected;
    Shape selectedShape;
    int n;
    Map(int n) {
        this.n=n;
        waitSelected = new boolean[Properties.waits];
        wait = new Shape[Properties.waits];
        map = new Block[n][n];
        fillWait();
    }
    boolean isDead() {
        for(Shape shape : wait) if(shape!=null) for(int i=0;i<n;i++) for(int j=0;j<n;j++) if(possible(shape,i,j)) return false;
        return true;
    }
    public boolean possible(Shape shape, int pi, int pj) {
        for(int i=-2;i<=2;i++) {
            for(int j=-2;j<=2;j++) {
                if(shape.shape[i+2][j+2]!=null) {
                    if(pi+i<0 || pi+i>=n || pj+j<0 || pj+j>=n) return false;
                    if(map[pi+i][pj+j]!=null) return false;
                }
            }
        }
        return true;
    }
    public void selectShape(int k) {
        for(int i=0;i<Properties.waits;i++) waitSelected[i]=false;
        selectedShape = wait[k];
        waitingBlockSelected=true;
        waitSelected[k]=true;
    }
    public void cancelSelect() {
        for(int i=0;i<Properties.waits;i++) waitSelected[i]=false;
        waitingBlockSelected=false;
        selectedShape = null;
    }
    public void setShape(Shape shape, int pi, int pj) {
        for(int i=-2;i<=2;i++) {
            for(int j=-2;j<=2;j++) {
                if(shape.shape[i+2][j+2]!=null) map[pi+i][pj+j]=shape.shape[i+2][j+2];
            }
        }
        for(int i=0;i<Properties.waits;i++) if(waitSelected[i]) wait[i]=null;
        cancelSelect();
        clearLines();
        fillWait();
    }
    void fillWait() {
        boolean flag=false;
        for(Shape shape : wait) if(shape!=null) flag=true;
        if(!flag) for(int i=0;i<wait.length;i++) wait[i] = randomShape();
        clearLines();
    }
    void clearLines() {
        for(int i=0;i<n;i++) clearRow(i);
        for(int j=0;j<n;j++) clearCol(j);
    }
    private void clearRow(int i) {
        boolean flag=false;
        for(int j=0;j<n;j++) if(map[i][j]==null) flag=true;
        if(!flag) for(int j=0;j<n;j++) map[i][j]=null;
    }
    private void clearCol(int j) {
        boolean flag=false;
        for(int i=0;i<n;i++) if(map[i][j]==null) flag=true;
        if(!flag) for(int i=0;i<n;i++) map[i][j]=null;
    }
    private Shape randomShape() {
        int whichShape = (int)(Math.random()*9)+1;
        switch(whichShape) {
            case 1:
                return new Shape1();
            case 2:
                return new Shape2();
            case 3:
                return new Shape3();
            case 4:
                return new Shape4();
            case 5:
                return new Shape5();
            case 6:
                return new Shape6();
            case 7:
                return new Shape7();
            case 8:
                return new Shape8();
            case 9:
                return new Shape9();
        }
        return null;
    }
}
