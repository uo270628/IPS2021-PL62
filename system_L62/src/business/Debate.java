package business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Debate {
	public enum DebateState{ACTIVE,FINISHED}
	private String id;
	private String idArticulo;
	private LocalDateTime fechaLimite;
	private DebateState state;
	
	public Debate(String id,String idArticulo,LocalDateTime fechaLimite) {
		if(fechaLimite.isBefore(LocalDateTime.now()))
			state=DebateState.FINISHED;
		else
			state=DebateState.ACTIVE;
		this.id=id;
		this.idArticulo=idArticulo;
		this.fechaLimite=fechaLimite;
		
	}
	
	public String getId() {
		return id;
	}

	public DebateState getState() {
		return state;
	}

	public void setState(DebateState state) {
		this.state = state;
	}


	
	public String getIdArticulo() {
		return idArticulo;
	}

	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}
	public void finalizarDebate() {
		this.state=DebateState.FINISHED;
	}
	
	
}
