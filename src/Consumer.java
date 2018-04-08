
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zegerd
 */
public class Consumer extends Thread {
    Buffer buffer;
    String name;
    int consumeWaitTime;
    private final DefaultTableModel model;
    private final DefaultTableModel model1;
    private final JTable table;
    private final JTable table1;
    
    // Constant Values for parsing
    private final int operatorPos = 1;
    private final int Num1Pos = 2;
    private final int Num2Pos = 3;

    public Consumer(Buffer buffer, int waitTime, JTable table, JTable table1) {
        this.buffer = buffer;
        this.name = "";
        this.consumeWaitTime = waitTime;
        this.table = table;
        this.model =(DefaultTableModel) table.getModel();
        this.table1 = table1;
        this.model1 =(DefaultTableModel) table1.getModel();
    }
   
    
    private float parsePrefixOperation(String operation) {
        float result = 0;
        String[] tokens = operation.split(" ");
        
        String op = tokens[operatorPos];
        float num1 = Float.parseFloat(tokens[Num1Pos]);
        float num2 = Float.parseFloat(tokens[Num2Pos]);
        
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;
        }
        
        return result;
    }

    @Override
    public void run() {
        System.out.println("Running consumer... ");
        String product = "";
        float productResult = 0;
        
        while (true) {
            product = this.buffer.consumeQ();
            productResult = parsePrefixOperation(product);
            System.out.println("Consumer " + this.name + " consumed: " + product + " = " + productResult);
            model.addRow(new Object[] { product + " = " + productResult });
            //model1.removeRow(0);
            try {
                Thread.sleep(consumeWaitTime * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
}
