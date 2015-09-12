package my.prj.rmi.test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import my.prj.rmi.PersonService;
import my.prj.rmi.impl.PersonServiceImpl;
import my.prj.rmi.model.PersonEntity;

public class TestRmi {


	@Before
	public void before() throws RemoteException, MalformedURLException, InterruptedException {
		System.out.println("Ready!"); 
		/**20150912 !!重要!!
		 * RmiServer 这个实现类使用了UnicastRemoteObject去联接RMI系统
		 * 。在我们的例子中，我们是直接的从UnicastRemoteObject这个类上继承的，事实上并不一定要这样做，当然也可以不是从UnicastRmeoteObject上继承，那必须使用它的exportObject()方法去联接到RMI。
		 * 如果一个类继承自UnicastRemoteObject，那么它必须提供一个构造函数并且声明抛出一个RemoteException对象。
		 * 当这个构造函数调用了super()，它久激活UnicastRemoteObject中的代码完成RMI的连接和远程对象的初始化。
		 * 而此时应该已经决定了使用哪个hostname来实例化远程对象。因此必须在服务对象创建之前指定绑定的hostname。
		 */
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		PersonService personService=new PersonServiceImpl();  
        //注册通讯端口  
        LocateRegistry.createRegistry(6600);  
        //注册通讯路径  
        Naming.rebind("rmi://127.0.0.1:6600/PersonService", personService);  
        System.out.println("Service Start!");   
        
      //wait代码，线程A
        //Object lock_t=new Object();
        //synchronized(lock_t){
        //	lock_t.wait();
        //要执行的代码
        //}
	}

	@Test
		public void test() throws RemoteException, MalformedURLException, NotBoundException {
			//调用远程对象，注意RMI路径与接口必须与服务器配置一致  
	        PersonService personService=(PersonService)Naming.lookup("rmi://127.0.0.1:6600/PersonService");  
	        List<PersonEntity> personList=personService.GetList();  
	        for(PersonEntity person:personList){  
	            System.out.println("ID:"+person.getId()+" Age:"+person.getAge()+" Name:"+person.getName());  
	        }   
		}

	
}
