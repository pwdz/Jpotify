package Network;

import Network.Server.ClientHandler;


import java.util.HashMap;


public interface Linker {
    public HashMap<String,ClientHandler> getUsernamesAndClientHandlers();
}
