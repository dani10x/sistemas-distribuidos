package com.service;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface IMenuService {

    void printMenu() throws IOException, NotBoundException;
}
