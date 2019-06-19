package Lists;

public class PlayList extends List {
    private boolean isDeletable;

    public PlayList(String name,boolean isDeletable) {
        super(name);
        this.isDeletable=isDeletable;
    }


}
