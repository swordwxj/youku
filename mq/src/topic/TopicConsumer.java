package topic;

import common.Pool;

import javax.jms.*;

public class TopicConsumer {
        public static void receiver(){
            Connection connection = Pool.createCon();
            try {
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Topic topic = session.createTopic("second");
                MessageConsumer consumer = session.createConsumer(topic);
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        TextMessage msg = (TextMessage) message;
                        try {
                            System.out.println("receive:"+ msg.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    public static void main(String[]  args){
        receiver();
    }
}
