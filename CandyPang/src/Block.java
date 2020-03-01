public class Block {
    boolean pressed, entered;
}

class WallBlock extends Block {

}

abstract class ColorBlock extends Block {
    abstract public boolean equals(Object o) ;
}

class RedBlock extends ColorBlock {
    public boolean equals(Object o) {
        if(o instanceof RedBlock) return true;
        else return false;
    }
}

class BlueBlock extends ColorBlock {
    public boolean equals(Object o) {
        if(o instanceof BlueBlock) return true;
        else return false;
    }
}

class GreenBlock extends ColorBlock {
    public boolean equals(Object o) {
        if(o instanceof GreenBlock) return true;
        else return false;
    }
}

class YellowBlock extends ColorBlock {
    public boolean equals(Object o) {
        if(o instanceof YellowBlock) return true;
        else return false;
    }
}

class PurpleBlock extends ColorBlock {
    public boolean equals(Object o) {
        if(o instanceof PurpleBlock) return true;
        else return false;
    }
}