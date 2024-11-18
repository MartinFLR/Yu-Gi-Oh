package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import model.Participante;
import model.PreguntaAproximacion;
import model.PreguntaOpcion;
import model.Tema;
import model.logica.Escalon;
import model.logica.Ronda;
import view.VistaJuego;
import view.componentes.PanelJugadorFinal;
import view.componentes.PanelJugadorNormal;


public class ControladorJuego implements ActionListener, KeyListener {
	private final VistaJuego vista;
	private final Escalon escalon;
    private int turnoJugador = 0;
    private boolean esperandoRespuesta = false;
	
	public ControladorJuego(Escalon escalon) {
		this.escalon = escalon;
		this.vista = new VistaJuego(this);
		this.vista.setVisible(true);
        this.vista.getPanelAproximacion().setVisible(false);
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
    }
//setea los colores del fondo para indicar de quien es el turno
    public void setColore(){
    Participante participante;
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
	public void rondaEmpate(Ronda ronda,List<Participante> participantes){
		PreguntaAproximacion preg = participantes.get(0).getPregEmpate();
        Double respuestaCorrecta = Double.valueOf(preg.getRespuestaCorrecta());
        double respMasLejana = 0;
        double diferencia;
        Participante peorParticipante = null;
        List<Participante> empatados = new ArrayList<>();
        preg.imprimirPregunta();

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


    public void verificarRondaFinalYGanador(List<Participante> participantes) {
        List<Participante> participantesFinales = this.escalon.getParticipantes();
        Participante participante1 = participantesFinales.get(0);
        Participante participante2 = participantesFinales.get(1);       
        int aciertos1 = participante1.getCantAciertos();
        int aciertos2 = participante2.getCantAciertos();
            if (aciertos1!=aciertos2) {
                    if (aciertos1 > aciertos2) {//  ganador
                        System.out.println("El ganador es: " + participante1.getNombre());
                        //aca iria la vista de winner
                        PanelJugadorFinal panelParFinal=this.vista.getJugadorFinal().get(0);
                        PanelJugadorFinal panelParFinal2=this.vista.getJugadorFinal().get(1);
                        panelParFinal.setCampeon();
                        panelParFinal2.setEliminado();
                        //deberia saltar una ultima vista con dialog campeon
                    } else if (aciertos2 > aciertos1) {
                        System.out.println("El ganador es: " + participante2.getNombre());
                        PanelJugadorFinal panelParFinal=this.vista.getJugadorFinal().get(0);
                        PanelJugadorFinal panelParFinal2=this.vista.getJugadorFinal().get(1);
                        panelParFinal2.setCampeon();
                        panelParFinal.setEliminado();
                        //aca iria la vista de winner
                          //deberia saltar una ultima vista con dialog campeon y que salte al inicio
                    }
            } else{
                Ronda ronda= this.escalon.getEstadoDeRonda();
                ronda.setRondaDeEmpate(participantesFinales);}
            }
	/*public void rondaFinal(Ronda ronda,List<Participante> participantes){
		//La base de datos deberá tener un tema llamado Final que junte todas las preguntas, para hacer preguntas de todos los temas.
        PreguntaOpcion preg;
        String resp;
        Integer cantPreguntasRestantes = 10;        
        /*for (int i = 0; i < 10; i++){ 
            //Esta linea se puede eliminar despues, es para mostrar la preg por consola
            participantes.getFirst().getPreguntasParticipante().get(i).imprimirPregunta();
            for (Participante participante:participantes) {
                preg=participante.getPreguntasParticipante().get(i);
                // Cuando integremos con igu sacamos el scanner
                resp=participante.getRespuestaParticipante();
                if (preg.getRespuestaCorrecta().equals(resp)){
                    participante.sumaAcierto();
                }else {
                    participante.sumaError();
                    }
                }
                cantPreguntasRestantes--;
            }
                //Verifica si uno de los dos participantes ya no tiene posiblidad de remontar y termina la ronda final.
               // verificarRondaFinalYGanador(participantes);
            
            
            }*/
        
	
	
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
			Tema tema = this.escalon.getTema();
            PreguntaAproximacion preguntaAprox = tema.sacarPreguntaAprox();
            for (Participante participante : participantesAEliminar) {
                participante.setPregEmpate(preguntaAprox);
            }
			Ronda ronda = this.escalon.getEstadoDeRonda();
            //Envia la lista de participantes a eliminar y sigue la la logica de la ronda de empate
            
            // ronda.setRondaDeEmpate(participantesAEliminar);
            // ronda.actualizarDatos(ronda, participantesAEliminar, tema);
            // // Repite la ronda de desempate hasta que quede uno
            // while(participantesAEliminar.size()>1){
			// 	this.rondaEmpate(ronda, participantesAEliminar);
            //     ronda.actualizarDatos(ronda, participantesAEliminar, tema);
            // }
            // this.escalon.getParticipantes().remove(participantesAEliminar.getFirst());
            // ronda.setRondaNormal();
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
        //panelParticipante.setRespondiendo();
        this.vista.getLblprePregunta().setText("<html><div style='width: 300px;'>" + pregunta.getPregunta() + "</div></html>");
        this.vista.getBtnpreRespuesta1().setText(pregunta.getOpcionA());
        this.vista.getBtnpreRespuesta2().setText(pregunta.getOpcionB());
        this.vista.getBtnpreRespuesta3().setText(pregunta.getOpcionC());
        this.vista.getBtnpreRespuesta4().setText(pregunta.getOpcionD());
        
    }
    private void procesarRespuesta(String respuesta){
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

            if (this.escalon.getEscalon()==8){
                Ronda ronda =  this.escalon.getEstadoDeRonda();
                ronda.setRondaFinal();
                this.vista.getPanelFinal().setVisible(true);// setearia la vista final, no lo probe bien
                ronda.actualizarDatos(ronda,this.escalon.getParticipantes(), escalon);
                this.rondaDePreguntas(ronda,this.escalon.getParticipantes());
                verificarRondaFinalYGanador(this.escalon.getParticipantes());
            }
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
    private void mostrarPreguntaEmpate(){

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
