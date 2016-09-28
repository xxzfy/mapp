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
    //��֮main������ִ�������漸������                                                                                                                                                                                                                                              
    public static void main(String[] args) throws Exception
    {
    // ����3�����ݿ�汾�ţ���com.cn.speedchat.greendao���ǰ�����Ҳ����˵���ɵ�Dao�ļ�����������£����Խ�Schema���Ϊ���ݿ������İ�
         Schema schema = new Schema(3, "com.zhao.mapp.greendao"); 
         //�����������൱�ڽ����������������㶼���Բ��ù��˻��Զ�����
        addUsers(schema);       
    //���������Dao�ļ���·����λ�ã��������ǰ���̵���һ��Ŀ¼��javagreendao��src-gen�ļ������棬��ʵ���Ǹ�srcͬһ��Ŀ¼���������Լ�Ҫ��srcͬһ��Ŀ¼���½�һ��src-gen�ļ��д���Ҫ���ɵ��ļ�
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