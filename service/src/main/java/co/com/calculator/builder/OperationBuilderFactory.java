package co.com.calculator.builder;

import co.com.calculator.builder.impl.Division;
import co.com.calculator.builder.impl.Multiplication;
import co.com.calculator.builder.impl.Pow;
import co.com.calculator.builder.impl.Subtract;
import co.com.calculator.builder.impl.Sum;
import co.com.calculator.model.Operation;

public class OperationBuilderFactory {
	
	public IOperationBuilder buildOperation(String operation) throws Exception {
		if (Operation.SUMA.toString().equalsIgnoreCase(operation)) {
			return new Sum();
		} else if (Operation.RESTA.toString().equalsIgnoreCase(operation)){
			return new Subtract();
		} else if (Operation.MULTIPLICACION.toString().equalsIgnoreCase(operation)){
			return new Multiplication();
		} else if (Operation.DIVISION.toString().equalsIgnoreCase(operation)){
			return new Division();
		} else if (Operation.POTENCIA.toString().equalsIgnoreCase(operation)){
			return new Pow();
		} else {
			throw new Exception("Operation " + operation +" no found");
		}
	}
}
