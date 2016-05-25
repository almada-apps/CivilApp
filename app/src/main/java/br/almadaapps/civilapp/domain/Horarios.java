package br.almadaapps.civilapp.domain;

import br.almadaapps.civilapp.MainActivity;

/**
 * Created by viniciusalmada on 20/04/2016.
 */
public class Horarios extends MainActivity{
    String intervalo;
    String disciplina;

    public Horarios(String intervalo, String disciplina){
        this.disciplina = disciplina;
        this.intervalo = intervalo;
    }

    public String getDisciplina () {
        return disciplina;
    }

    public String getIntervalo () {
        return MainActivity.hor_int[Integer.parseInt(intervalo)];
    }

    public void setDisciplina (String disciplina) {
        this.disciplina = disciplina;
    }

    public void setIntervalo (String intervalo) {
        this.intervalo = intervalo;
    }
}
