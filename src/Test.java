import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("lj");
        user.setPassword("C 2998-mysql");
        UserDAO userDAO = new UserDAOImpl();
        int flag = userDAO.login(user);
        if (flag == 1) {
            System.out.println("You are in!");
        }
        else if (flag == -1) System.out.println("Not a user.");
        else System.out.println("Wrong password.");
    }
}
