package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ExpenseInput {
	private SimpleIntegerProperty amount;
	private SimpleStringProperty date;
	private SimpleStringProperty categories;

	public ExpenseInput(Integer amount, String date, String categories) {
		super();
		this.amount = new SimpleIntegerProperty(amount);
		this.date = new SimpleStringProperty(date);
		this.categories = new SimpleStringProperty(categories);
	}
	
	public Integer getExpenseTableAmountCol() {
		return amount.get();
	}
	
	public String getExpenseTableDateCol() {
		return date.get();
	}
	
	public String getExpenseTableCategoriesCol() {
		return categories.get();
	}

	
	
}
