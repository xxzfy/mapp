package javagreendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
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
         //addNote() addSession() addReplay()�����������൱�ڽ����������������㶼���Բ��ù��˻��Զ�����
        addNote(schema);       
        addSession(schema);
        addReplay(schema);
        addCustomerOrder(schema);
    //���������Dao�ļ���·����λ�ã��������ǰ���̵���һ��Ŀ¼��javagreendao��src-gen�ļ������棬��ʵ���Ǹ�srcͬһ��Ŀ¼���������Լ�Ҫ��srcͬһ��Ŀ¼���½�һ��src-gen�ļ��д���Ҫ���ɵ��ļ�
        new DaoGenerator().generateAll(schema, "../javagreendao/src-gen");    
    }
      //�����һ��Note��,Ȼ������node.add***�Ǳ���ֶ����Լ�����                                                                                                                                                                                           
    private static void addNote(Schema schema)                 
    {
        //"MqttChatEntity"�൱���Ǳ����������MqttChatEntity���ɶ���Ϳ��Է�������������ˣ�Ҳ����������Ӧ������࣬����ʹ����ͻ�������
        Entity note = schema.addEntity("MqttChatEntity");    
        note.addIdProperty().autoincrement();
        note.addIntProperty("mode").notNull();
        note.addStringProperty("sessionid").notNull();
        note.addStringProperty("from").notNull();
        note.addStringProperty("to").notNull();
        note.addStringProperty("v_code");
        note.addStringProperty("timestamp").notNull();
        note.addStringProperty("platform");
        note.addStringProperty("message");
        note.addBooleanProperty("isread").notNull();
        note.addLongProperty("gossipid");
        note.addStringProperty("gossip");
        note.addIntProperty("chattype").notNull();
        note.addStringProperty("imagepath");
        note.addStringProperty("base64image");
    }
    //�����һ��Session��,Ȼ������node.add***�Ǳ���ֶ����Լ����ԣ�������д�ĻỰ��һ����
    private static void addSession(Schema schema)     
    {
        Entity note = schema.addEntity("SessionEntity");
        note.addIdProperty().autoincrement();
        note.addStringProperty("sessionid").notNull().unique();
        note.addStringProperty("from").notNull();
        note.addStringProperty("to").notNull();
        note.addLongProperty("gossipid").notNull();
        note.addStringProperty("gossip");
        note.addIntProperty("sessiontype").notNull();
        note.addBooleanProperty("asdasd").notNull();
    }
  //�����һ��Replay��,Ȼ������node.add***�Ǳ���ֶ����Լ����ԣ�������д�Ļظ���һ����
    private static void addReplay(Schema schema)
    {
        //ReplayEntity��Ӧ������
        Entity note = schema.addEntity("ReplayEntity");    
        note.addIdProperty().autoincrement();
        note.addIntProperty("mode").notNull();
        note.addStringProperty("from").notNull();
        note.addStringProperty("to").notNull();
        note.addStringProperty("v_code");
        note.addStringProperty("timestamp").notNull();
        note.addStringProperty("platform");
        note.addStringProperty("message");
        note.addIntProperty("msgtype").notNull();
        note.addBooleanProperty("isread").notNull();
    } 
    //������ù��ˣ��ճ���
    private static void addCustomerOrder(Schema schema)    
    {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();


        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);


        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }                                                                                                                                                                                                                                                                                                              
}