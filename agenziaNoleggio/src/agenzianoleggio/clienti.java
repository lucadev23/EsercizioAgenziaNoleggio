package agenzianoleggio;

/**
 *
 * @author lucadev23
 */
public class clienti {
    
    private String codiceFiscale;
    private String nomeCognome;
    private int durataNoleggio;
    
    public clienti(String codiceFiscale, String nomeCognome, int durataNoleggio){
        this.codiceFiscale=codiceFiscale;
        this.nomeCognome=nomeCognome;
        this.durataNoleggio=durataNoleggio;
    }
    
    public int getDurataNoleggio(){
        return this.durataNoleggio;
    }
    
    @Override
    public boolean equals(Object c){
        return this.codiceFiscale.equalsIgnoreCase( ((clienti)c).getCodice() );
    }
    
    public String getCodice(){
        return this.codiceFiscale;
    }
}
