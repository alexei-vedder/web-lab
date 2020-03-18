import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortingServiceImpl extends UnicastRemoteObject implements SortingService {
    protected SortingServiceImpl() throws RemoteException {
    }

    @Override
    public Dishes sortAndFilterUniqueItems(Dishes dishes) throws RemoteException {
        dishes.filterUniqueItems();
        dishes.sort();
        return dishes;
    }
}
