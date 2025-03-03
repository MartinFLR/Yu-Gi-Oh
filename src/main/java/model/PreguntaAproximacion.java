package model;

public class PreguntaAproximacion extends Preguntas{
    
    private String respuesta_correcta;

    //Constructores
    public PreguntaAproximacion(String pregunta, int id_tema){
        //para traer de la bd
        super(pregunta,"Aproximacion", id_tema);
    }
    public PreguntaAproximacion(int id,String pregunta,String respuesta_correcta,int id_tema){
        super(id,pregunta,"Aproximacion",id_tema);
        this.respuesta_correcta = respuesta_correcta;
    }
    
    public PreguntaAproximacion(int id, String pregunta, int id_tema) {
        super(id, pregunta, "Aproximacion", id_tema);
    }
    
    //Metodos
    @Override
    public void imprimirPregunta(){
        System.out.println("\tId: "+this.getId_pregunta());
        System.out.println("\tPregunta: "+this.getPregunta());
        System.out.println("\tTipo Pregunta: "+this.getTipo_preg());
        System.out.println("\tRespuesta correcta: "+this.getRespuestaCorrecta());
    }

    //Getters y Setters
    @Override
    public String getRespuestaCorrecta(){
        return this.respuesta_correcta;
    }


}
