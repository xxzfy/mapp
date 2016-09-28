package com.zhao.mapp.tools;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.zhao.mapp.MApplication;
import com.zhao.mapp.greendao.DaoSession;
import com.zhao.mapp.greendao.Users;
import com.zhao.mapp.greendao.UsersDao;

public class DBHelper {
    private static final String TAG = DBHelper.class.getSimpleName();  
    private static class mHolder{
    	private static final DBHelper INSTANCE=new DBHelper();
    }
    private static Context appContext;
    private DaoSession mDaoSession;  
    private UsersDao usersDao;
    private DBHelper() {
    } 
    //单例模式，DBHelper只初始化一次
    public static  DBHelper getInstance(Context context) {
    	DBHelper instance= mHolder.INSTANCE;
        if (instance == null) {
                   instance = new DBHelper();  
                   if (appContext == null){  
                       appContext = context.getApplicationContext();  
                   }  
                   instance.mDaoSession = MApplication.getDaoSession();
                   instance.usersDao = instance.mDaoSession.getUsersDao();
               } 
        return instance;  
    }


    //删除Session表
    public  void dropUsersTable()
    {
    	UsersDao.dropTable(mDaoSession.getDatabase(), true);
    }
   //删除所有表
    public void dropAllTable()
    {
    	UsersDao.dropTable(mDaoSession.getDatabase(), true);
    }
    //创建所有表
    public void createAllTable()
    {
    	UsersDao.createTable(mDaoSession.getDatabase(), true);
    }
    /** 
     * insert or update note 
     * @param note 
     * @return insert or update note id 
     */  
    //插入或者删除users项
    public long saveSession(Users users){  
        return usersDao.insertOrReplace(users);  
    }  


    //获得所有的user倒序排存到List列表里面
    public List<Users> loadAllUser() {
        List<Users> users = new ArrayList<Users>();
        List<Users> tmpUsers = usersDao.loadAll();
        int len = tmpUsers.size();
        for (int i = len-1; i >=0; i--) {
        	users.add(tmpUsers.get(i));
        }
        return users;
    }  


    public void DeleteUsers(Users user) {
    	usersDao.delete(user);
    }  
   
    //根据id找到某一项
    public Users loadNote(long id) {
        return usersDao.load(id);  
    }  
    //获得所有的MqttChatEntity列表 
    public List<Users> loadAllNote(){  
        return usersDao.loadAll();  
    }  


    /** 
     * query list with where clause 
     * ex: begin_date_time >= ? AND end_date_time <= ? 
     * @param where where clause, include 'where' word 
     * @param params query parameters 
     * @return 
     */  
      //查询满足params条件的列表
    public List<Users> queryUsers(String where, String... params){
        ArrayList<Users> ad = new ArrayList<Users>();
        return usersDao.queryRaw(where, params); 
    }


    /** 
     * delete all note 
     */  
    public void deleteAllUsers(){  
        usersDao.deleteAll();  
    }  


    /** 
     * delete note by id 
     * @param id 
     */  
    public void deleteUsers(long id){  
        usersDao.deleteByKey(id);  
        Log.i(TAG, "delete");  
    }  


    public void deleteUsers(Users user){  
        usersDao.delete(user);  
    }  
}