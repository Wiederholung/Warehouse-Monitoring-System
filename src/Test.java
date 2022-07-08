import model.dao.UserDAO;
import model.dao.WarehouseDAO;
import model.dao.impl.UserDAOImpl;
import model.dao.impl.WarehouseDAOImpl;
import model.vo.User;
import model.vo.Warehouse;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            // 登陆用
            User manager = new User("hyt", "C 2998-mysql",
                    "Yitong Hu", true);
            // 注册管理者
            User manager2 = new User("admin", "password",
                    "Admin", true);
            // 注册工人
            User user2 = new User("jake", "jk1234456",
                    "Jake", false);

//            userDAO.login(manager); // 登陆
//            userDAO.registerWorker(user2);    // 注册工人
//            userDAO.addManger(manager2, new ArrayList<>() {{
//                add(1);
//                add(2);
//                add(3);
//            }});
//            user2 = userDAO.getUser("xmll");
//            userDAO.addWorkerToWh(manager, user2.getUserID(), 1);
//            userDAO.addWorkerToWh(manager, user2.getUserID(), 3);
//            userDAO.delWorkerFromWh(manager, user2.getUserID(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Warehouse wh = new Warehouse("3rd", "Sport");
            WarehouseDAO whDAO = new WarehouseDAOImpl();

            whDAO.addWarehouse(wh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye World!");
    }
}