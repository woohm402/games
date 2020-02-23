import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameRun extends JFrame {
    Map map;
    int n, headSpace=Properties.topSpace, bottomSpace=Properties.bottomSpace, turn;
    JLabel[][] panels;
    JPanel head;
    JLabel score, endLabel;
    boolean end;
    GameRun(int n, int block_size, int space) {
        this.n=n;
        map = new Map(n);
        panels = new JLabel[n][n];
        setSize(n*block_size+2*space,n*block_size+2*space+headSpace+bottomSpace);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                panels[i][j] = new JLabel();
                panels[i][j].setBounds(j*block_size+space,headSpace+i*block_size+space,block_size,block_size);
                panels[i][j].setBorder(new LineBorder(Color.BLACK,1));
                panels[i][j].setBackground(Properties.blockColor);
                panels[i][j].setHorizontalAlignment(JLabel.CENTER);
                panels[i][j].setFont(Properties.blockFont);
                add(panels[i][j]);
            }
        }
        endLabel = new JLabel("END");
        endLabel.setBounds(0,block_size/4,block_size*2,block_size/2);
        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endLabel.setVisible(false);
        endLabel.setFont(Properties.endFont);
        add(endLabel);

        score = new JLabel("Score:0");
        score.setBounds((n-1)*block_size+space, block_size/4, block_size, block_size/2);
        score.setFont(Properties.scoreFont);
        add(score);

        head = new JPanel();
        head.setBounds(0,0,n*block_size+2*space,headSpace);
        head.setBackground(Properties.backgroundColor);
        add(head);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                String[] modes = {"LEFT", "UP", "RIGHT", "DOWN"};
                if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
                    map.update(modes[e.getKeyCode() - 37]);
                }
                showMap();
                end=map.isDead();
                System.out.println(end);
                if(end) endLabel.setVisible(true);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        showMap();
    }
    void showMap() {
        score.setText("Score:"+map.score);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(map.getItem(i,j)!=0) panels[i][j].setText(map.getItem(i,j)+"");
                else panels[i][j].setText("");
                setVisible(true);
            }
        }
    }
}
