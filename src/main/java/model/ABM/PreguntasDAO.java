package model.ABM;

import java.util.ArrayList;
import java.util.List;

import model.PreguntaAproximacion;
import model.PreguntaOpcion;
import model.Preguntas;

public class PreguntasDAO implements DAO<Preguntas>{

    private ArrayList<Preguntas> preguntas = new ArrayList<>();

    public void agregaPreguntas(Preguntas p){
        this.preguntas.add(p);
    }

    public void recorrePreguntas(){
        for (Preguntas p : preguntas) {
            p.imprimirPregunta();
        }
    }

    @Override
    public List<Preguntas> buscarTodos() {

            PreguntaOpcionDAO preguntaOpcionDAO = new PreguntaOpcionDAO();
            PreguntaAproximacionDAO preguntaAproximacionDAO = new PreguntaAproximacionDAO();

            List<PreguntaAproximacion> listaPreguntaAproximacion = preguntaAproximacionDAO.buscarTodos();
            List<PreguntaOpcion> listaPreguntaOp = preguntaOpcionDAO.buscarTodos();
            List<Preguntas> listaPreguntas = new ArrayList<>();

            listaPreguntas.addAll(listaPreguntaOp);
            listaPreguntas.addAll(listaPreguntaAproximacion);

            return listaPreguntas;
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertar(Preguntas entidad) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void modificar(int id, Preguntas entidad) {
        // TODO Auto-generated method stub
        
    }

    

}
