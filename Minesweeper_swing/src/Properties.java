import javax.swing.border.LineBorder;
import java.awt.*;

public class Properties {
    final static int m=20;
    final static int n=25;
    final static int bombs = 50;

    final static int block_size = 20;
    final static int space = 10;
    final static int topSpace = 100;
    final static int bottomSpace = 30;

    final static Color panel_default_color = new Color(100,100,100);
    final static Color panel_onClick_color = new Color(150,150,150);
    final static Color panel_clicked_color = new Color(80,80,80);
    final static Color panel_marker_color = new Color(250,200,200);
    final static Color panel_marker_onClick_color = new Color(150,100,100);
    final static Color panel_bomb_color = Color.BLACK;
    final static Color panel_invisible = new Color(0,0,0,0);

    final static Font blockFont = new Font("SansSerif",Font.PLAIN, 15);
    final static Font gameOverFont = new Font("SansSerif",Font.BOLD,50);
    final static LineBorder panelLine = new LineBorder(Color.BLACK, 1);
}
