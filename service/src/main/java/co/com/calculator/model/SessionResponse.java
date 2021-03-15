package co.com.calculator.model;

public class SessionResponse {

	private String message;
	private String sessionId;
	
	public SessionResponse() {
	}
	
	public SessionResponse(String message, String sessionId) {
		this.message = message;
		this.sessionId = sessionId;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
