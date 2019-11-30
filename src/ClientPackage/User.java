package ClientPackage;

import Network.Client.Client;
import Serialization.Serializer;

public class User {
    private String username;
    private Library library;
    private Client client;

    public User(String username) {
        this.username = username;
        library = new Library(Serializer.readFromFile(".\\SaveFiles\\saved.bin"));
    }

    public String getUsername() {
        return username;
    }


    public Library getLibrary() {
        return library;
    }
}