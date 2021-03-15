package co.com.calculator.builder.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.com.calculator.builder.IOperationBuilder;

/***
 * Builder para executar suma de operandos
 * @author rubéndarío
 *
 */
public class Sum implements IOperationBuilder {

	public Sum() {
	}

	@Override
	public double executeOperation(List<Double> values) {
		double result = 0;
		
		if (null != values) {
			result = values.stream().collect(Collectors.summingDouble(Double::doubleValue));
		}
		
		return result;
	}
}
