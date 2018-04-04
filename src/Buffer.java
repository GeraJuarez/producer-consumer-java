
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Zegerd & Dragv
 */
public class Buffer {
    private BlockingQueue<String> bufferQ;

    public Buffer(int size) {
        this.bufferQ = new LinkedBlockingQueue<>(size);
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
            this.bufferQ.put(product);
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //notify();
    }
    
    
 /* TODO delete this later */
    private char buffer; //delete this later
    public Buffer() {
        // delete this cosntructor later
        this.buffer = 0; //delete this later
    }
    
    // synchronized permite que se bloqueen las demas funciones de la clase cuando una de ellas esta activa
    synchronized char consume() {
        char product = 0;
        
        if (product == 0) {
            try {
                wait(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        product = this.buffer;
        this.buffer = 0;
        // notify() desperta el primer hilo que utilizo el metodo wait()
        // notifyAll() despierta todos los hilos en wait() de esta clase
        notify();
        
        return product;
    }
    
    synchronized void produce(char product) {
        if (this.buffer != 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        notify();
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