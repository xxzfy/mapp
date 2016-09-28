package javagreendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
/**
 * Generates entities and DAOs for the example project DaoExample.
 *
 * Run it as a Java application (not Android).
 *
 * @author Markus
 */
public class ExampleDaoGenerator
{
    //总之main函数就执行了下面几个函数                                                                                                                                                                                                                                              
    public static void main(String[] args) throws Exception
    {
    // 参数3是数据库版本号，“com.cn.speedchat.greendao”是包名，也就是说生成的Dao文件会在这个包下，可以将Schema理解为数据库上下文吧
         Schema schema = new Schema(3, "com.zhao.mapp.greendao"); 
         //这三个函数相当于建立了三个表，表名你都可以不用管了会自动生成
        addUsers(schema);       
    //这个是生成Dao文件的路径的位置，这个代表当前工程的上一级目录的javagreendao的src-gen文件夹里面，其实就是跟src同一级目录，所以你自己要在src同一级目录下新建一个src-gen文件夹待会要生成的文件
        new DaoGenerator().generateAll(schema, "../javagreendao/src-gen");    
    }
    
    private static void addUsers(Schema schema){
    	Entity users=schema.addEntity("Users");
    	users.addIdProperty().autoincrement();
    	users.addStringProperty("loginname").notNull();
    	users.addStringProperty("username");
    	users.addStringProperty("md5password");
    	users.addDateProperty("lastlogintime");
    }  
                                                                                                                                                                                                                                                                                                                 
}