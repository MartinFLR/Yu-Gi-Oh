import java.sql.SQLException;
import java.util.List;

import model.*;
import model.ABM.Busqueda;
import model.ABM.ParticipantesDAO;
import model.ABM.PreguntaAproximacionDAO;
import model.ABM.PreguntaOpcionDAO;
import model.ABM.TemasDAO;


public class Main {
  public static void main(String[] args) throws SQLException {
    // PreguntaOpcionDAO abmPreg = PreguntaOpcionDAO.getInstance();
    // PreguntaAproximacionDAO abmPregAprox = PreguntaAproximacionDAO.getInstance();
     TemasDAO abmTemas = TemasDAO.getInstance();
    // ParticipantesDAO abmPart = ParticipantesDAO.getInstance();

    // List<Participante> listaParticipantes = abmPart.buscarTodos();
    // List<PreguntaOpcion> listaPreguntas = abmPreg.buscarTodos();
     List<Tema> listaTemas = abmTemas.buscarObjeto(1);
    // List<PreguntaAproximacion> listaPreguntasAproximacion = abmPregAprox.buscarTodos();

    // System.out.println("Lista de Participantes:");
    // for (Participante participante : listaParticipantes) {
    //   System.out.println(participante.getNombre()+", ");
    // }

    // System.out.println("Lista de Preguntas:");
    // for (PreguntaOpcion pregunta : listaPreguntas) {

    //   System.out.println("Id pregunta "+pregunta.getId() +", "+pregunta.getPregunta()+", Respuesta Correcta: "+pregunta.getRespuestaCorrecta()+", Tema ID: "+ pregunta.getIdTema());
    // }
  


<<<<<<< .merge_file_KrFaje
      System.out.println("Id pregunta "+pregunta.getId() +", "+pregunta.getPregunta()+", Respuesta Correcta: "+pregunta.getRespuestaCorrecta()+", Tema ID: "+ pregunta.getIdTema());
    }
  
    System.out.println("Lista de Preguntas de aproximacion:");
    for (PreguntaAproximacion pregunta : listaPreguntasAproximacion) {

      System.out.println("Id pregunta "+pregunta.getId() +", "+pregunta.getPregunta()+", Respuesta Correcta: "+pregunta.getRespuestaCorrecta()+", Tema ID: "+ pregunta.getIdTema());
    }
=======
    // System.out.println("Lista de Temas:");
    // for (Tema tema : listaTemas) {
    //   System.out.println(tema.getNombre()+", Id: "+ tema.getId());
    // }
>>>>>>> .merge_file_LqJ1ql

    Busqueda busq = new Busqueda();

    for (Tema tema : listaTemas) {
      System.out.println(tema.getNombre() + "ID: " + tema.getId());
    }

<<<<<<< .merge_file_KrFaje

    //ASI SE ELIMINAN LAS PREGUNTAS
    abmPreg.eliminar(4);
  }
}
=======
  }}
>>>>>>> .merge_file_LqJ1ql


