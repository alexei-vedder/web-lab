import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerApplication {
    public static void main(String ...args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(1099);
        context.bind("rmi://localhost:1099/dishes", new SortingServiceImpl());
        System.out.println("the server is ready");
    }
}
