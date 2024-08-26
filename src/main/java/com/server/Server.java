package com.server;

import com.service.impl.EmpleadosSerice;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1099);
        EmpleadosSerice service = new EmpleadosSerice();
        reg.rebind("Nomina", service);
        System.out.println("Servidor Iniciado");
    }
}
