public class Map {
    int n, turn=0, score=0;
    int[][] map;
    boolean changed;
    Map(int n) {
        this.n=n;
        map = new int[n][n];
        newItem();
        newItem();
    }
    void newItem() {
        int i,j;
        do {
            i = (int) (Math.random() * n);
            j = (int) (Math.random() * n);
        }while(map[i][j]!=0);

        if(turn++>=20 && Math.random()<0.2) map[i][j]=4;
        else map[i][j]=2;
    }
    int getItem(int i, int j) {
        return map[i][j];
    }
    int[] getRow(int i) {
        int[] ret = new int[n];
        for(int j=0;j<n;j++) ret[j]=map[i][j];
        return ret;
    }
    int[] getCol(int j) {
        int[] ret = new int[n];
        for(int i=0;i<n;i++) ret[i]=map[i][j];
        return ret;
    }
    void setRow(int[] arr, int i) {
        for(int j=0;j<n;j++) map[i][j]=arr[j];
    }
    void setCol(int[] arr, int j) {
        for(int i=0;i<n;i++) map[i][j]=arr[i];
    }
    void update(String mode) {
        changed=false;
        for(int i=0;i<n;i++) {
            switch (mode) {
                case "UP":
                    setCol(update_line(getCol(i)), i);
                    break;
                case "DOWN":
                    setCol(inverseLine(update_line(inverseLine(getCol(i)))), i);
                    break;
                case "RIGHT":
                    setRow(inverseLine(update_line(inverseLine(getRow(i)))), i);
                    break;
                case "LEFT":
                    setRow(update_line(getRow(i)), i);
                    break;
            }
        }
        if(changed) newItem();
    }
    int[] update_line(int[] arr) {
        return update_line(arr, "");
    }
    int[] update_line(int[] arr, String mode) {
        boolean flag=false;
        for(int i=n-1;i>=0;i--) {
            if(arr[i]!=0) flag=true;
            if(flag && arr[i]==0) changed=true;
        }
        int prev=0, ind=0;
        int[] ret = new int[n];
        for(int i=0;i<n;i++) {
            if(arr[i]!=0) {
                if(arr[i]==prev) {
                    ret[ind++]=arr[i]*2;
                    if(mode!="test") score+=arr[i]*2;
                    prev=0;
                    changed=true;
                } else {
                    if(prev!=0) ret[ind++]=prev;
                    prev=arr[i];
                }
            }
        }
        if(prev!=0) ret[ind++]=prev;
        return ret;
    }
    int[] inverseLine(int[] arr) {
        int[] ret = new int[n];
        for(int i=0;i<n;i++) ret[n-i-1]=arr[i];
        return ret;
    }
    boolean isDead() {
        changed=false;
        for(int i=0;i<n;i++) update_line(getRow(i), "test");
        for(int i=0;i<n;i++) update_line(getCol(i), "test");
        return !changed;
    }
}
