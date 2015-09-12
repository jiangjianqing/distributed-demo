package my.prj.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import my.prj.rmi.model.PersonEntity;

/**
 * 创建远程接口PersonService,注意远程接口需要继承Remote
 * @author cz_jjq
 *
 */
public interface PersonService extends Remote {
	public List<PersonEntity> GetList() throws RemoteException;
}
