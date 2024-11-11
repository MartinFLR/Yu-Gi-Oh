package model;

import java.util.ArrayList;
import java.util.List;

public class Participante {
    private int id;
    private final String nombre;//fijarse que no pueda tener el mismo nombre que otro participante
    private final List<PreguntaOpcion> preguntas = new ArrayList<>();
    private String respuestaParticipante;
    private PreguntaAproximacion pregEmpate;
    private double respuestaParticipanteEmpate;
    private int cantErrores=0;
    private int cantAciertos = 0;
    private int numEscalon=1;
    private String imagen;
    
    //Constructores
    public Participante(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
    }
    // Constructor sin id para agregar participantes
    public Participante(String nombre) {
        this.nombre = nombre;
    }
    
    //Metodos
    public void sumaAcierto(){
        this.cantAciertos++;
    }
    public void sumaError(){
        this.cantErrores++;
    }
    public void subeEscalon(){
        this.numEscalon++;
    }
    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public String getRespuestaParticipante() {
        return respuestaParticipante;
    }
    public void setRespuestaParticipante(String respuestaParticipante) {
        this.respuestaParticipante = respuestaParticipante;
    }
    public List<PreguntaOpcion> getPreguntasParticipante(){
        return this.preguntas;
    } 
    public void setPreguntasParticipante(PreguntaOpcion preg){
        this.preguntas.add(preg);
    }
    public int getCantErrores(){
        return this.cantErrores;
    }
    public int getCantAciertos(){
        return this.cantAciertos;
    }
    public double getRespuestaParticipanteEmpate() {
        return respuestaParticipanteEmpate;
    }
    public void setRespuestaParticipanteEmpate(double respuestaParticipanteEmpate) {
        this.respuestaParticipanteEmpate = respuestaParticipanteEmpate;
    }
    public PreguntaAproximacion getPregEmpate() {
        return pregEmpate;
    }
    public void setPregEmpate(PreguntaAproximacion pregEmpate) {
        this.pregEmpate = pregEmpate;
    }
    public int getNumEscalon() {
        return numEscalon;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCantErrores(int i) {
        this.cantErrores = i;
    }

    public void setCantAciertos(int cantAciertos) {
        this.cantAciertos = cantAciertos;
    }
}

