package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IncomeInput {
	private SimpleStringProperty source;
	private SimpleStringProperty frequency;
	private SimpleIntegerProperty amount;
	
	public IncomeInput(Integer amount, String source, String Frequency) {
		super();
		this.amount = new SimpleIntegerProperty(amount);
		this.source = new SimpleStringProperty(source);
		this.frequency = new SimpleStringProperty(Frequency);
	}

	public String getIncomeTableSourceCol() {
		return source.get();
	}
	
	public String getIncomeTableFrequencyCol() {
		return frequency.get();
	}

	public Integer getIncomeTableAmountCol() {
		return amount.get();
	}
	
}
