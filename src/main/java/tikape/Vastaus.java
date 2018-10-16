/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape;

/**
 *
 * @author ptalonen
 */


public class Vastaus {
 
    private Integer id;
    private String vastausteksti;
    private boolean oikein;


    public Vastaus(Integer id, String vastausteksti, boolean oikein) {
        this.id = id;
        this.vastausteksti = vastausteksti;
        this.oikein = oikein;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVastausteksti() {
        return vastausteksti;
    }

    public void setVastausteksti (String vastausteksti) {
        this.vastausteksti = vastausteksti;

    }

     public Boolean getOikein() {
        return oikein;
    }

    public void setOikein(boolean oikein) {
        this.oikein = oikein;

    }
    
    
    
}
