package br.almadaapps.civilapp.domain;

/**
 * Created by viniciusalmada on 24/04/2016.
 */
public class HorarioPessoalAux {

    String dia;
    String[] hor;
    String[] disciplinas;
    String horAux;
    String disciplinaAux;

    public HorarioPessoalAux (String dia, String[] hor, String[] disciplinas){
        this.dia = dia;
        this.hor = hor;
        this.disciplinas = disciplinas;
    }

    public HorarioPessoalAux (String dia, String horAux, String disciplinaAux){
        this.dia = dia;
        this.horAux = horAux;
        this.disciplinaAux = disciplinaAux;
    }

    public String getDia () {
        return dia;
    }

    public String[] getDisciplinas () {
        return disciplinas;
    }

    public String[] getHor () {
        return hor;
    }

    public String getDisciplinaAux () {
        return disciplinaAux;
    }

    public String getHorAux () {
        return horAux;
    }

    public void setDia (String dia) {
        this.dia = dia;
    }

    public void setDisciplinas (String[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setHor (String[] hor) {
        this.hor = hor;
    }

    public void setDisciplinaAux (String disciplinaAux) {
        this.disciplinaAux = disciplinaAux;
    }

    public void setHorAux (String horAux) {
        this.horAux = horAux;
    }
}
