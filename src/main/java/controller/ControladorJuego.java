package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Participante;
import model.PreguntaAproximacion;
import model.PreguntaOpcion;
import model.ABM.ParticipantesDAO;
import model.logica.Escalon;
import model.logica.Ronda;
import view.VistaJuego;
import view.componentes.PanelJugadorFinal;
import view.componentes.PanelJugadorNormal;


public class ControladorJuego implements ActionListener, KeyListener {
	private final VistaJuego vista;
	private final Escalon escalon;
    private ParticipantesDAO participantesDAO;
    private int indiceEmpate = 0;
    private int turnoJugador = 0;
    private boolean esperandoRespuesta = false;
    private int  indiceActualPar=0;
    private boolean huboEmpate=false;
    private boolean nuevaRondaFinal=true;
    private List<String> respuestasJugador;
    private final HashMap<Integer, Color> colorEscalon = new HashMap<>();

	public ControladorJuego(Escalon escalon) {
		this.escalon = escalon;
		this.vista = new VistaJuego(this);
		this.vista.setVisible(true);
		this.vista.setEscalonUso(this.escalon.getEscalon());
		//Por default muestra el de el primer participante
		poneNombres();
        poneNombresEscalones();
        poneColoresAEscalones();
        
        inicializarActionListeners();
        this.rondaDePreguntas(this.escalon.getParticipantes());
        //Mostrar en la vista
			// La cant errores
            // Cant aciertos
            // Filtrar participantes
		    // Subir escalon
	}

    private void poneColoresAEscalones(){
        //Agrega colores a los escalones
		colorEscalon.put(0, new Color(138, 43, 226));
		colorEscalon.put(1, new Color(0, 150, 75));
		colorEscalon.put(2, new Color(30, 144,255));
		colorEscalon.put(3, new Color(0, 35, 255));
		colorEscalon.put(4, new Color(255, 105, 180));
		colorEscalon.put(5, new Color(255, 140, 0));
		colorEscalon.put(6, new Color(255, 69, 0));
		colorEscalon.put(7, new Color(255, 215, 0));
		for (int i = 0; i < 8; i++) {
            if (this.escalon.getEscalon() == i) {
                this.vista.getEscalones().get(i).setBackground(colorEscalon.get(i));
                this.vista.getEscalones().get(i).setcolorUso();
            }else{
                this.vista.getEscalones().get(i).setBackground(colorEscalon.get(i));
                this.vista.getEscalones().get(i).setcolorNoUso();
            }
		}
    }
	//Metodos para la ronda normal
	private void rondaDePreguntas(List<Participante> participantes){
        
        poneColoresAEscalones();
        mostrarPreguntaActual();
        esperandoRespuesta = true;
	}
    private void mostrarPreguntaActual() {
        Participante participante = escalon.getParticipantes().get(turnoJugador);

        esperandoRespuesta = true;
        
        if (this.escalon.getEscalon() != 7) {
            PreguntaOpcion pregunta = participante.getPreguntasParticipante().getFirst();
            int posParticipante = escalon.getParticipantes().indexOf(participante);
            PanelJugadorNormal panelParticipante = this.vista.getJugadorNormal().get(posParticipante);
            System.out.println("Tema: "+escalon.getTema().getNombre());
            System.out.println("Pregunta ID: "+pregunta.getId_pregunta());
            System.out.println("Pregunta: "+ pregunta.getPregunta());
            System.out.println("Respuesta correcta: " + pregunta.getRespuestaCorrecta());
            panelParticipante.setRespondiendo();
            
            this.vista.getLblprePregunta().setText("<html><div style='width: 450px; text-align: center;margin-left: 85px;'>" + pregunta.getPregunta() + "</div></html>");
            this.vista.getBtnpreRespuesta1().setText(pregunta.getOpcionA());
            this.vista.getBtnpreRespuesta2().setText(pregunta.getOpcionB());
            this.vista.getBtnpreRespuesta3().setText(pregunta.getOpcionC());
            this.vista.getBtnpreRespuesta4().setText(pregunta.getOpcionD());
        } else {
            mostrarPreguntaFinal(participante);
        }
    }
    private void procesarRespuesta(String respuesta) {
        Participante participante = escalon.getParticipantes().get(turnoJugador);
        int posParticipante = escalon.getParticipantes().indexOf(participante);
        participante.setRespuestaParticipante(respuesta);

        procesarPregunta(participante, posParticipante, respuesta);

        turnoJugador++;
        if (turnoJugador < escalon.getParticipantes().size()) {
                indiceActualPar = turnoJugador;
            } else {
                indiceActualPar = 0;
            }

        // Actualiza la flag de huboEmpate para que no continue el flujo del juego
        if(turnoJugador == escalon.getParticipantes().size()
        && getParticipantesAEliminar().size()>1
        && participante.getPreguntasParticipante().isEmpty()){
            huboEmpate = true;
            turnoJugador = 0;
            indiceEmpate = escalon.getParticipantes().indexOf(getParticipantesAEliminar().getFirst());
            escalon.getEstadoDeRonda().setRondaDeEmpate(getParticipantesAEliminar());
            escalon.getEstadoDeRonda().actualizarDatos(escalon.getEstadoDeRonda(), getParticipantesAEliminar(), escalon.getTema());
            this.vista.getPanelAproximacion().setVisible(true);
            setActivosEmpatados();
            mostrarPreguntaEmpate();
        }

        if (turnoJugador == escalon.getParticipantes().size() 
        && participante.getPreguntasParticipante().isEmpty() 
        && !huboEmpate) {
            manejarFinDeRonda();
        } else if (this.escalon.getEscalon() < 7 && !huboEmpate) {
            manejarRondaNormal();
        }
    }
    
    //Metodos para procesarRespuesta
    private void procesarPregunta(Participante participante, int posParticipante, String respuesta) {
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
    }   
    private void manejarFinDeRonda() {
        turnoJugador = 0;
        this.filtrarParticipantes();

        this.vista.getDefTable().setNumRows(0);
        if (this.escalon.getEscalon() != 7){
        this.escalon.subeEscalon();
        this.vista.setEscalonUso(this.escalon.getEscalon());}
        esperandoRespuesta = false;

        if (this.escalon.getEscalon() != 7) {
            
            this.vista.setEscalonUso(this.escalon.getEscalon());
            for (PanelJugadorNormal panelJugadorNormal : this.vista.getJugadorNormal()) {
                if (panelJugadorNormal.isActivo()) {
                panelJugadorNormal.setResetErrores();
                }
            }
            this.rondaDePreguntas(escalon.getParticipantes());
        } else if (this.escalon.getEscalon() == 7){
            if (nuevaRondaFinal){
                manejarRondaFinal();
            }else{
                huboEmpate=false;
                empateFinal();
            }
    }}
    private void manejarRondaFinal() {
        if (turnoJugador >= escalon.getParticipantes().size()) {
            turnoJugador = 0;
            indiceActualPar = 0;
        }

        Ronda ronda = this.escalon.getEstadoDeRonda();
        ronda.setRondaFinal();
        System.out.println("ronda final");
        nuevaRondaFinal=false;
        for (PanelJugadorNormal jn : this.vista.getJugadorNormal()) {
            jn.setVisible(false);
        }

        this.vista.getPanelAproximacion().setVisible(false);
        this.vista.getPanelJugadores().setVisible(false);
        this.vista.getPanelFinal().setVisible(true);

        for (PanelJugadorFinal pjf : this.vista.getJugadorFinal()) {
            pjf.setVisible(true);
        }
        for (Participante par:  this.escalon.getParticipantes()){
            par.setCantAciertos(0);
            par.setCantErrores(0);
        }
        ronda.actualizarDatos(ronda, this.escalon.getParticipantes(), this.escalon.getTema());
        
        this.rondaFinal(this.escalon.getParticipantes());
    }
    private void manejarRondaNormal() {
        if (turnoJugador == escalon.getParticipantes().size()) {
            turnoJugador = 0;
            indiceActualPar = 0;
        }
        setColores();
        esperandoRespuesta = false;
        mostrarPreguntaActual();
    }
    
    //Metodos para la ronda de empate
    private void rondaEmpate( List<Participante> participantes){
		PreguntaAproximacion preg = participantes.get(0).getPregEmpate();
        Double respuestaCorrecta = Double.valueOf(preg.getRespuestaCorrecta());
        double respMasLejana = 0;
        double diferencia;
        Participante peorParticipante = null;
        List<Participante> empatados = new ArrayList<>();

        //recorre la lista de participantes y compara las respuestas de los participantes con la respuesta correcta
        for (model.Participante participante: participantes){	
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
        
        participantes.clear();

        if(empatados.size()>1){
            participantes.addAll(empatados);
            System.out.println("Empate entre");
            for (Participante participante : empatados) {
                System.out.println(participante.getNombre());
                participante.sumaError();
            }
            huboEmpate = true;
            Ronda ronda = this.escalon.getEstadoDeRonda();
            ronda.actualizarDatos(ronda, participantes, this.escalon.getTema());
        } else if (peorParticipante != null) {
            peorParticipante.sumaError();
            participantes.add(peorParticipante);
            System.out.println("Participante a eliminar: "+peorParticipante.getNombre());
        }
	}
    private void mostrarPreguntaEmpate(){
        Participante participante = getParticipantesAEliminar().get(turnoJugador);
        PreguntaAproximacion pregunta = participante.getPregEmpate();
        this.vista.getPanelPregunta().setVisible(false);
        this.vista.getlblaproxPregunta().setText("<html><div style='width: 300px;margin-top:47px;text-align:center'>" + pregunta.getPregunta() + "</div></html>");
        this.vista.getTxtaproxRespuesta().requestFocusInWindow();
        setColores();
        int posParticipante = escalon.getParticipantes().indexOf(participante);
        if (this.escalon.getEscalon()<7){
            this.vista.getJugadorNormal().get(posParticipante).setRespondiendo();
        }else{
            this.vista.getJugadorFinal().get(posParticipante).setRespondiendo();//cheqeuar si no anda borrar
        }
        
        esperandoRespuesta = true;
    }
    private void procesarRespuestaEmpate(Double respuesta){
        Participante participante = getParticipantesAEliminar().get(turnoJugador);
        indiceEmpate = escalon.getParticipantes().indexOf(participante);
        participante.setRespuestaParticipanteEmpate(respuesta);
        this.vista.getDefTable().addRow(new Object[]{participante.getNombre(),participante.getRespuestaParticipanteEmpate()});
        this.vista.getTxtaproxRespuesta().setText("");
        siguienteTurnoEmpate();
        
    }
    private void siguienteTurnoEmpate(){
        if (this.escalon.getEscalon()==7){
            this.vista.getJugadorFinal().get(indiceEmpate).setActivo();//cheqeuar si no anda borrar
        }else{
            this.vista.getJugadorNormal().get(indiceEmpate).setActivo();
        }
        turnoJugador++;
        List<Participante> participantes = getParticipantesAEliminar(); 
        if(turnoJugador == participantes.size()){
            rondaEmpate(participantes);
            if(participantes.size()>1){
                huboEmpate = true;
                turnoJugador=0;
                setActivosEmpatados();
                this.vista.getDefTable().setNumRows(0);
                this.vista.getPanelAproximacion().setVisible(true);
                this.vista.getPanelPregunta().setVisible(false);
                this.rondaEmpate(participantes);
                this.escalon.getEstadoDeRonda().actualizarDatos(this.escalon.getEstadoDeRonda(), participantes, this.escalon.getTema());
                mostrarPreguntaEmpate();
            }else{
                huboEmpate= false;
                manejarFinDeRonda();
            }
        } else {
            esperandoRespuesta = true;
            mostrarPreguntaEmpate();
        }
    }
    private void setActivosEmpatados(){
        for(Participante participante:escalon.getParticipantes()){
            int index = escalon.getParticipantes().indexOf(participante);
            if(!getParticipantesAEliminar().contains(participante)){
                this.vista.getJugadorNormal().get(index).setResetErrores();
            }
        }
    }

    //Metodos para la ronda final
    private void rondaFinal(List<Participante> participantes){
		//La base de datos deberá tener un tema llamado Final que junte todas las preguntas, para hacer preguntas de todos los temas.
        poneColoresAEscalones();
        this.respuestasJugador = new ArrayList<>(escalon.getParticipantes().size());
        // Inicializamos la lista con un tamaño igual al número de participantes.
        for (int i = 0; i < escalon.getParticipantes().size(); i++) {
            respuestasJugador.add(null);  // Inicializa las respuestas como nulas
        }
        mostrarPreguntaActual();
    }
    private void procesarRespuestaFinal(String respuesta, Participante participante) {
        int posParticipante = escalon.getParticipantes().indexOf(participante);
        respuestasJugador.set(posParticipante, respuesta);  // Asignamos la respuesta del jugador
        
            // Avanzar al siguiente turno
        System.out.println("Participantes " + this.escalon.getParticipantes());
        System.out.println("Respuestas actuales: " + respuestasJugador);
        // Verificamos si todos los jugadores han respondido
        if (!respuestasJugador.contains(null)) {  // Si no hay respuestas nulas
            // Procesamos las respuestas de todos los jugadores
            for (int i = 0; i < escalon.getParticipantes().size(); i++) {
                Participante p = escalon.getParticipantes().get(i);
                String respuestaActual = respuestasJugador.get(i);
                PreguntaOpcion preguntaActual = p.getPreguntasParticipante().getFirst();
                
                if (respuestaActual.equals(preguntaActual.getRespuestaCorrecta())) {
                    this.vista.getJugadorFinal().get(i).setAcierto(p);
                    p.sumaAcierto();
                    System.out.println("suma acierto");
                } else {
                    this.vista.getJugadorFinal().get(i).setError(p);
                    p.sumaError();
                    System.out.println("suma error");
                }
    
                // Eliminar la pregunta actual para el participante
                p.getPreguntasParticipante().remove(0);
                // Mostrar la siguiente pregunta si es necesario
            
            }
        // Reiniciar la lista de respuestas para la siguiente ronda
            for (int i = 0; i < this.escalon.getParticipantes().size(); i++) {
                respuestasJugador.set(i, null);  // Limpiamos las respuestas
                System.out.println("Respuestas actuales despues de nullear: " + respuestasJugador);
            }
            if (turnoJugador >= escalon.getParticipantes().size()) {
                turnoJugador = 0;
            }
            if (!this.escalon.getParticipantes().get(turnoJugador).getPreguntasParticipante().isEmpty()) {
                mostrarPreguntaActual();
            } else {
                verificarRondaFinalYGanador();
            }
        }
    turnoJugador++;
    if (turnoJugador >= escalon.getParticipantes().size()) {
        turnoJugador = 0;
    }
    
    }
    private void mostrarPreguntaFinal(Participante participante){
        //Podemos usar .remove() para sacar la preg y que no se repita
        
        PreguntaOpcion pregunta = participante.getPreguntasParticipante().getFirst();
        int posParticipante = escalon.getParticipantes().indexOf(participante);
        indiceActualPar= escalon.getParticipantes().indexOf(participante);
        PanelJugadorFinal panelParticipante = this.vista.getJugadorFinal().get(posParticipante);
        System.out.println("Respuesta correcta: "+pregunta.getRespuestaCorrecta());
        
        esperandoRespuesta=true;
        this.vista.getLblprePregunta().setText("<html><div style='width: 350px; text-align: center;margin-left: 100px;'>" + pregunta.getPregunta() + "</div></html>");
        this.vista.getBtnpreRespuesta1().setText(pregunta.getOpcionA());
        this.vista.getBtnpreRespuesta2().setText(pregunta.getOpcionB());
        this.vista.getBtnpreRespuesta3().setText(pregunta.getOpcionC());
        this.vista.getBtnpreRespuesta4().setText(pregunta.getOpcionD());
        
        }
    private void verificarRondaFinalYGanador() {
        List<Participante> participantesFinales = this.escalon.getParticipantes();
        Participante participante1 = participantesFinales.get(0);
        Participante participante2 = participantesFinales.get(1);       
        int aciertos1 = participante1.getCantAciertos();
        int aciertos2 = participante2.getCantAciertos();
            if (aciertos1!=aciertos2 && (aciertos1!=0 || aciertos2!=0 )) {
                if(aciertos1!=aciertos2){
                    if (aciertos1 > aciertos2) {//  ganador
                        System.out.println("cantidad aciertos juador 0 "+ aciertos1);
                        System.out.println("cantidad aciertos juador 1 "+ aciertos2);
                        System.out.println("El ganador es: " + participante1.getNombre());
                        
                        //aca iria la vista de winner
                        PanelJugadorFinal panelParFinal=this.vista.getJugadorFinal().get(0);
                        PanelJugadorFinal panelParFinal2=this.vista.getJugadorFinal().get(1);
                        //panelParFinal.setCampeon();
                        //panelParFinal2.setEliminado();
                        this.vista.setGanadorNombre(this.escalon.getParticipantes().getFirst().getNombre());
                        this.vista.getPanelGanador().setVisible(true);
                        this.vista.getPanelPregunta().setVisible(false);
                        this.vista.getPanelFinal().setVisible(false);
                        //deberia saltar una ultima vista con dialog campeon
                    } else if (aciertos2 > aciertos1) {
                        System.out.println("cantidad aciertos juador 0 "+ aciertos1);
                        System.out.println("cantidad aciertos juador 1 "+ aciertos2);
                        System.out.println("El ganador es: " + participante2.getNombre());
                        this.vista.setGanadorNombre(this.escalon.getParticipantes().get(1).getNombre());
                        PanelJugadorFinal panelParFinal=this.vista.getJugadorFinal().get(0);
                        PanelJugadorFinal panelParFinal2=this.vista.getJugadorFinal().get(1);
                        //panelParFinal2.setCampeon();
                        //panelParFinal.setEliminado();
                        this.vista.getPanelGanador().setVisible(true);
                        this.vista.getPanelPregunta().setVisible(false);
                        this.vista.getPanelFinal().setVisible(false);
                        //aca iria la vista de winner
                        //deberia saltar una ultima vista con dialog campeon y que salte al inicio
                    }}
            }  else if (aciertos1==aciertos2){
            for (Participante par: participantesFinales){
                par.setCantErrores(0);
                par.setCantAciertos(0);
            }
            huboEmpate=true;
            escalon.getEstadoDeRonda().setRondaDeEmpate(participantesFinales);
            escalon.getEstadoDeRonda().actualizarDatos(escalon.getEstadoDeRonda(),participantesFinales, escalon.getTema());
            this.vista.getPanelAproximacion().setVisible(true);
            setActivosEmpatados();
            mostrarPreguntaEmpate();
            
            
            }}
    private void empateFinal(){
        if (huboEmpate==false){
            this.vista.setGanadorNombre(this.escalon.getParticipantes().getFirst().getNombre());
            this.vista.getPanelGanador().setVisible(true);
            this.vista.getPanelPregunta().setVisible(false);
            this.vista.getPanelFinal().setVisible(false);
        }
    }
	private void poneNombres(){
        for (int i = 0; i < 9; i++) {
			this.vista.getJugadorNormal().get(i).setNombre(escalon.getParticipantes().get(i).getNombre());
			this.vista.getJugadorNormal().get(i).setImagen(escalon.getParticipantes().get(i).getImg());
		}
	}
	
	//Metodos para filtrar y eliminar participantes
	private List<Participante> getParticipantesAEliminar() {
        List<Participante> participantesAEliminar = new ArrayList<>();
        int maxErrores = 0;

        for (Participante participante : escalon.getParticipantes()) {
            int errParticipante = participante.getCantErrores();
            if (errParticipante > 0) {
                if (errParticipante > maxErrores) {
                    maxErrores = errParticipante;
                    participantesAEliminar.clear();
                    participantesAEliminar.add(participante);
                } else if (errParticipante == maxErrores) {
                    participantesAEliminar.add(participante);
                }
            }
        }

        if (maxErrores == 0) {
            for (Participante participante : escalon.getParticipantes()) {
                participantesAEliminar.add(participante);
            }
        }

        // If there is only one participant with the maximum errors, return only that participant
        if (participantesAEliminar.size() > 1) {
            List<Participante> maxErroresParticipantes = new ArrayList<>();
            for (Participante participante : participantesAEliminar) {
                if (participante.getCantErrores() == maxErrores) {
                    maxErroresParticipantes.add(participante);
                }
            }
            return maxErroresParticipantes;
        }
        return participantesAEliminar;
    }
    private void filtrarParticipantes(){
        List<Participante> participantesAEliminar = getParticipantesAEliminar();
        //Si hay mas de un participante con la misma cantidad de errores, setea la ronda de empate
        if (participantesAEliminar.size()>1){
            // les envia la pregunta de aproximacion a todos los participantes empatados.
            this.vista.getPanelPregunta().setVisible(false);
        	this.vista.getPanelAproximacion().setVisible(true);
			Ronda ronda = this.escalon.getEstadoDeRonda();
            huboEmpate = true;
            //Envia la lista de participantes a eliminar y sigue la la logica de la ronda de empate
            ronda.setRondaDeEmpate(participantesAEliminar);
            ronda.actualizarDatos(ronda, participantesAEliminar, this.escalon.getTema());
            
            // Aca hay que meter la logica a la ronda de empate, aunque capaz no hace falta
            // mostrarPreguntaEmpate()
        }else{
            //Si solo hay uno, se elimina
            Participante participante = participantesAEliminar.getFirst();
            //Para ver la posicion en el panel recupero el indice que ocupa en la lista
            int indice = this.escalon.getParticipantes().indexOf(participante);
            this.setColores();
            if (this.escalon.getEscalon()<7){
                this.vista.getJugadorNormal().get(indice).setEliminado();
                this.vista.getJugadorNormal().remove(indice);
                Ronda estado = this.escalon.getEstadoDeRonda();
                estado.setRondaNormal();
            escalon.setTema();
            estado.actualizarDatos(estado, escalon.getParticipantes(), escalon.getTema());
            this.rondaDePreguntas(escalon.getParticipantes());
            }
            this.escalon.eliminaParticipante(participante);
            
            this.vista.getPanelAproximacion().setVisible(false);
            this.vista.getPanelPregunta().setVisible(true);
           
            
        }
    }

    //Procesar preguntas y respuestas
    private void inicializarActionListeners(){
        this.vista.getBtnpreRespuesta1().addActionListener(e -> {
            if (esperandoRespuesta ) {
                if (this.escalon.getEscalon()==7 ){
                    procesarRespuestaFinal(e.getActionCommand(),escalon.getParticipantes().get(turnoJugador));
                }else  if(this.escalon.getEscalon()<7){
                    procesarRespuesta(e.getActionCommand());
                }
            } 
        });
        this.vista.getBtnpreRespuesta2().addActionListener(e -> {
            if (esperandoRespuesta) {
                if (this.escalon.getEscalon()==7 ){
                    procesarRespuestaFinal(e.getActionCommand(),escalon.getParticipantes().get(turnoJugador));
                }else if(this.escalon.getEscalon()<7) {
                    procesarRespuesta(e.getActionCommand());
                }
            }
        });
        this.vista.getBtnpreRespuesta3().addActionListener(e -> {
            if (esperandoRespuesta) {
                if (this.escalon.getEscalon()==7 ){
                    procesarRespuestaFinal(e.getActionCommand(),escalon.getParticipantes().get(turnoJugador));
                }else  if(this.escalon.getEscalon()<7) {
                    procesarRespuesta(e.getActionCommand());
                }
            }
        });
        this.vista.getBtnpreRespuesta4().addActionListener(e -> {
            if (esperandoRespuesta) {
                if (this.escalon.getEscalon()==7 ){
                    procesarRespuestaFinal(e.getActionCommand(),escalon.getParticipantes().get(turnoJugador));
                }else  if(this.escalon.getEscalon()<7) {
                    procesarRespuesta(e.getActionCommand());
                }
            }
        });

        this.vista.getBtnaproxEnviar().addActionListener(e->{
            if(esperandoRespuesta && huboEmpate){
                try{
                    Double ResParse = Double.valueOf(this.vista.getTxtaproxRespuesta().getText());
                procesarRespuestaEmpate(ResParse);
                }catch(NumberFormatException e1){
                    System.out.println("Error, ingrese un numero " + e1.getMessage());
                }
            }
        });
        
        KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1, KeyEvent.VK_NUMPAD1 -> {
                    if (!huboEmpate) {
                        vista.getBtnpreRespuesta1().doClick();
                    }
                }
                case KeyEvent.VK_2, KeyEvent.VK_NUMPAD2 -> {
                    if (!huboEmpate) {
                        vista.getBtnpreRespuesta2().doClick();
                    }
                }
                case KeyEvent.VK_3, KeyEvent.VK_NUMPAD3 -> {
                    if (!huboEmpate) {
                        vista.getBtnpreRespuesta3().doClick();
                    }
                }
                case KeyEvent.VK_4, KeyEvent.VK_NUMPAD4 -> {
                    if (!huboEmpate) {
                        vista.getBtnpreRespuesta4().doClick();
                    }
                }
                case KeyEvent.VK_ENTER -> vista.getBtnaproxEnviar().doClick();
            }
        }
    };

        this.vista.getBtnpreRespuesta1().addKeyListener(keyListener);
        this.vista.getBtnpreRespuesta2().addKeyListener(keyListener);
        this.vista.getBtnpreRespuesta3().addKeyListener(keyListener);
        this.vista.getBtnpreRespuesta4().addKeyListener(keyListener);
        this.vista.getTxtaproxRespuesta().addKeyListener(keyListener);
    }
    private void setColores(){
    //Setea los colores del fondo para indicar de quien es el turno
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
            }}
    private void poneNombresEscalones(){
        this.vista.getEscalones().getFirst().getLblTema().setText(this.escalon.getTema().getNombre());
        int indice=0;
        for (int i = 1; i < 7; i++) {
            this.vista.getEscalones().get(i).getLblTema().setText(this.escalon.getTemas().get(indice).getNombre());
            indice++;
        }
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
