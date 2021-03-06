package my.prj.rmi.test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.junit.Test;

import my.prj.rmi.HelloService;
import my.prj.rmi.PersonService;
import my.prj.rmi.model.PersonEntity;

public class TestRmi {

	@Test
	public void test() throws MalformedURLException, RemoteException, NotBoundException {
		//调用远程对象，注意RMI路径与接口必须与服务器配置一致  
        PersonService personService=(PersonService)Naming.lookup("rmi://127.0.0.1:6600/PersonService");  
        List<PersonEntity> personList=personService.GetList();  
        for(PersonEntity person:personList){  
            System.out.println("ID:"+person.getId()+" Age:"+person.getAge()+" Name:"+person.getName());  
        }  
        
        HelloService helloService=(HelloService)Naming.lookup("rmi://127.0.0.1:6600/HelloService");
        System.out.println(helloService.sayHello());
	}

}
