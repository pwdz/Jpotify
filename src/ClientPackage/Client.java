package ClientPackage;

public class Client {
    private String username;
    private Library library;
    public Client(String username){
        this.username=username;
        library=new Library();
    }
    public Library getLibrary()
    {
        return library;
    }
}
