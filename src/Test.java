import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();

        User user1 = new User();
        user1.setUsername("lmh");
        user1.setPassword("123");
        user1.isManger(true);

        User user2 = new User();
        user2.setUsername("wie2");
        user2.setName("Widerholung2");
        user2.setPassword("123");

        int flag = userDAO.login(user1);
        if (flag == 1) {
            System.out.println("You are in!");
            userDAO.addManger(user1, user2);
            System.out.println("Add manger successfully!");
            user2.setUsername("mike2");
            userDAO.addWorker(user1, user2);
            System.out.println("Add worker successfully!");
        }
        else if (flag == -1) System.out.println("Not a user.");
        else System.out.println("Wrong password.");

    }
}