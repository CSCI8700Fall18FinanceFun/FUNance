package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class incomeLog {
	private SimpleStringProperty source;
	private SimpleStringProperty frequency;
	private SimpleIntegerProperty amount;
	
	public incomeLog(Integer amount, String source, String Frequency) {
		super();
		this.amount = new SimpleIntegerProperty(amount);
		this.source = new SimpleStringProperty(source);
		this.frequency = new SimpleStringProperty(Frequency);
	}
	
	public String getIncomeLogTableSourceCol() {
		return source.get();
	}
	
	public String getIncomeLogTableFrequencyCol() {
		return frequency.get();
	}

	public Integer getIncomeLogTableAmountCol() {
		return amount.get();
	}
}
