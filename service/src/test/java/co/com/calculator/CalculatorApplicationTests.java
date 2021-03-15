package co.com.calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import co.com.calculator.builder.IOperationBuilder;
import co.com.calculator.builder.OperationBuilderFactory;
import co.com.calculator.controller.CalculatorController;
import co.com.calculator.model.Session;
import co.com.calculator.observable.SessionObserver;

@SpringBootTest
class CalculatorApplicationTests {

	@Bean
	public OperationBuilderFactory operationBuilderFactory() {
		return new OperationBuilderFactory();
	}
	
	@Autowired
	private CalculatorController calculatorController;
	
	@Test
	void contextLoads() {
	}
	
	/***
	 * Este metodo permite validar la notificación del cambio de la propiedad sessionId
	 */
	@Test
	void whenChangeSessionId_theNewSessionNotified() {
		Session observable = new Session();
//		Session session1 = new Session();
		SessionObserver observer = new SessionObserver();
		
		observable.addPropertyChangeListener(observer);
		String sessionId = UUID.randomUUID().toString();
		
		observable.setSessionId(sessionId);
		assertEquals(observer.getSessionId(), sessionId);
	}
	
	/***
	 * Este método permite probar operaciones existente
	 * @throws Exception 
	 */
	@Test
	void whenExecuteOperationAndExist() throws Exception {
		List<Double> values = Arrays.asList(0D,1D,2D,3D,4D,5D,6D,7D,8D,9D);
		IOperationBuilder operationBuilder = operationBuilderFactory().buildOperation("suma");
		double result = operationBuilder.executeOperation(values);
		
		assertEquals(45D, result);
	}
	
	/***
	 * Este método permite probar operaciones que no existenten
	 * @throws Exception 
	 */
	@Test
	void whenExecuteOperationAndNotExist() {
		
		String operation = "raizcuadrada";
		
		Exception ex = assertThrows(Exception.class, () -> operationBuilderFactory().buildOperation("raizcuadrada"));
		
		String expectedMessage = "Operation " + operation + " no found";
		
		assertTrue(ex.getMessage().equalsIgnoreCase(expectedMessage));
	}
	
	/***
	 * Método que valida el comportamiento de la división por cero. Se genera un Double infinito
	 * @throws Exception
	 */
	@Test
	void isDivisionByZero() throws Exception {
		List<Double> values = Arrays.asList(24d, 0d);
		IOperationBuilder operationBuilder = operationBuilderFactory().buildOperation("DIVISION");
		double result = operationBuilder.executeOperation(values);
		
		assertTrue(Double.isInfinite(result));
	}
	
	/***
	 * Método que valida la potencia por cero
	 * @throws Exception
	 */
	@Test
	void isPowByZero() throws Exception {
		List<Double> values = Arrays.asList(24d, 0d);
		IOperationBuilder operationBuilder = operationBuilderFactory().buildOperation("potencia");
		double result = operationBuilder.executeOperation(values);
		
		assertEquals(1D, result);
	}
	
	/***
	 * Método que valida la potencia con valores máximos de Double
	 * @throws Exception
	 */
	@Test
	void isPowExtremeValues() throws Exception {
		List<Double> values = Arrays.asList(Double.MAX_VALUE, Double.MAX_VALUE);
		IOperationBuilder operationBuilder = operationBuilderFactory().buildOperation("potencia");
		double result = operationBuilder.executeOperation(values);
		
		assertTrue(Double.isInfinite(result));
	}

}
