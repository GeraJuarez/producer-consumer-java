
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zegerd & Dragv
 */
public class Buffer {
    private BlockingQueue<String> bufferQ;
    private JProgressBar progress;
    private int size;

    public Buffer(int size, JProgressBar progress) {
        this.size = size;
        this.bufferQ = new LinkedBlockingQueue<>(size);
        this.progress = progress;
        //this.progress.setMaximum(size);
        //this.progress.setMinimum(size);
        this.progress.setValue(0);
        this.progress.setStringPainted(true);
    }
    
    public String consumeQ() {
        String product = null;
        try {
            product = this.bufferQ.take();
            //System.out.println(this.bufferQ.size());
            this.progress.setValue( (this.bufferQ.remainingCapacity()*100) / this.size );
          
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //notify();
        return product;
    }
    
    public void produceQ (String product) {
        try {
            this.bufferQ.put(product);
            //System.out.println(this.bufferQ.size());
            this.progress.setValue( (this.bufferQ.remainingCapacity()*100) / this.size );
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //notify();
    }
    
    public BlockingQueue<String> getQueue (){
        return this.bufferQ;
    }
    
    synchronized void addRowProducer(Object[] row, DefaultTableModel tableProd, boolean add){
        if (add) {
            tableProd.addRow(row);
        } else {
            tableProd.removeRow(0);
        }
        
    }
    
    synchronized void  addRowConsumer(Object[] row, DefaultTableModel tableCons, DefaultTableModel tableProd) {
        tableCons.addRow(row);
        addRowProducer(row, tableProd, false);
    }
}