package my.prj.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import my.prj.rmi.HelloService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

	protected HelloServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return "hello,rmi!";
	}

}
