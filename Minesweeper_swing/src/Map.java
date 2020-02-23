public class Map {
    Block[][] map;
    int m,n,bombs;
    Map(int m, int n, int bombs) {
        this.m=m;
        this.n=n;
        this.bombs=bombs;
        map = new Block[m+2][n+2];
        initMap();
    }
    void initMap() {
        int remainBomb = bombs;
        int remainLand = m*n-bombs;
        for(int i=0;i<=m+1;i++) {
            for(int j=0;j<=n+1;j++) {
                if(i==0 || i==m+1 || j==0 || j==n+1) map[i][j] = new WallBlock(i,j,this);
                else {
                    if(remainBomb==0) {
                        map[i][j] = new LandBlock(i,j,this);
                        remainLand--;
                    }
                    else if(remainLand==0) {
                        map[i][j] = new MineBlock(i,j,this);
                        remainBomb--;
                    }
                    else {
                        if(Math.random()<((double)remainBomb)/(remainLand+remainBomb)) {
                            map[i][j] = new MineBlock(i,j,this);
                            remainBomb--;
                        }
                        else {
                            map[i][j] = new LandBlock(i,j,this);
                            remainLand--;
                        }
                    }
                }
            }
        }
        for(int i=1;i<=m;i++) for(int j=1;j<=n;j++) map[i][j].init();
    }
    boolean click(int pi, int pj) {
        if(map[pi][pj] instanceof MineBlock) return true;
        boolean[][] visit = new boolean[m+2][n+2];
        for(int i=0;i<=m+1;i++) {
            for(int j=0;j<=n+1;j++) {
                if(!(map[i][j] instanceof LandBlock) || map[i][j].visible) visit[i][j]=true;
            }
        }
        updateMap(pi,pj,visit);
        return false;
    }
    void updateMap(int pi, int pj, boolean[][] visit) {
        map[pi][pj].visible=true;
        visit[pi][pj]=true;
        if(map[pi][pj].mineCnt!=0) return;
        int[] di = {-1,-1,-1,0,0,1,1,1};
        int[] dj = {-1,0,1,-1,1,-1,0,1};
        for(int i=0;i<8;i++) if(!visit[pi+di[i]][pj+dj[i]]) updateMap(pi+di[i],pj+dj[i],visit);
    }
    void printMap() {
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(map[i][j] instanceof MineBlock) System.out.print("X");
                else System.out.print("O");
            }
            System.out.println();
        }
    }
}

abstract class Block {
    Map map;
    boolean visible, marked, mouseOn, pressed;
    int pi, pj, mineCnt=0;;
    public Block(int pi, int pj, Map map) {
        this.map=map;
        this.pi=pi;
        this.pj=pj;
    }
    abstract void init();
}

class MineBlock extends Block {
    MineBlock(int pi, int pj, Map map) {
        super(pi, pj, map);
    }

    @Override
    void init() {

    }
}

class LandBlock extends Block {

    LandBlock(int pi, int pj, Map map) {
        super(pi, pj, map);
    }
    @Override
    void init() {
        int[] di = {-1,-1,-1,0,0,1,1,1};
        int[] dj = {-1,0,1,-1,1,-1,0,1};
        for(int i=0;i<8;i++) if(map.map[pi+di[i]][pj+dj[i]] instanceof MineBlock) mineCnt++;
    }
}

class WallBlock extends Block {
    WallBlock(int pi, int pj, Map map) {
        super(pi, pj, map);
    }

    @Override
    void init() {

    }
}