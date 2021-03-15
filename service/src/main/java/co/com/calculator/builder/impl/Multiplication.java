package co.com.calculator.builder.impl;

import java.util.List;

import co.com.calculator.builder.IOperationBuilder;

public class Multiplication implements IOperationBuilder {

	public Multiplication() {
	}

	@Override
	public double executeOperation(List<Double> values) {
		double result = 0;

		if (null != values) {
			result = values.stream().mapToDouble(Double::doubleValue).reduce(1, (total, value) -> total * value);
		}

		return result;
	}

}
