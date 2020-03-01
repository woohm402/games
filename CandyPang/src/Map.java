import java.util.concurrent.TimeUnit;

public class Map {
    int m,n;
    Block[][] map;
    Map(int m, int n) {
        this.m=m;
        this.n=n;
        map = new Block[m+2][n+2];
        for(int i=0;i<=m+1;i++) map[i][0]=map[i][n+1]=new WallBlock();
        for(int j=0;j<=n+1;j++) map[0][j]=map[m+1][j]=new WallBlock();
        fillMap();
        printMap();
    }

    public boolean crush(int pi, int pj) {
        boolean[][] visit = new boolean[n+2][n+2];
        if(count(pi,pj)>=Properties.minCount) {
            crush(map[pi][pj], pi, pj, visit);
            fillMap();
            return true;
        } else return false;
    }

    public Block[] getCol(int j) {
        Block[] ret = new Block[m+2];
        for(int i=0;i<=m+1;i++) ret[i]=map[i][j];
        return ret;
    }

    public void printMap() {
        for(int i=0;i<=m+1;i++) {
            for(int j=0;j<=n+1;j++) {
                if(map[i][j] instanceof WallBlock) System.out.print("O ");
                if(map[i][j] instanceof RedBlock) System.out.print("R ");
                if(map[i][j] instanceof BlueBlock) System.out.print("B ");
                if(map[i][j] instanceof GreenBlock) System.out.print("G ");
                if(map[i][j] instanceof YellowBlock) System.out.print("Y ");
                if(map[i][j] instanceof PurpleBlock) System.out.print("P ");
            }
            System.out.println();
        }
    }

    private void fillMap() {
        for(int j=1;j<=n;j++) setCol(fillLine(getCol(j)),j);
    }

    private Block[] fillLine(Block[] arr) {
        Block[] ret = new Block[m+2];
        ret[0]=ret[m+1]=new WallBlock();
        int ind=m;
        for(int i=m;i>=1;i--) if(arr[i]!=null) ret[ind--]=arr[i];
        for(int i=m;i>=1;i--) if(ret[i]==null) ret[i]=randomBlock();
        for(Block b : ret)  {
            b.entered=false;
            b.pressed=false;
        }
        return ret;
    }

    private void setCol(Block[] arr, int j) {
        for(int i=0;i<=m+1;i++) map[i][j]=arr[i];
    }

    private void crush(Block item, int pi, int pj, boolean[][] visit) {
        if(map[pi][pj] instanceof WallBlock) return;
        visit[pi][pj]=true;
        int[] di = {-1,0,1,0}, dj = {0,-1,0,1};
        for(int d=0;d<4;d++) if(!visit[pi+di[d]][pj+dj[d]] && map[pi+di[d]][pj+dj[d]].equals(item) ) crush(item, pi+di[d],pj+dj[d],visit);
        map[pi][pj]=null;
    }

    private int count(int pi, int pj) {
        boolean[][] visit = new boolean[m+2][n+2];
        return count(map[pi][pj], pi, pj, visit);
    }

    private int count(Block item, int pi, int pj, boolean[][] visit) {
        if(visit[pi][pj] || map[pi][pj] instanceof WallBlock || !(item.equals(map[pi][pj]))) return 0;
        visit[pi][pj]=true;
        int ret=1;
        int[] di= {-1,0,1,0}, dj = {0,-1,0,1};
        for(int d=0;d<4;d++) ret+=count(item, pi+di[d], pj+dj[d], visit);
        return ret;
    }

    private Block randomBlock() {
        int whichBlock = (int)(Math.random()*Properties.kind);
        switch(whichBlock) {
            case 0:
                return new RedBlock();
            case 1:
                return new BlueBlock();
            case 2:
                return new YellowBlock();
            case 3:
                return new GreenBlock();
            case 4:
                return new PurpleBlock();
        }
        return null;
    }
}
