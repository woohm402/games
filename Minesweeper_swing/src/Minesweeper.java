import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Minesweeper extends JFrame {
    Map map;
    JPanel[][] panels;
    JLabel[][] labels;
    JPanel head;
    JLabel gameOver;
    int m, n;
    boolean end;

    Minesweeper(int m, int n, int bombs) {
        this.m = m;
        this.n = n;
        map = new Map(m, n, bombs);
        map.printMap();
        panels = new JPanel[m + 2][n + 2];
        labels = new JLabel[m + 2][n + 2];
        int block_size = Properties.block_size;
        int space = Properties.space;
        setSize(2 * space + (n) * block_size, Properties.topSpace + (m) * block_size + Properties.bottomSpace);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                panels[i][j] = new JPanel();
                panels[i][j].setBorder(Properties.panelLine);
                panels[i][j].setBackground(Properties.panel_default_color);
                panels[i][j].setBounds(space + (j - 1) * block_size, Properties.topSpace + (i - 1) * block_size, block_size, block_size);
                int finalI = i;
                int finalJ = j;
                Block block = map.map[i][j];
                JPanel panel = panels[i][j];
                panels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        block.pressed = true;
                        display();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        block.pressed = false;
                        if (!end && !block.visible) {
                            if (SwingUtilities.isLeftMouseButton(e)) end = map.click(finalI, finalJ);
                            else if (SwingUtilities.isRightMouseButton(e)) block.marked = !block.marked;
                        }
                        display();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        block.mouseOn = true;
                        display();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        block.mouseOn = false;
                        display();
                    }
                });
                add(panels[i][j]);

                labels[i][j] = new JLabel();
                labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                labels[i][j].setBounds(space + (j - 1) * block_size, Properties.topSpace + (i - 1) * block_size, block_size, block_size);
                labels[i][j].setFont(Properties.blockFont);
                labels[i][j].setBackground(Color.WHITE);
                labels[i][j].setBorder(Properties.panelLine);
                add(labels[i][j]);
            }
        }
        gameOver = new JLabel();
        gameOver.setHorizontalAlignment(JLabel.CENTER);
        gameOver.setText("Game Over!");
        gameOver.setBounds(0, 0, 2 * space + n * block_size, Properties.topSpace);
        gameOver.setFont(Properties.gameOverFont);
        gameOver.setVisible(false);
        add(gameOver);

        head = new JPanel();
        head.setBackground(Properties.panel_invisible);
        add(head);
        setVisible(true);
    }

    void display() {
        if (end) {
            gameOver.setVisible(true);
            for(int i=1;i<=m;i++) {
                for(int j=1;j<=n;j++) {
                    Block block = map.map[i][j];
                    JPanel panel = panels[i][j];
                    JLabel label = labels[i][j];
                    if(block instanceof MineBlock) {
                        label.setVisible(false);
                        panel.setVisible(true);
                        panel.setBackground(Properties.panel_bomb_color);
                    }
                    else {
                        panel.setBackground(Properties.panel_invisible);
                        if(block.mineCnt!=0) label.setText(block.mineCnt+"");
                        else label.setText("");
                        label.setVisible(true);
                    }
                }
            }
        } else {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    Block block = map.map[i][j];
                    JPanel panel = panels[i][j];
                    JLabel label = labels[i][j];
                    if (block.visible) {
                        label.setVisible(true);
                        panel.setBackground(Properties.panel_invisible);
                        if (block.mineCnt != 0) label.setText(block.mineCnt + "");
                    } else {
                        label.setVisible(false);
                        if (!block.pressed) {
                            if (block.marked) {
                                if (block.mouseOn) {
                                    panel.setBackground(Properties.panel_marker_onClick_color);
                                } else {
                                    panel.setBackground(Properties.panel_marker_color);
                                }
                            } else {
                                if (block.mouseOn) {
                                    panel.setBackground(Properties.panel_onClick_color);
                                } else {
                                    panel.setBackground(Properties.panel_default_color);
                                }
                            }
                        } else {
                            panel.setBackground(Properties.panel_clicked_color);
                        }
                    }
                }
            }
        }
    }
}
