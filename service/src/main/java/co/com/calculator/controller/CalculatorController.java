package co.com.calculator.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.calculator.builder.IOperationBuilder;
import co.com.calculator.builder.OperationBuilderFactory;
import co.com.calculator.model.Session;
import co.com.calculator.model.SessionResponse;
import co.com.calculator.observable.SessionObserver;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/calculator")
public class CalculatorController {

	// Observable
	private Session session;
	private SessionObserver observer;
	private final OperationBuilderFactory operationBuilderfactory;
	
	Logger logger = LoggerFactory.getLogger(CalculatorController.class);

	/***
	 * Constructor Observable and Observer
	 * 
	 * @param session
	 * @param observer
	 */
	public CalculatorController(OperationBuilderFactory operationBuilderfactory) {
		this.operationBuilderfactory = operationBuilderfactory;
	}

	/***
	 * Constructor default
	 */
	public CalculatorController() {
		this(new OperationBuilderFactory());
	}

	@GetMapping("/newSession")
	public ResponseEntity<SessionResponse> newSession() {
		logger.info("Start create session");
		this.session = new Session();
		this.observer = new SessionObserver();
		this.session.addPropertyChangeListener(this.observer);
		
		String sessionId = UUID.randomUUID().toString();
		this.session.setSessionId(sessionId);
		// Se prepara respuesta
		SessionResponse response = new SessionResponse("Session Created", sessionId);
		
		return new ResponseEntity<SessionResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/addValue/{value}")
	public ResponseEntity<String> addValue(@PathVariable double value) {
		logger.info("Start add value");
		if (null != this.session && null != this.session.getSessionId())
			this.observer.getValues().add(value);
		else
			return new ResponseEntity<String>("Session not inicializated", HttpStatus.NOT_ACCEPTABLE);
		
		return new ResponseEntity<String>("Added value " + value, HttpStatus.OK);
	}

	@GetMapping("/executeOperation/{operation}")
	public ResponseEntity<String> executeOperation(@PathVariable String operation) {
		logger.info("Start execute operation");
		double result = 0D;
		
		if (null != this.session && null != this.session.getSessionId()) {
			try {
				IOperationBuilder operationBuilder = this.operationBuilderfactory.buildOperation(operation);
				result = operationBuilder.executeOperation(this.observer.getValues());
				// Se reinician operandos
				this.observer.setValues(new ArrayList<Double>());
				this.observer.getValues().add(result);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Error build operation. " + e.getMessage(), HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<String>("Session not inicializated", HttpStatus.NOT_ACCEPTABLE);		
		
		return new ResponseEntity<String>("The result is: " + result, HttpStatus.OK);
	}
}
