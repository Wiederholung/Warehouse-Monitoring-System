import model.dao.UserDAO;
import model.dao.WarehouseDAO;
import model.dao.impl.UserDAOImpl;
import model.dao.impl.WarehouseDAOImpl;
import model.vo.User;
import model.vo.Warehouse;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // 用户对象
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

            userDAO.login(manager); // 登陆
//            userDAO.registerWorker(user2);    // 注册工人 未指定仓库
//            userDAO.addManger(manager2, new ArrayList<>() {{ // 管理员添加管理者 并且授权仓库
//                add(1);
//                add(2);
//                add(3);
//            }});
            user2 = userDAO.getUser("jake");    // 获取工人ID
//            userDAO.addWorkerToWh(manager, user2.getUserID(), 1);
//            userDAO.addWorkerToWh(manager, user2.getUserID(), 3);
            userDAO.delWorkerFromWh(manager, user2.getUserID(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 仓库对象
        try {
            Warehouse wh = new Warehouse("3rd", "Sport");
            WarehouseDAO whDAO = new WarehouseDAOImpl();

//            whDAO.addWarehouse(wh); // 添加仓库 未指定货架和容量
            whDAO.getWarehouseID(wh); // 获取仓库ID

            for (Warehouse warehouse : whDAO.queryWarehouse()) { // 查询仓库
                System.out.println(warehouse);
            }

            for (Warehouse warehouse : whDAO.queryWarehouse(wh)) { // 查询指定仓库
                System.out.println(warehouse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 程序结束
        System.out.println("Goodbye World!");
    }
}