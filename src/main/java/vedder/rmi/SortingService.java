package vedder.rmi;

import vedder.models.Ration;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SortingService extends Remote {
    Ration sortAndFilterUniqueItems(Ration ration) throws RemoteException;
}
