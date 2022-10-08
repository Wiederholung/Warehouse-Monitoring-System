package model.dao.impl;

import model.dao.UserDAO;
import model.vo.User;
import model.vo.Warehouse;
import org.junit.jupiter.api.Test;

class UserDAOImplTest {
    UserDAO userDAO = new UserDAOImpl();

    private User m1 = new User("hyt", "C 2998-mysql",
            "Yitong Hu", true);
    private User u1 = new User("wie", "123",
            "Widerholung", false);
    private Warehouse wh1 = new Warehouse("3rd", "Sport");



    @org.junit.jupiter.api.Test
    void delWorkerFromWh() {
    }

    @org.junit.jupiter.api.Test
    void testDelWorkerFromWh() {
    }

    @Test
    void getUser() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void checkPasswordStrength() {
    }

    @Test
    void login() {
    }

    @Test
    void registerWorker() {
    }

    @Test
    void addManger() {
    }

    @Test
    void getAccess() {
    }

    @Test
    void addWorkerToWh() {
    }

    @Test
    void testUpdWorkerFromWh() {
    }

    @Test
    void testDelWorkerFromWh1() {
    }

    @Test
    void testDelWorkerFromWh2() {
    }
}