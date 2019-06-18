package Lists;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class List {
    protected ArrayList<String> songs;
    protected String name;

    public List(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
    
}
