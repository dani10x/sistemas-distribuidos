package com.service.impl;

import com.entity.EmpleadoEntity;
import com.service.IEmpleadosService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.utils.GeneradorRandom.*;

public class EmpleadosSerice extends UnicastRemoteObject implements IEmpleadosService {

    private final List<EmpleadoEntity> matriz;

    public EmpleadosSerice() throws RemoteException {
        matriz = new ArrayList<>();
    }

    @Override
    public List<EmpleadoEntity> generarMatriz(int empleados, int meses) throws RemoteException {
        matriz.clear();
        for (int i=0; i<empleados; i++) {
            matriz.add(generarEmpleado(meses));
        }
        return matriz;
    }

    @Override
    public Map<String, Integer> getTotalPagadoEmpleado() throws RemoteException {
        Map<String, Integer> pagos = new HashMap<>();
        if (!matriz.isEmpty()) {
            for (EmpleadoEntity empleado : matriz) {
                int total = empleado.getSalarios()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                pagos.put(empleado.getNombre(), total);
            }
        }
        return pagos;
    }

    @Override
    public Map<String, Integer> getPromedioCadaMes() throws RemoteException {
        Map<String, Integer> pagos = new HashMap<>();
        if (!matriz.isEmpty()) {
            for (int i=0; i<matriz.get(0).getSalarios().size(); i++) {
                int mes = i;
                int total = matriz.stream()
                        .mapToInt(empleado -> empleado.getSalarios().get(mes))
                        .sum();
                pagos.put("Mes" + (i+1), total/matriz.size());
            }
        }
        return pagos;
    }

    @Override
    public Integer getTotalPagadoMatriz() throws RemoteException {
        return matriz.stream()
                .flatMap(empleado -> empleado.getSalarios().stream())
                .mapToInt(Integer::intValue)
                .sum();
    }

    private EmpleadoEntity generarEmpleado(int meses) {
        List<Integer> salarios = new ArrayList<>(meses);
        for (int i=0; i<meses; i++) {
            salarios.add(generateRandomNumber());
        }
        return new EmpleadoEntity(salarios, generateRandomName());
    }
}
