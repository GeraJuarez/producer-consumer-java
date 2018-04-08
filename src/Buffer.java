
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zegerd & Dragv
 */
public class Buffer {
    private BlockingQueue<String> bufferQ;
    private final DefaultTableModel model;
    private final JTable table;

    public Buffer(int size, JTable table) {
        this.bufferQ = new LinkedBlockingQueue<>(size);
        this.table = table;
        this.model =(DefaultTableModel) table.getModel();
    }
    
    public String consumeQ() {
        String product = null;
        try {
            product = this.bufferQ.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //notify();
        return product;
    }
    
    public void produceQ (String product) {
        try {
            model.addRow(new Object[] { product, "Column 2" });
            this.bufferQ.put(product);
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //notify();
    }
    
    public BlockingQueue<String> getQueue (){
        return this.bufferQ;
    }
    
/* TODO delete this from above later */
}

/*
definir tamanio de buffer de strings, arreglo o arrylist de strings
interfaz que eprmite el numero de cosumidores y productores
productores se denitenen cuando el buffer esta lleno
consumidores se detienen cuando no hay nada en el buffer
se le agrega un tiempo definido por usuario para el tiempo de producir  consumir respectivamente

la interfaz debe llevar las tareas por hacer y otra tabala con las tareas realziadas
y campos para el rango de numeros generados

Productor: 4 operaciones, suma, resta, multiplicaicon o divicion
- genera una funcion de squeme
Tabala: 
= Productor n producio (/ 1 2)
= COnsumidor n conusmio (/1 2) resultado 2
= debajo de cada tabal debe llevar cun contador de las cosas producidas y el de contadores

*/