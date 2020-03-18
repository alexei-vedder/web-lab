import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SortingService extends Remote {
    Dishes sortAndFilterUniqueItems(Dishes dishes) throws RemoteException;
}
