package agenzianoleggio;

import java.util.LinkedList;

/**
 *
 * @author lucadev23
 */
public class automobili {
    
    private String modello;
    private int codice;
    private String marca;
    private float costoGiornaliero;
    private LinkedList<clienti> listaClienti;
    
    public automobili(String modello, int codice, String marca, float costoGiornaliero){
        this.modello=modello;
        this.codice=codice;
        this.marca=marca;
        this.costoGiornaliero=costoGiornaliero;
        listaClienti = new LinkedList<clienti>();
    }
    
    public float getCostoTotale(){
        float costo = 0.0f;
        for(clienti c: listaClienti){
            costo += (c.getDurataNoleggio()*this.costoGiornaliero);
        }
        return costo;
    }
    
    public String getMarca(){
        return this.marca;
    }
    
    public void eliminaCliente(String codice){
        listaClienti.remove(new clienti(codice,null,0));
    }
    
    public boolean clienteEsistente(String codice){
        for(clienti c: listaClienti){
            if(c.getCodice().equalsIgnoreCase(codice)){
                return true;
            }
        }
        return false;
    }
    
    public void caricaCliente(clienti c){
        listaClienti.add(c);
    }
    
    public int getCodice(){
        return this.codice;
    }
}
