package com.s.d.a.a.conceitodashboard;

import java.io.Serializable;
import java.util.List;

public class ListaDeCarros implements  Serializable{
    private static final long serialVersionUID = -2251881666082662021L;
    public static final String KEY = "carros";
    public List<Carro> carros;

    public ListaDeCarros(List<Carro> carros) {
        this.carros = carros;
    }
}
