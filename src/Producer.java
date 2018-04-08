
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zegerd
 */
public class Producer extends Thread {
    Buffer buffer;
    String name;
    int productionWaitTime;
    int minRandNumber;
    int maxRandNumber;
    int range;
    private final DefaultTableModel model;
    private final JTable table;
    
    // Constant indices for the production of operations
    final String operations = "+-*/";

        public Producer(Buffer buffer, int waitTime, int min, int max, JTable table) {
        this.buffer = buffer;
        this.name = "";
        this.productionWaitTime = waitTime;
        this.minRandNumber = min;
        this.maxRandNumber = max;
        this.table = table;
        this.model =(DefaultTableModel) table.getModel();
    }
    

    @Override
    public void run() {
        System.out.println("Running producer... ");
        String product;
        
        while (!Thread.currentThread().isInterrupted()) {
            product = producePrefixOperation();
            this.buffer.produceQ(product);
            System.out.println("Producer " + this.name + " produced: " + product);
            model.addRow(new Object[] { product });
            try {
                Thread.sleep(productionWaitTime * 1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                //Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
    }
    
    private String producePrefixOperation() {
        // Create operations with this form: (operation n1 n2)
        String prefixOp = "";
        String tempNumber;
        int randSeed;
        
        // Init random
        Random opRand = new Random();
        Random rangeRand = new Random();
        
        // init default operation
        StringBuilder sb = new StringBuilder("( ");
        
        // Get random operator and replace
        String tempOperation = Character.toString( operations.charAt(opRand.nextInt(4)) );
        sb.append(tempOperation).append(" ");
        
        // Get random number and replace
        randSeed = rangeRand.nextInt((this.maxRandNumber - this.minRandNumber) + 1);
        tempNumber = "" + (randSeed + minRandNumber);
        sb.append(tempNumber).append(" ");
        // ----
        randSeed = rangeRand.nextInt((this.maxRandNumber - this.minRandNumber) + 1);
        tempNumber = "" + (randSeed + minRandNumber);
        sb.append(tempNumber);
        
        sb.append(" )");
        
        prefixOp = sb.toString(); 
        return prefixOp;
    }
}
