import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortingServiceImpl extends UnicastRemoteObject implements SortingService {
    protected SortingServiceImpl() throws RemoteException {}

    @Override
    public Ration sortAndFilterUniqueItems(Ration ration) {
        ration.filterUniqueItems();
        ration.sort();
        return ration;
    }
}
