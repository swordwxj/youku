package queue;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

//消息生产者
public class QueueProducer {

    public static  void send(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            //获取连接
            Connection connection = factory.createConnection();
            //创建会话
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //对列
            Queue queue = session.createQueue("first");
            //生产者
            MessageProducer producer = session.createProducer(queue);
            //消息
            TextMessage message = session.createTextMessage("第一条消息");
            producer.send(message);
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static  void main(String[] args){
            send();
    }
}
