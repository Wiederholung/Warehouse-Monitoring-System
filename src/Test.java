import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();

        User manager = new User();
        manager.setUsername("hyt");
        manager.setPassword("C 2998-mysql");

        User manager2 = new User();
        manager2.setUsername("admin");
        manager2.setName("Admin");
        manager2.setPassword("password");

        User user2 = new User();
        user2.setUsername("xmll");
        user2.setName("Wiederholung3");
        user2.setPassword("123456");

        try {
            userDAO.login(manager);
//            userDAO.addUser(user2);
//            userDAO.registerWorker(user2);
            userDAO.addManger(manager2, new ArrayList<Integer>(){{add(1);add(2);add(3);}});

//            user2 = userDAO.getUser("xmll");
//            userDAO.addWorkerToWh(manager, user2.getUserID(), 1);
//            userDAO.addWorkerToWh(manager, user2.getUserID(), 3);
//            userDAO.delWorkerFromWh(manager, user2.getUserID(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye World!");
    }
}