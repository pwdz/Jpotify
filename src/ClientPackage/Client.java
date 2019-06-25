package ClientPackage;

import Lists.List;

public class Client {
    private String username;
    private Library library;
    private List currentList;
    public Client(String username){
        this.username=username;
        library=new Library();
//        currentList = new List(library.);
    }
    public Library getLibrary()
    {
        return library;
    }
}
