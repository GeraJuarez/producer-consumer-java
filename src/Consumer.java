
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zegerd
 */
public class Consumer extends Thread {
    Buffer buffer;
    String name;

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Running consumer... ");
        String product = "";
        
        while (true) {
            product = this.buffer.consumeQ();
            System.out.println("Consumer " + this.name + " consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
}
