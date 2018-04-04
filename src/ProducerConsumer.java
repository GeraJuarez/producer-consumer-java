/**
 *
 * @author Zegerd
 */
public class ProducerConsumer {
    
    public static void main(String[] args) {
        
        Buffer buffer = new Buffer(2);
        
        Producer producer = new Producer(buffer, "1");
        producer.start();
        
        Consumer consumer = new Consumer(buffer, "1");
        consumer.start();
        
        Consumer consumer2 = new Consumer(buffer, "2");
        consumer2.start();
        
        Consumer consumer3 = new Consumer(buffer, "3");
        consumer3.start();
    }
}
