import java.awt.*;

public class Properties {
    final static int n=4;
    final static int block_size=100;
    final static int space=10;
    final static int topSpace=100;
    final static int bottomSpace=30;
    final static Color blockColor = new Color(240,240,240);
    final static Color backgroundColor = new Color(255,240,240);

    final static Color[] blockColors = new Color[]{
            new Color(255,255,200),
            new Color(255,230,150),
            new Color(255,200,150),
            new Color(255,180,120),
            new Color(255,150,120),
            new Color(255,150,100),
            new Color(255,100,100)
    };

    final static Font endFont = new Font("SansSerif",Font.BOLD, 50);
    final static Font scoreFont = new Font("SansSerif",Font.BOLD, 20);
    final static Font blockFont = new Font("SansSerif",Font.BOLD, 35);
}
