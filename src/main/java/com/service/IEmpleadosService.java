package com.service;

import com.entity.EmpleadoEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IEmpleadosService extends Remote {

    List<EmpleadoEntity> generarMatriz(int empleados, int meses) throws RemoteException;

    Map<String, Integer> getTotalPagadoEmpleado() throws RemoteException;

    Map<String, Integer> getPromedioCadaMes() throws RemoteException;

    Integer getTotalPagadoMatriz() throws RemoteException;
}
