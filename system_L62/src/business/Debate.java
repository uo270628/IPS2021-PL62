package business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Debate {
	public enum DebateState{ACTIVE,FINISHED}
	private String id;
	private List<Revisor>list= new ArrayList<>();
	private LocalDateTime fechaLimite;
	private DebateState state;
	
	public Debate(String id,List<Revisor>list,LocalDateTime fechaLimite) {
		if(fechaLimite.isBefore(LocalDateTime.now()))
			state=DebateState.FINISHED;
		else
			state=DebateState.ACTIVE;
		this.id=id;
		this.list=list;
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


	public List<Revisor> getList() {
		return list;
	}

	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}
	public void finalizarDebate() {
		this.state=DebateState.FINISHED;
	}
	
	
}
