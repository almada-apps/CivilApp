package br.almadaapps.civilapp.domain;

/**
 * Created by viniciusalmada on 22/05/2016.
 */

public class Monografias {
    private String authorOrTitle;

    public Monografias(String authorOrTitle){
        this.authorOrTitle = authorOrTitle;
    }

    public void setAuthorOrTitle (String authorOrTitle) {
        this.authorOrTitle = authorOrTitle;
    }

    public String getAuthorOrTitle () {
        return authorOrTitle;
    }
}
