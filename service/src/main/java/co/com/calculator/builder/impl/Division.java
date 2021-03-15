package co.com.calculator.builder.impl;

import java.util.List;

import co.com.calculator.builder.IOperationBuilder;

public class Division implements IOperationBuilder {

	public Division() {
	}

	@Override
	public double executeOperation(List<Double> values) {
		double result = 0;

		if (null != values && values.size() > 0) {
			// Se obtiene primer valor de la coleccion para inicializar el total en la
			// segunda iteración
			double total = 0;
			boolean firstValue = true;

			for (Double value : values) {
				if (firstValue) {
					total = value;
					firstValue = false;
				} else
					total /= value;
			}

			result = total;
		}

		return result;
	}

}
