package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import model.Participante;
import model.PreguntaAproximacion;
import model.PreguntaOpcion;
import model.logica.Escalon;
import model.logica.Ronda;
import view.VistaJuego;
import view.componentes.PanelJugadorNormal;


public class ControladorJuego implements ActionListener, KeyListener {
	private final VistaJuego vista;
	private final Escalon escalon;
    private int indiceEmpate = 0;
    private int turnoJugador = 0;
    private boolean esperandoRespuesta = false;
	
	public ControladorJuego(Escalon escalon) {
		this.escalon = escalon;
		this.vista = new VistaJuego(this);
		this.vista.setVisible(true);
        this.vista.getPanelAproximacion().setVisible(true);
        this.vista.getPanelFinal().setVisible(false);
		this.vista.setEscalonUso(this.escalon.getEscalon());
		//Por default muestra el de el primer participante
		poneNombres();
        inicializarActionListeners();
        this.rondaDePreguntas(this.escalon.getEstadoDeRonda(), this.escalon.getParticipantes());
        //Mostrar en la vista
			// La cant errores
            // Cant aciertos
            // Filtrar participantes
		    // Subir escalon

	}

    private void inicializarActionListeners(){

        this.vista.getBtnpreRespuesta1().addActionListener(e -> {
            if (esperandoRespuesta) {
            procesarRespuesta(e.getActionCommand());
            }
        });
        this.vista.getBtnpreRespuesta2().addActionListener(e -> {
            if (esperandoRespuesta) {
            procesarRespuesta(e.getActionCommand());
            }
        });
        this.vista.getBtnpreRespuesta3().addActionListener(e -> {
            if (esperandoRespuesta) {
            procesarRespuesta(e.getActionCommand());
            }
        });
        this.vista.getBtnpreRespuesta4().addActionListener(e -> {
            if (esperandoRespuesta) {
            procesarRespuesta(e.getActionCommand());
            }
        });
        
        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        vista.getBtnpreRespuesta1().doClick();
                        break;
                    case KeyEvent.VK_B:
                        vista.getBtnpreRespuesta2().doClick();
                        break;
                    case KeyEvent.VK_C:
                        vista.getBtnpreRespuesta3().doClick();
                        break;
                    case KeyEvent.VK_D:
                        vista.getBtnpreRespuesta4().doClick();
                        break;
                }
            }
        };

        this.vista.getBtnpreRespuesta1().addKeyListener(keyListener);
        this.vista.getBtnpreRespuesta2().addKeyListener(keyListener);
        this.vista.getBtnpreRespuesta3().addKeyListener(keyListener);
        this.vista.getBtnpreRespuesta4().addKeyListener(keyListener);
    
    }
//setea los colores del fondo para indicar de quien es el turno
    public void setColore(){
    Participante participante = escalon.getParticipantes().get(turnoJugador);
        if (turnoJugador!=0 ){
            participante = escalon.getParticipantes().get(turnoJugador-1);
            int nroParticipante = escalon.getParticipantes().indexOf(participante);
            PanelJugadorNormal panelParticipante = this.vista.getJugadorNormal().get(nroParticipante) ;
            panelParticipante.setActivo(); }
            else{
                participante = escalon.getParticipantes().getLast();
                int nroParticipante = escalon.getParticipantes().indexOf(participante);
                PanelJugadorNormal panelParticipante = this.vista.getJugadorNormal().get(nroParticipante) ;
                panelParticipante.setActivo(); 
            }
            
            }
	//Rondas
	public void rondaDePreguntas(Ronda ronda,List<Participante> participantes){
        mostrarPreguntaActual();
        esperandoRespuesta = true;
	}
	public void rondaEmpate(Ronda ronda, List<Participante> participantes){
		PreguntaAproximacion preg = participantes.get(0).getPregEmpate();
        Double respuestaCorrecta = Double.valueOf(preg.getRespuestaCorrecta());
        double respMasLejana = 0;
        double diferencia;
        Participante peorParticipante = null;
        List<Participante> empatados = new ArrayList<>();
        this.vista.getlblaproxPregunta().setText(preg.getPregunta());

        //recorre la lista de participantes y compara las respuestas de los participantes con la respuesta correcta
        for (model.Participante participante: participantes){
            System.out.println("Respuesta del participante: " + participante.getNombre());	
            Double respuestaParticipante = participante.getRespuestaParticipanteEmpate();
            
            //Calcula la diferencia entre la respuesta correcta y la respuesta del participante
            diferencia = Math.abs(respuestaCorrecta-respuestaParticipante);
            //Si la diferencia es mayor a la respuesta mas lejana, se guarda la diferencia y el participante
            if (diferencia>respMasLejana){
                respMasLejana = diferencia;
                peorParticipante = participante;
                empatados.clear();
                empatados.add(participante);
            }else if (diferencia==respMasLejana){
                empatados.add(participante);
            }
        }

        participantes.clear(); // vacia la lista.

        if(empatados.size()>1){
            participantes.addAll(empatados);
            System.out.println("Empate entre");
            for (Participante participante : empatados) {
                System.out.println(participante.getNombre());
            }
        } else if (peorParticipante != null) {
            peorParticipante.sumaError();
            participantes.add(peorParticipante);
            System.out.println("Participante a eliminar: "+peorParticipante.getNombre());
        }
	}
	public void rondaFinal(Ronda ronda,List<Participante> participantes){
		//La base de datos deberá tener un tema llamado Final que junte todas las preguntas, para hacer preguntas de todos los temas.
        PreguntaOpcion preg;
        String resp;
        Integer cantPreguntasRestantes = 10;        
        for (int i = 0; i < 10; i++){ 
            System.out.println("Pregunta "+(i+1));
            //Esta linea se puede eliminar despues, es para mostrar la preg por consola
            participantes.getFirst().getPreguntasParticipante().get(i).imprimirPregunta();
            for (Participante participante:participantes) {
                preg=participante.getPreguntasParticipante().get(i);
                // Cuando integremos con igu sacamos el scanner
                resp=participante.getRespuestaParticipante();
                System.out.println("Participante: "+participante.getNombre() + " Ingrese la respuesta correcta: (a, b, c, d)");
                if (preg.getRespuestaCorrecta().equals(resp)){
                    participante.sumaAcierto();
                }else {
                    participante.sumaError();
                    }
                }
                cantPreguntasRestantes--;

                //Verifica si uno de los dos participantes ya no tiene posiblidad de remontar y termina la ronda final.
                if (participantes.getFirst().getCantAciertos() > participantes.getLast().getCantAciertos() + cantPreguntasRestantes
                || participantes.getLast().getCantAciertos() > participantes.getFirst().getCantAciertos() + cantPreguntasRestantes ) {
                    if(participantes.getFirst().getCantAciertos() > participantes.getLast().getCantAciertos()){
                        System.out.println("Ganador: "+participantes.getFirst().getNombre());
                    }else{
                        System.out.println("Ganador: "+participantes.getLast().getNombre());
                    }
                    break;
            }
            // Verifica si ambos participantes tienen la misma cantAciertos y no hay pregs restantes
            System.out.println("Cant aciertos participante 1: "+participantes.getFirst().getCantAciertos());
            System.out.println("Cant aciertos participante 2: "+participantes.getLast().getCantAciertos());
            if (cantPreguntasRestantes == 0 
            && participantes.getFirst().getCantAciertos() == participantes.getLast().getCantAciertos()) {
                System.out.println("Pasa a ronda de empate\n");
                //Aca esta el problema donde no se manda la pregunta de desempate
                // En caso de la rondaNormal se manda desde Escalon
                // dentro de metodo filtrarParticipantes
                ronda.setRondaDeEmpate(participantes);
                return;
            }
        }
	}
	
	//Metodos para filtrar y eliminar participantes
	private List<Participante> getParticipantesAEliminar() {
        //Tiene que checkear que haya solo 1, si hay mas de 1 setea el estado en RondaEmpate
        List<Participante> participantesAEliminar = new ArrayList<>();
        for (Participante participante : escalon.getParticipantes()) {
            int errParticipante = participante.getCantErrores();
            if (errParticipante>0){
                //Si la lista no esta vacia, compara con el maximo de errores
                if(!participantesAEliminar.isEmpty()){
                    //Si el participante tiene mas errores que el maximo de errores,
                    //se limpia la lista y se agrega el participante
                    if(errParticipante>participantesAEliminar.get(0).getCantErrores()){
                        participantesAEliminar.clear();
                        participantesAEliminar.add(participante);
                    }else if(errParticipante==participantesAEliminar.get(0).getCantErrores()){
                        //Si el participante tiene la misma cantidad de errores que el maximo de errores,
                        //se agrega el participante
                        participantesAEliminar.add(participante);
                    }
                }else{
                    //Si la lista esta vacia, se agrega el participante
                    participantesAEliminar.add(participante);                
                }
            }
        }
        return participantesAEliminar;
    }
    public void filtrarParticipantes(){
        List<Participante> participantesAEliminar = getParticipantesAEliminar();
        //Si hay mas de un participante con la misma cantidad de errores, setea la ronda de empate
        if (participantesAEliminar.size()>1){
            // les envia la pregunta de aproximacion a todos los participantes empatados.
			this.vista.getPanelAproximacion().setVisible(true);
			Ronda ronda = this.escalon.getEstadoDeRonda();

            //Envia la lista de participantes a eliminar y sigue la la logica de la ronda de empate
            ronda.setRondaDeEmpate(participantesAEliminar);
            ronda.actualizarDatos(ronda, participantesAEliminar, this.escalon.getTema());
            rondaEmpateTurnos(participantesAEliminar);

             // Repite la ronda de desempate hasta que quede uno
            //while(participantesAEliminar.size()>1){
		    //this.rondaEmpate(ronda, participantesAEliminar);
            //rondaEmpateTurnos(participantesAEliminar);
            // this.vista.getPanelAproximacion().setVisible(true);
            // ronda.actualizarDatos(ronda, participantesAEliminar, this.escalon.getTema());
            //}
            Thread hilo = new Thread(() -> {
                while (getParticipantesAEliminar().size() > 1) {
                    esperandoRespuesta = true;
                }
            });
            hilo.start();
            try {
                hilo.join(); // Espera a que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.escalon.getParticipantes().remove(participantesAEliminar.getFirst());
            ronda.setRondaNormal();
        }else{
            //Si solo hay uno, se elimina
            System.out.println("Se elimina el participante "+ participantesAEliminar.getFirst().getNombre());  
            Participante participante = participantesAEliminar.getFirst();
            //Para ver la posicion en el panel recupero el indice que ocupa en la lista
            int indice = this.escalon.getParticipantes().indexOf(participante);
            this.setColore();
            this.vista.getJugadorNormal().get(indice).setEliminado();
            this.vista.getJugadorNormal().remove(indice);
            this.escalon.eliminaParticipante(participante);
            Ronda estado = this.escalon.getEstadoDeRonda();
            estado.setRondaNormal();
            escalon.setTema();
            estado.actualizarDatos(estado, escalon.getParticipantes(), escalon.getTema());
            this.rondaDePreguntas(estado, escalon.getParticipantes());
        }
    }

    //Procesar preguntas y respuestas
    private void mostrarPreguntaActual(){
        Participante participante = escalon.getParticipantes().get(turnoJugador);
        mostrarPregunta(participante);
        esperandoRespuesta = true;  // Estamos esperando la respuesta
    }
    private void mostrarPregunta(Participante participante){
        //Podemos usar .remove() para sacar la preg y que no se repita
        PreguntaOpcion pregunta = participante.getPreguntasParticipante().getFirst();
        int posParticipante = escalon.getParticipantes().indexOf(participante);
        PanelJugadorNormal panelParticipante = this.vista.getJugadorNormal().get(posParticipante);
        System.out.println("Respuesta correcta: "+pregunta.getRespuestaCorrecta());
        panelParticipante.setRespondiendo();
        this.vista.getLblprePregunta().setText("<html><div style='width: 300px;'>" + pregunta.getPregunta() + "</div></html>");
        this.vista.getBtnpreRespuesta1().setText("A) "+pregunta.getOpcionA());
        this.vista.getBtnpreRespuesta2().setText("B) "+pregunta.getOpcionB());
        this.vista.getBtnpreRespuesta3().setText("C) "+pregunta.getOpcionC());
        this.vista.getBtnpreRespuesta4().setText("D) "+pregunta.getOpcionD());
        
    }
    private void procesarRespuesta(String respuesta){
        //La respuesta viene con un formato "A) " o "B) "
        //Elimina los primeros 3 caracteres antes de hacer las comparaciones
        respuesta = respuesta.substring(3);

        Participante participante = escalon.getParticipantes().get(turnoJugador);
        int posParticipante = escalon.getParticipantes().indexOf(participante);
        participante.setRespuestaParticipante(respuesta);
        if (!participante.getPreguntasParticipante().isEmpty()) {
            PreguntaOpcion preguntaActual = participante.getPreguntasParticipante().getFirst(); 
            if (respuesta.equals(preguntaActual.getRespuestaCorrecta())) {
                this.vista.getJugadorNormal().get(posParticipante).setAcierto(participante);
                participante.sumaAcierto();
            } else {
                this.vista.getJugadorNormal().get(posParticipante).setError(participante);
                participante.sumaError();
            }
            participante.getPreguntasParticipante().remove(0);
        }
        turnoJugador++;

        //Si es el turno del ultimo y no tiene mas preguntas, termina la ronda y sube escalon
        //Deberia repartir preguntas para el proximo escalon y filtrar participantes
        if (turnoJugador == escalon.getParticipantes().size() && participante.getPreguntasParticipante().isEmpty()) {
            //subeEscalon() aumenta el escalon, resetea los errores y aciertos y reparte preguntas
            int nroRonda = escalon.getEscalon()+1;
            System.out.println("Ronda " + nroRonda +" terminada");
            turnoJugador = 0;
            this.filtrarParticipantes();
            this.escalon.subeEscalon();
            this.vista.setEscalonUso(this.escalon.getEscalon());
            esperandoRespuesta = false;
            for (PanelJugadorNormal panelJugadorNormal : this.vista.getJugadorNormal()) {
                if(panelJugadorNormal.isActivo()){
                panelJugadorNormal.setResetErrores();
                }
            }
            mostrarPreguntaActual();
            return;
        }

        if (turnoJugador >= escalon.getParticipantes().size()) {
            turnoJugador = 0;  
        }
        setColore();
        esperandoRespuesta = false;
        mostrarPreguntaActual();
    }

	private void poneNombres(){
		for (int i = 0; i < 9; i++) {
			this.vista.getJugadorNormal().get(i).setNombre(escalon.getParticipantes().get(i).getNombre());
			this.vista.getJugadorNormal().get(i).setImagen(escalon.getParticipantes().get(i).getImg());
		}
	}

      private void rondaEmpateTurnos(List<Participante> participantesEmpate){
          System.out.println("Ejecutnando ronda empate turnos");
          this.vista.getPanelAproximacion().setVisible(true);

          Participante participante = participantesEmpate.get(indiceEmpate);
          PreguntaAproximacion preguntaEmpate = participante.getPregEmpate();

          this.vista.getlblaproxPregunta().setText(preguntaEmpate.getPregunta());

          this.vista.getBtnaproxEnviar().addActionListener(e -> {
              try {
                  participante.setRespuestaParticipanteEmpate(Double.valueOf((this.vista.getTxtaproxRespuesta().getSelectedText())));
                  this.vista.getDefTable().addRow(new Object[]{participante.getNombre(),participante.getRespuestaParticipanteEmpate()});
                  indiceEmpate++;
                  if (indiceEmpate >= participantesEmpate.size()) {
                      rondaEmpate(this.escalon.getEstadoDeRonda(), participantesEmpate);
                      indiceEmpate = 0;
                  } else {
                      actualizarVistaEmpate();
                      rondaEmpateTurnos(participantesEmpate);
                  }
  
              } catch (NumberFormatException ex) {
                  System.out.println("Excepcion");
              }
          });
      }

      private void actualizarVistaEmpate(){
        this.vista.getTxtaproxRespuesta().setText("");
      }



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
			new ControladorMenupausa();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
