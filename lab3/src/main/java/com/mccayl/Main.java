package com.mccayl;

import com.mccayl.dao.*;

public class Main {
    public static void main(String[] args) {
        ComputerDAO computerDAO = new ComputerDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        OfficeDAO officeDAO = new OfficeDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        ComputerLocationDAO locationDAO = new ComputerLocationDAO();

        // Добавление данных
        computerDAO.addComputer("HP ProDesk", "Intel i5", 8, "256GB SSD", "Windows 10");
        employeeDAO.addEmployee("Ivan Ivanov", "IT-отдел");
        officeDAO.addOffice("101", 1, "Главное здание");

        // Назначение компьютера сотруднику
        assignmentDAO.assignComputerToEmployee(1, 1);

        // Размещение компьютера в кабинете
        locationDAO.placeComputerInOffice(1, 1);

        // Вывод всех данных
        System.out.println("Сотрудники:");
        employeeDAO.getAllEmployees().forEach(System.out::println);

        System.out.println("Кабинеты:");
        officeDAO.getAllOffices().forEach(System.out::println);

        System.out.println("Компьютеры:");
        computerDAO.getAllComputers().forEach(System.out::println);
    }
}