package agenzianoleggio;

/**
 *
 * @author lucadev23
 */
public class threadBackground extends Thread{
    
    private gestoreNoleggio gestore;
    
    public threadBackground(gestoreNoleggio gestore){
        this.gestore=gestore;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                this.sleep(5000);
                gestore.calcolaMarcaMaggioriGuadagni();
            }catch(InterruptedException iex){
                System.out.println("Errore Interrupt");
            }
        }
    }
}
