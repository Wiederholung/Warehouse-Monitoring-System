import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("C 2998-mysql");
        UserDAO dao = new UserDAOImpl();
        int flag = dao.queryByUsername(user);
        if (flag == 1) {
            System.out.println("success");
        }
        else if (flag == -1) System.out.println("No User");
        else System.out.println("wrong password");
    }

}
