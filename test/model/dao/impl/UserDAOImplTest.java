package model.dao.impl;

import model.dao.UserDAO;
import model.vo.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class UserDAOImplTest {
    private final UserDAO userDAO = new UserDAOImpl();
    private User m1 = new User("hyt", "C 2998-mysql",
            "Yitong Hu", true);
    private User m2 = new User("admin", "password",
            "HYT", true);

    @org.junit.jupiter.api.Test
    void delWorkerFromWh() throws Exception {
        userDAO.login(m1);
        userDAO.delWorkerFromWh(m1, 19, 10);
    }

    @Test
    void getUser() throws Exception {
        User u = userDAO.getUser(m1.getUsername());
        System.out.println(u.getName());
    }

    @Test
    void addUser() throws Exception {
        User u = new User("mike3", "123456",
                "Mike3", false);
        userDAO.addUser(u);
    }

    @Test
    void checkPasswordStrength() throws Exception {
        String password = "123";
        System.out.println(userDAO.checkPasswordStrength(password));
    }

    @Test
    void login() throws Exception {
        userDAO.login(m1);
        System.out.println(m1.getHasWarehouse());
    }

    @Test
    void registerWorker() throws Exception {
        User u = new User("mike4", "123456",
                "Mike4", false);
        userDAO.registerWorker(u);
    }

    @Test
    void addManger() throws Exception {
        ArrayList<Integer> whs = new ArrayList<>();
        whs.add(1);
        whs.add(2);
        whs.add(3);
        whs.add(10);
        userDAO.addManger(m2, whs);
        userDAO.login(m2);
        System.out.println(m2.getHasWarehouse());
    }

    @Test
    void getAccess() throws Exception {
        User u = userDAO.getUser(m1.getUsername());
        userDAO.getAccess(u);
        System.out.println(u.getHasWarehouse());
    }

    @Test
    void addWorkerToWh() throws Exception {
        userDAO.login(m1);
        userDAO.addWorkerToWh(m1, 19, 10);
    }
}