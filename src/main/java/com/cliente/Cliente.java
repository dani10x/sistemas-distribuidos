package com.cliente;

import com.service.impl.MenuService;
import java.io.IOException;
import java.rmi.NotBoundException;

public class Cliente {

    public static void main(String[] args) throws IOException, NotBoundException {
        MenuService menuService = new MenuService();
        menuService.printMenu();
    }
}
