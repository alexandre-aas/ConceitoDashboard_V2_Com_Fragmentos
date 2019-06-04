package com.s.d.a.a.conceitodashboard_v2_com_fragmentos;

import java.io.Serializable;
import java.util.List;

public class ListaDeCarros implements  Serializable{
    private static final long serialVersionUID = -2251881666082662021L;
    public static final String KEY = "carros";
    public List<com.s.d.a.a.conceitodashboard_v2_com_fragmentos.Carro> carros;

    public ListaDeCarros(List<com.s.d.a.a.conceitodashboard_v2_com_fragmentos.Carro> carros) {
        this.carros = carros;
    }

    /** public ListaDeCarros(List<Carro> carros) {
        this.carros = carros;
    } */
}
