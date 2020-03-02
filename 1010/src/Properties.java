import javax.swing.border.LineBorder;
import java.awt.*;

public class Properties {
    final static int n = 10;
    final static int waits = 3;
    final static int max_shape_size = 5;

    final static int block_size_map = 50;
    final static int block_size_wait = 32;
    final static int space_top = 30;
    final static int space_lr = 10;
    final static int space_mid = 20;
    final static int space_bottom = 30;
    final static int space_waits = 10;

    final static Color voidColor = new Color(255, 255, 255);
    final static Color map_entered_color = new Color(100,100,100);
    final static Color map_possible_color = new Color(50,50,50);
    final static Color map_impossible_color = new Color(255,100,100);
    final static Color wait_entered_color = new Color(150,150,150);
    final static Color wait_selected_color = new Color(0,0,0);

    final static Color shape1Color = new Color(150, 100, 100);
    final static Color shape2Color = new Color(100, 255, 100);
    final static Color shape3Color = new Color(100, 100, 255);
    final static Color shape4Color = new Color(100, 255, 255);
    final static Color shape5Color = new Color(255, 255, 100);
    final static Color shape6Color = new Color(255, 100, 255);
    final static Color shape7Color = new Color(255, 200, 100);
    final static Color shape8Color = new Color(100, 0, 255);
    final static Color shape9Color = new Color(0, 100, 100);

    final static LineBorder panelBorder = new LineBorder(Color.BLACK, 2);
    final static LineBorder waitPanelBorder = new LineBorder(Color.BLACK, 1);

    public static int getWidth() {
        return space_lr * 2 + n * block_size_map;
    }

    public static int getHeight() {
        return space_top + space_mid + space_bottom + n * block_size_map + 5 * block_size_wait;
    }
}
