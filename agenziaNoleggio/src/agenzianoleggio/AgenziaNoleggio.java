package agenzianoleggio;

/**
 *
 * @author lucadev23
 */
public class AgenziaNoleggio {

    
    public static void main(String[] args) {
        gestoreNoleggio gestore = new gestoreNoleggio();
        threadForeground tf = new threadForeground(gestore);
        threadBackground tb = new threadBackground(gestore);
        
        tf.start();
        tb.start();
    }
    
}
