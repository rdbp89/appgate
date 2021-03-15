package co.com.calculator.observable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SessionObserver implements PropertyChangeListener {
	
	private List<Double> values;
	private String sessionId;
	
	@Override
	public void propertyChange(PropertyChangeEvent ev) {
		// TODO Auto-generated method stub
		String propertyName = ev.getPropertyName();
		
		switch (propertyName) {
		case "sessionId":
			this.setValues(new ArrayList<Double>());
			this.setSessionId((String) ev.getNewValue());
			break;
		default:
			new Exception("PropertyName no found " + propertyName);
			break;
		}
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
