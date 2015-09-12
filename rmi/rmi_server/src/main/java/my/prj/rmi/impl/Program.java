package my.prj.rmi.impl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.swing.JFrame;

import my.prj.rmi.HelloService;
import my.prj.rmi.PersonService;

public class Program {

	public static void main(String[] args) {
		try {  
			/**20150912 !!重要!!
			 * RmiServer 这个实现类使用了UnicastRemoteObject去联接RMI系统
			 * 。在我们的例子中，我们是直接的从UnicastRemoteObject这个类上继承的，事实上并不一定要这样做，当然也可以不是从UnicastRmeoteObject上继承，那必须使用它的exportObject()方法去联接到RMI。
			 * 如果一个类继承自UnicastRemoteObject，那么它必须提供一个构造函数并且声明抛出一个RemoteException对象。
			 * 当这个构造函数调用了super()，它久激活UnicastRemoteObject中的代码完成RMI的连接和远程对象的初始化。
			 * 而此时应该已经决定了使用哪个hostname来实例化远程对象。因此必须在服务对象创建之前指定绑定的hostname。
			 */
			System.setProperty("java.rmi.server.hostname","127.0.0.1");
            PersonService personService=new PersonServiceImpl();  
            HelloService helloService=new HelloServiceImpl();
            //注册通讯端口  
            LocateRegistry.createRegistry(6600);  
            //注册通讯路径  
            Naming.rebind("rmi://127.0.0.1:6600/PersonService", personService); 
            Naming.rebind("rmi://127.0.0.1:6600/HelloService", helloService);
            JFrame frame=new JFrame("hello");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.setVisible(true);;
            System.out.println("Service Start!");  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}

}
