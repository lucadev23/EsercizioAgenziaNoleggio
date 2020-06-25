package agenzianoleggio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucadev23
 */
public class threadForeground extends Thread{
    
    private gestoreNoleggio gestore;
    private BufferedReader tastiera;
    
    public threadForeground(gestoreNoleggio gestore){
        this.gestore=gestore;
        tastiera=new BufferedReader(new InputStreamReader(System.in));
    }
    
    @Override
    public void run(){
        gestore.caricaModelliAuto();
        gestore.caricaClienti();
        gestore.calcolaMarcaMaggioriGuadagni(); // l'ho messo ANCHE qui per efficentare il processo di aggiornamento
        while(true){
            switch(menu()){
                case 1:
                    gestore.eliminaCliente();
                    break;
                case 2:
                    gestore.stampaTotaleCostiMarca();
                    break;
                case 3:
                    gestore.stampaMarcaMaggioriGuadagni();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Valore non valido!");
                    break;
            }
        }
    }
    
    public int menu(){
        int valore=-1;
        System.out.print("1 - Elimina cliente\n"
                + "2 - Totale costi data la marca\n"
                + "3 - Stampa modello con maggiore guadagni\n"
                + "0 - Esci\n"
                + "Scelta: ");
        try{
            valore = Integer.parseInt(tastiera.readLine());
        }catch(IOException ioe){
            System.out.println("Errore I/O");
            System.exit(-1);
        }catch(NumberFormatException nfe){
            System.out.println("Devi inserire un valore intero");
        }
        return valore;
    }
}
