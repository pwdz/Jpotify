package ClientPackage;

import Lists.List;
import Lists.ListType;
import Serialization.Serializer;

public class Client {
    private String username;
    private Library library;
    public Client(String username){
        this.username=username;
        library=new Library(Serializer.readFromFile(""));

    }
    public Library getLibrary()
    {
        return library;
    }
}
