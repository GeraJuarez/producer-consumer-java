/**
 *
 * @author Zegerd
 */
public class ProducerConsumer {
    
    public static void main(String[] args) {
        
        ConsumerProducerInterface panel = new ConsumerProducerInterface();
        panel.setVisible(true);
        
        Buffer buffer = new Buffer(2);
        
        //Producer producer = new Producer(buffer, "1");
        Producer producer = new Producer(buffer, 5, 10, 15);
        producer.start();
        
        //Consumer consumer = new Consumer(buffer, "1");
        Consumer consumer = new Consumer(buffer, 3);
        consumer.start();
        
        //Consumer consumer2 = new Consumer(buffer, "2");
        Consumer consumer2 = new Consumer(buffer, 3);
        consumer2.start();
        
        //Consumer consumer3 = new Consumer(buffer, "3");
        Consumer consumer3 = new Consumer(buffer, 3);
        consumer3.start();
    }
}
