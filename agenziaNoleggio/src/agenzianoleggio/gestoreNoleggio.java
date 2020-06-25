package agenzianoleggio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 *
 * @author lucadev23
 */
public class gestoreNoleggio {
    private LinkedList<automobili> listaAutomobili;
    private BufferedReader tastiera;
    private automobili marcaMaggioreGuadagno;
    
    public gestoreNoleggio(){
        listaAutomobili = new LinkedList<automobili>();
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void calcolaMarcaMaggioriGuadagni(){
        float costo=0.0f,calcolo;
        for(automobili a: listaAutomobili){
            calcolo=a.getCostoTotale();
            if(costo<calcolo){
                marcaMaggioreGuadagno=a;
            }
        }
    }
    
    public void stampaMarcaMaggioriGuadagni(){
        if(marcaMaggioreGuadagno==null){
            System.out.println("Nessuna automobili caricata");
            return;
        }
        System.out.println("Marca con maggiore guadagni: "+marcaMaggioreGuadagno.getMarca());
    }
    
    public float totaleCostiMarca(String marca){
        float costo=0.0f;
        for(automobili a: listaAutomobili){
            if(a.getMarca().equalsIgnoreCase(marca)==true){
                costo += a.getCostoTotale();
            }
        }
        return costo;
    }
    
    public boolean controlloEsistenzaMarca(String marca){
        for(automobili a: listaAutomobili){
            if(a.getMarca().equalsIgnoreCase(marca)==true){
                return true;
            }
        }
        return false;
    }
    
    public void stampaTotaleCostiMarca(){
        try{
            System.out.println("Inserisci la marca: ");
            String marca = tastiera.readLine();
            if(controlloEsistenzaMarca(marca)==false){
                System.out.println("Marca "+marca+" non esistente");
                return;
            }
            System.out.println("Totale costo: "+totaleCostiMarca(marca));
        }catch(IOException ioe){
            System.out.println("Errore I/O");
            System.exit(-1);
        }
    }
    
    public void eliminaClienteDaStruttura(String codice){
        for(automobili a: listaAutomobili){
            a.eliminaCliente(codice);
        }
    }
    
    public void controllaEsitenzaCodiceFiscale(String codice) throws clienteNonEsistenteException{
        for(automobili a: listaAutomobili){
            if(a.clienteEsistente(codice)){
                return;
            }
        }
        throw new clienteNonEsistenteException();
    }
    
    public void eliminaCliente(){
        try{
            System.out.println("Inserisci il codice fiscale: ");
            String codice = tastiera.readLine();
            controllaEsitenzaCodiceFiscale(codice);
            eliminaClienteDaStruttura(codice);
            System.out.println("Cliente "+codice+" eliminato dalla struttura");
        }catch(IOException ioe){
            System.out.println("Errore I/O");
            System.exit(-1);
        }
        catch(clienteNonEsistenteException cne){
            System.out.println("Errore: cliente non esistente");
            return;
        }
    }
    
    public void aggiungiCliente(int codice, clienti c){
        for(automobili a: listaAutomobili){
            if(a.getCodice()==codice){
                a.caricaCliente(c);
            }
        }
    }
    
    public void caricaClienti(){
        try{
            BufferedReader fpClienti = new BufferedReader(new FileReader("listaNoleggio.txt"));
            
            String codice;
            String codiceFiscale;
            String nomeCognome;
            int durata;
            
            codice = fpClienti.readLine();
            while(codice!=null){
                codiceFiscale = fpClienti.readLine();
                nomeCognome = fpClienti.readLine();
                durata = Integer.parseInt(fpClienti.readLine());
                aggiungiCliente(Integer.parseInt(codice),new clienti(codiceFiscale, nomeCognome, durata));
                codice=fpClienti.readLine();
            }
        }catch(FileNotFoundException fex){
            System.out.println("Errore apertura file 'modelliAuto.txt'");
            System.exit(-1);
        }
        catch(IOException ioe){
            System.out.println("Errore I/O");
            System.exit(-1);
        }
    }
    
    public void caricaModelliAuto(){
        try{
            BufferedReader fpModelli = new BufferedReader(new FileReader("modelliAuto.txt"));
            String modello, marca;
            int codice;
            float costo;
            modello = fpModelli.readLine();
            while(modello!=null){
                codice=Integer.parseInt(fpModelli.readLine());
                marca = fpModelli.readLine();
                costo=Float.parseFloat(fpModelli.readLine());
                listaAutomobili.add(new automobili(modello, codice, marca, costo));
                modello=fpModelli.readLine();
            }
        }catch(FileNotFoundException fex){
            System.out.println("Errore apertura file 'modelliAuto.txt'");
            System.exit(-1);
        }
        catch(IOException ioe){
            System.out.println("Errore I/O");
            System.exit(-1);
        }
    }
}
