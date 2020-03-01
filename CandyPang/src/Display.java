import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame {
    int m,n;
    Map map;
    JPanel[][] panels;
    JPanel[][] coverPanels;
    Display(int m, int n) {
        this.m=m;
        this.n=n;
        panels = new JPanel[m+2][n+2];
        coverPanels = new JPanel[m+2][n+2];
        map = new Map(m,n);
        setSize(Properties.lrSpace*2+Properties.block_size*n, Properties.top_space+Properties.bottom_space+m*Properties.block_size);
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                panels[i][j] = new JPanel();
                coverPanels[i][j] = new JPanel();
                coverPanels[i][j].setBackground(Properties.coverPanelColor);
                add(coverPanels[i][j]);
                add(panels[i][j]);
                JPanel temp = new JPanel();
                add(temp);

                int finalI=i, finalJ=j;
                panels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        map.map[finalI][finalJ].pressed=true;
                        display();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(map.map[finalI][finalJ].pressed) map.crush(finalI,finalJ);
                        display();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        map.map[finalI][finalJ].entered=true;
                        display();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        map.map[finalI][finalJ].pressed=false;
                        map.map[finalI][finalJ].entered=false;
                        display();
                    }
                });
                panels[i][j].setBorder(Properties.blockLineBorder);
                panels[i][j].setBounds(Properties.lrSpace+(j-1)*Properties.block_size, Properties.top_space+(i-1)*Properties.block_size, Properties.block_size, Properties.block_size);
                coverPanels[i][j].setBounds(Properties.lrSpace+(j-1)*Properties.block_size, Properties.top_space+(i-1)*Properties.block_size, Properties.block_size, Properties.block_size);
            }
        }
        display();
        setVisible(true);
        //autoCrush();
    }

    void autoCrush() {
        while(true) {
            for(int i=1;i<=m;i++) for(int j=1;j<=n;j++) {
                if(map.crush(i,j)) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    display();
                }
            }
        }
    }

    void display() {
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(map.map[i][j].entered) coverPanels[i][j].setVisible(true);
                else coverPanels[i][j].setVisible(false);
                if(map.map[i][j] instanceof RedBlock) panels[i][j].setBackground(Properties.redColor);
                if(map.map[i][j] instanceof GreenBlock) panels[i][j].setBackground(Properties.greenColor);
                if(map.map[i][j] instanceof BlueBlock) panels[i][j].setBackground(Properties.blueColor);
                if(map.map[i][j] instanceof YellowBlock) panels[i][j].setBackground(Properties.yellowColor);
                if(map.map[i][j] instanceof PurpleBlock) panels[i][j].setBackground(Properties.purpleColor);
            }
        }
    }
}
