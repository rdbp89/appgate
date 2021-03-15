package co.com.calculator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Session {

	private String sessionId;
	private List<Double> values;
	private String operation;
	private PropertyChangeSupport sessionObservable;
	
	public Session() {
		sessionObservable = new PropertyChangeSupport(this);
	}

	public Session(String sessionId) {
		this.sessionId = sessionId;
	}

	public void addPropertyChangeListener(PropertyChangeListener propertieChangeListener) {
		sessionObservable.addPropertyChangeListener(propertieChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertieChangeListener) {
		sessionObservable.removePropertyChangeListener(propertieChangeListener);
	}

	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		sessionObservable.firePropertyChange("sessionId", this.sessionId, sessionId);
		this.sessionId = sessionId;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
