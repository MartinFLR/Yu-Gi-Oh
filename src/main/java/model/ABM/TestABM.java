package model.ABM;

//Sincronizacion general

import model.*;

import java.util.ArrayList;
import java.util.List;

public class TestABM {

    public static void main(String[] args) {
        //Metodo para limpiar bien la bd
        //TRUNCATE TABLE participantes RESTART IDENTITY;
        ParticipantesDAO participantesDAO = new ParticipantesDAO();
        PreguntaAproximacionDAO preguntaAproximacionDAO = new PreguntaAproximacionDAO();
        PreguntaOpcionDAO preguntaOpcionDAO = new PreguntaOpcionDAO();
        TemasDAO temasDAO = new TemasDAO();
        AdminDAO adminDAO = new AdminDAO();
        PreguntasDAO preguntasDAO = new PreguntasDAO();

        PreguntaAproximacion preguntaAprox = new PreguntaAproximacion("quien es alditasdasdo",2);
        ArrayList<Respuesta> listaRespuestas = new ArrayList<>();
        Respuesta respuesta = new Respuesta("el mismisimo aldoasdasdasdasd",false);
        Respuesta respuesta1 = new Respuesta("un pasdaanchasdasdo",true);
        Respuesta respuesta2 = new Respuesta("usasdasn casdaapo",false);
        Respuesta respuesta3 = new Respuesta("alasdasdasdasddo",false);
        listaRespuestas.add(respuesta);
        listaRespuestas.add(respuesta1);
        listaRespuestas.add(respuesta2);
        listaRespuestas.add(respuesta3);

        preguntaAproximacionDAO.crearPregunta(preguntaAprox,listaRespuestas);


        List<Preguntas> listaTodo = preguntasDAO.buscarTodos();
        for (Preguntas preguntas : listaTodo) {
            preguntas.imprimirPregunta();
        }

        //Prueba Participante
//        Participante participante = new Participanste("Aldo");
//        Participante participanteModificado = new Participante("Hola fui modificado");
//        //participantesDAO.insertar(participante);
//        List<Participante> listaParticipantes = participantesDAO.buscarTodos();

//        for (Participante participante1 : listaParticipantes) {
//            System.out.println(participante1.getId() +" "+ participante1.getNombre());
//        }

//        System.out.println("");
//        participantesDAO.modificar(2, participanteModificado);
//        List<Participante> listaParticipantes2 = participantesDAO.buscarTodos();
//        for (Participante participante2 : listaParticipantes2) {
//            System.out.println(participante2.getId() +" "+ participante2.getNombre());
//        }

//        participantesDAO.eliminar(5);
//        List<Participante> listaParticipantes3 = participantesDAO.buscarTodos();
//        for (Participante participante3 : listaParticipantes3) {
//            System.out.println(participante3.getId() +" "+ participante3.getNombre());
//        }

        //Prueba tema
        Tema tema = new Tema("Geografia");
//        temasDAO.insertar(tema);
//        List<Tema> listaTemas = temasDAO.buscarTodos();
//        for (Tema tema1 : listaTemas) {
//            System.out.println(tema1.getId() +" "+ tema1.getNombre());
//        }

//        temasDAO.eliminar(11);
//        List<Tema> listaTemas2 = temasDAO.buscarTodos();
//        for (Tema tema1 : listaTemas2) {
//            System.out.println(tema1.getId() +" "+ tema1.getNombre());
//        }
//
//        temasDAO.modificar(1,tema);
//        List<Tema> listaTemas3 = temasDAO.buscarTodos();
//        for (Tema tema1 : listaTemas3) {
//            System.out.println(tema1.getId() +" "+ tema1.getNombre());
//        }


    }


}
