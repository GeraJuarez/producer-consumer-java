
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zegerd
 */
public class Producer extends Thread {
    Buffer buffer;
    String name;

    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Running producer... ");
        String products = "AEIOU";
        Random r = new Random();
        char product = 0;
        
        while (true) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produceQ(Character.toString(product) + this.name);
            System.out.println("Producer " + this.name + " produced: " + product);
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
