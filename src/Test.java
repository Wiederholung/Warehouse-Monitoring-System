import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();

        User manager = new User();
        manager.setUsername("hyt");
        manager.setPassword("C 2998-mysql");

        User user2 = new User();
        user2.setUsername("xmll");
        user2.setName("Wiederholung3");
        user2.setPassword("123456");

        try {
            userDAO.login(manager);
//            userDAO.addUser(user2);
//            userDAO.registerWorker(user2);
            user2 = userDAO.getUser("xmll");
            userDAO.addWorkerToWh(manager, user2.getUserID(), 1);
            userDAO.addWorkerToWh(manager, user2.getUserID(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye World!");
    }
}