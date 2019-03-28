package com.s.d.a.a.conceitodashboard;
import java.io.Serializable;

public class Carro implements Serializable{
    private static final long serialVersionUID = 6601006766832473959L;
    public static final String KEY = "carro";
    public static final String TIPO = "tipo";
    public static final String TIPO_CLASSICO = "classicos";
    public static final String TIPO_ESPORTIVOS = "esportivos";
    public static final String TIPO_LUXO = "luxo";
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
}
