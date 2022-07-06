import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();

        User user = new User();
        user.setUsername("mike");
        user.setPassword("123");
        user.isManger(true);
        user.setName("Mike");
        user.setAge(20);
        user.setPhone("123456789");
        user.setGender("Male");

        userDAO.register(user);

        int flag = userDAO.login(user);
        if (flag == 1) {
            System.out.println("You are in!");
            System.out.println(user.getGender() + " " + user.isManger());
        }
        else if (flag == -1) System.out.println("Not a user.");
        else System.out.println("Wrong password.");
    }
}