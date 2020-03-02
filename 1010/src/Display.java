import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame {
    int n;
    Map map;
    JPanel[][] panels;
    JPanel[][][] waitPanels;
    boolean[][] mapEntered;
    boolean[] waitEntered;

    Display(int n) {
        this.n = n;
        map = new Map(n);
        setSize(Properties.getWidth(), Properties.getHeight());
        panels = new JPanel[n][n];
        mapEntered = new boolean[n][n];
        waitEntered = new boolean[Properties.waits];
        waitPanels = new JPanel[Properties.waits][Properties.max_shape_size][Properties.max_shape_size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                panels[i][j] = new JPanel();
                panels[i][j].setBackground(Properties.voidColor);
                panels[i][j].setBorder(Properties.panelBorder);
                panels[i][j].setBounds(Properties.space_lr + j * Properties.block_size_map, Properties.space_top + i * Properties.block_size_map, Properties.block_size_map, Properties.block_size_map);
                panels[i][j].setVisible(true);
                int finalI = i, finalJ = j;
                panels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(!map.isDead()) {
                            if (map.waitingBlockSelected) {
                                if (map.possible(map.selectedShape, finalI, finalJ))
                                    map.setShape(map.selectedShape, finalI, finalJ);
                            }
                        }
                        display();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if(!map.isDead()) {
                            mapEntered[finalI][finalJ] = true;
                        }
                        display();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if(!map.isDead()) {
                            mapEntered[finalI][finalJ] = false;
                        }
                        display();
                    }
                });
                add(panels[i][j]);
            }
        }
        for (int k = 0; k < Properties.waits; k++) {
            for (int i = 0; i < Properties.max_shape_size; i++) {
                for (int j = 0; j < Properties.max_shape_size; j++) {
                    waitPanels[k][i][j] = new JPanel();
                    waitPanels[k][i][j].setBounds(Properties.space_lr + k * (Properties.space_waits + Properties.max_shape_size * Properties.block_size_wait) + j * Properties.block_size_wait, Properties.space_top + Properties.block_size_map * n + Properties.space_mid + i * Properties.block_size_wait, Properties.block_size_wait, Properties.block_size_wait);
                    waitPanels[k][i][j].setBorder(Properties.waitPanelBorder);
                    waitPanels[k][i][j].setBackground(Properties.voidColor);
                    waitPanels[k][i][j].setVisible(true);
                    int finalK = k;
                    waitPanels[k][i][j].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if(!map.isDead()) {
                                if (map.wait[finalK] == null) map.cancelSelect();
                                else {
                                    if (map.waitSelected[finalK]) {
                                        map.cancelSelect();
                                    } else {
                                        map.selectShape(finalK);
                                    }
                                }
                            }
                            display();
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if(!map.isDead()) {
                                waitEntered[finalK] = true;
                            }
                            display();
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if(!map.isDead()) {
                                waitEntered[finalK] = false;
                            }
                            display();
                        }
                    });
                    add(waitPanels[k][i][j]);
                }
            }
        }
        JPanel tail = new JPanel();
        add(tail);
        display();
        setVisible(true);
    }

    void display() {
        if (map.isDead()) {
            for(int i=0;i<n;i++) for(int j=0;j<n;j++) panels[i][j].setBackground(Color.BLACK);

            for(int k=0;k<3;k++) for(int i=0;i<5;i++) for(int j=0;j<5;j++) waitPanels[k][i][j].setBackground(Color.WHITE);
            for(int k=0;k<3;k++) {
                for(int j=0;j<5;j++) {
                    waitPanels[k][0][j].setBackground(Color.BLACK);
                    waitPanels[k][2][j].setBackground(Color.BLACK);
                }
                for(int i=0;i<5;i++) waitPanels[k][i][4].setBackground(Color.BLACK);
            }
        } else {
            //display Map
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    if (map.map[i][j] == null) panels[i][j].setBackground(Properties.voidColor);
                    else panels[i][j].setBackground(map.map[i][j].color);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mapEntered[i][j]) {
                        if (map.waitingBlockSelected) {
                            Shape shape = map.selectedShape;
                            if (map.possible(shape, i, j)) {
                                for (int x = -2; x <= 2; x++)
                                    for (int y = -2; y <= 2; y++)
                                        if (shape.shape[x + 2][y + 2] != null)
                                            if (inBound(i + x, j + y))
                                                panels[i + x][j + y].setBackground(Properties.map_possible_color);
                            } else {
                                for (int x = -2; x <= 2; x++)
                                    for (int y = -2; y <= 2; y++)
                                        if (shape.shape[x + 2][y + 2] != null)
                                            if (inBound(i + x, j + y))
                                                panels[i + x][j + y].setBackground(Properties.map_impossible_color);

                            }
                        } else {
                            panels[i][j].setBackground(Properties.map_entered_color);
                        }
                    }
                }
            }
            //display Wait
            for (int k = 0; k < Properties.waits; k++) {
                Shape shape = map.wait[k];
                for (int i = 0; i < Properties.max_shape_size; i++) {
                    for (int j = 0; j < Properties.max_shape_size; j++) {
                        if (shape == null) {
                            if (waitEntered[k]) waitPanels[k][i][j].setBackground(Properties.wait_entered_color);
                            else waitPanels[k][i][j].setBackground(Properties.voidColor);
                        } else {
                            if (shape.shape[i][j] != null) waitPanels[k][i][j].setBackground(shape.color);
                            else waitPanels[k][i][j].setBackground(Properties.voidColor);
                            if (waitEntered[k]) {
                                if (map.wait[k].shape[i][j] != null) waitPanels[k][i][j].setBackground(shape.color);
                                else waitPanels[k][i][j].setBackground(Properties.wait_entered_color);
                            }
                            if (map.waitSelected[k]) {
                                if (map.wait[k].shape[i][j] != null)
                                    waitPanels[k][i][j].setBackground(Properties.wait_selected_color);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean inBound(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) return false;
        else return true;
    }
}
