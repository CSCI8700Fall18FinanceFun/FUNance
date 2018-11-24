package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class expenseLog {
	private SimpleIntegerProperty amount;
	private SimpleStringProperty date;
	private SimpleStringProperty categories;
	
	public expenseLog(Integer amount, String date, String categories) {
		super();
		this.amount = new SimpleIntegerProperty(amount);
		this.date = new SimpleStringProperty(date);
		this.categories = new SimpleStringProperty(categories);
	}
	
	public Integer getExpenseLogTableAmountCol() {
		return amount.get();
	}
	
	public String getExpenseLogTableDateCol() {
		return date.get();
	}
	
	public String getExpenseLogTableCategoriesCol() {
		return categories.get();
	}

}
