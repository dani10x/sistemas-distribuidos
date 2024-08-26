package com.service.impl;

import com.entity.EmpleadoEntity;
import com.service.IEmpleadosService;
import com.service.IMenuService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class MenuService implements IMenuService {

    private List<EmpleadoEntity> matriz;

    @Override
    public void printMenu() throws IOException, NotBoundException {
        int choice = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Empleados:");
        int empleados = obtenerOpcion(br);
        System.out.print("Meses:");
        int meses = obtenerOpcion(br);
        System.out.println();

        IEmpleadosService service = (IEmpleadosService) Naming.lookup("Nomina");
        matriz = service.generarMatriz(empleados, meses);
        printMatriz();
        do {
            printMenuInicial();
            choice = obtenerOpcion(br);
            consumirServicio(choice, service);
        } while (choice != 5);
    }

    private void printMenuInicial() {
        System.out.println("1. Total pagado por empleado    ");
        System.out.println("2. Promedio de cada mes por pago de salarios   ");
        System.out.println("3. Total pagado en la matriz    ");
        System.out.println("4. Visualizar matriz    ");
        System.out.println("5. Salir    ");
        System.out.print("OPCION:");
    }

    private void consumirServicio(int choice, IEmpleadosService service) throws NotBoundException, IOException {
        switch (choice) {
            case 1: totalPagadoEmpleado(service); break;
            case 2: totalPagadoMes(service); break;
            case 3: totalPagadoMatriz(service); break;
            case 4: printMatriz(); break;
            default: break;
        }
    }

    private void totalPagadoEmpleado(IEmpleadosService service) throws RemoteException {
        Map<String, Integer> pagos = service.getTotalPagadoEmpleado();
        pagos.forEach((empleado, pago) -> {
            System.out.println(empleado + ": " + pago);
        });
    }

    private void totalPagadoMes(IEmpleadosService service) throws RemoteException {
        Map<String, Integer> pagos = service.getPromedioCadaMes();
        pagos.forEach((mes, pago) -> {
            System.out.println(mes + ": " + pago);
        });
    }

    private void totalPagadoMatriz(IEmpleadosService service) throws RemoteException {
        System.out.println("Total pagado en la matriz: $" + service.getTotalPagadoMatriz());
    }

    private int obtenerOpcion(BufferedReader br) throws IOException {
        try {
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida, por favor ingrese un número.");
            return obtenerOpcion(br);
        }
    }

    private void printMatriz() {
        if (!matriz.isEmpty()) {
            printencabezados();
            for (EmpleadoEntity empleado : matriz) {
                System.out.printf("%-12s", empleado.getNombre());

                empleado.getSalarios().forEach(salario -> System.out.printf("%-8s", "$" + salario));

                System.out.println();
            }
            System.out.println();
        }

    }

    private void printencabezados() {
        System.out.printf("%-12s", "EMPLEADO");
        for (int i=1; i<= matriz.get(0).getSalarios().size(); i++) {
            System.out.printf("%-8s", "MES " + i);
        }
        System.out.println();
    }

}
