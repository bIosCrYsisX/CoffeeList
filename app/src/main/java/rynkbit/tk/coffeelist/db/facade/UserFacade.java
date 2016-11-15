package rynkbit.tk.coffeelist.db.facade;

import android.app.Activity;
import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rynkbit.tk.coffeelist.admin.user.ManageUserActivity;
import rynkbit.tk.coffeelist.db.DbHelper;
import rynkbit.tk.coffeelist.db.entity.Item;
import rynkbit.tk.coffeelist.db.entity.Protocol;
import rynkbit.tk.coffeelist.db.entity.User;
import rynkbit.tk.coffeelist.item.ItemActivity;

/**
 * Created by michael on 11/13/16.
 */

public class UserFacade {
    public static List<User> getUsers(Context activity){
        DbHelper dbHelper = OpenHelperManager.getHelper(activity, DbHelper.class);
        Dao<User, Integer> userDao = null;
        List<User> result = new LinkedList<>();

        try {
            userDao = DaoManager.createDao(dbHelper.getConnectionSource(), User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            result = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        OpenHelperManager.releaseHelper();
        return result;
    }

    public static User insert(Context activity, String username) {
        DbHelper dbHelper = OpenHelperManager.getHelper(activity, DbHelper.class);
        Dao<User, Integer> userDao = null;
        User user = new User();
        user.setName(username);

        try {
            userDao = DaoManager.createDao(dbHelper.getConnectionSource(), User.class);
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
            user = null;
        }

        OpenHelperManager.releaseHelper();
        return user;
    }

    public static void removeUser(Context activity, User mUser) {
        DbHelper dbHelper = OpenHelperManager.getHelper(activity, DbHelper.class);
        Dao<User, Integer> userDao = null;

        try {
            userDao = DaoManager.createDao(dbHelper.getConnectionSource(), User.class);
            userDao.delete(mUser);
        }catch (SQLException e){
            e.printStackTrace();
        }
        OpenHelperManager.releaseHelper();
    }

    public static void renameUser(Context activity,
                                  User user,
                                  String newName){
        DbHelper dbHelper = OpenHelperManager.getHelper(activity, DbHelper.class);
        Dao<User, Integer> userDao = null;
        user.setName(newName);

        try {
            userDao = DaoManager.createDao(dbHelper.getConnectionSource(), User.class);
            userDao.update(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void even(Context activity, User user) {
        DbHelper dbHelper = OpenHelperManager.getHelper(activity, DbHelper.class);
        Dao<User, Integer> userDao = null;
        double oldBalance = user.getBalance();
        user.setBalance(0);

        try {
            userDao = DaoManager.createDao(dbHelper.getConnectionSource(), User.class);
            userDao.update(user);

            StringBuilder builder = new StringBuilder();
            builder
                    .append("User evened;")
                    .append(user.getId())
                    .append(";")
                    .append(user.getName())
                    .append(";")
                    .append(user.getBalance())
                    .append(";")
                    .append(oldBalance);

            ProtocolFacade.writeLine(activity, builder.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(Context context, User user) {
        DbHelper dbHelper = OpenHelperManager.getHelper(context, DbHelper.class);
        try {
            Dao<User, Integer> userDao =
                    DaoManager.createDao(
                            dbHelper.getConnectionSource(),
                            User.class);
            Dao<Item, Integer> itemDao =
                    DaoManager.createDao(
                            dbHelper.getConnectionSource(),
                            Item.class);

            User oldUser = userDao.queryForId(user.getId());
            userDao.update(user);

            StringBuilder builder = new StringBuilder();
            builder.append("Updated user:")
                    .append(";")
                    .append(oldUser.getId())
                    .append(";")
                    .append(oldUser.getName())
                    .append(";")
                    .append(oldUser.getBalance())
                    .append(";")
                    .append("to")
                    .append(";")
                    .append(user.getName())
                    .append(";")
                    .append(user.getBalance());
            ProtocolFacade.writeLine(context, builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        OpenHelperManager.releaseHelper();
    }
}
