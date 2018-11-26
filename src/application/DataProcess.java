package application;

import java.util.Iterator;

import javafx.collections.ObservableList;

public class DataProcess {
	public static final int NUMBER_OF_MONTH = 12;
	public static final String[] MONTHES = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	public static double[] procesIncomeByMonth(ObservableList<IncomeEntry> incomeList, int year)
	{
		Iterator<IncomeEntry> itr = incomeList.iterator();
		double[] monthly = new double[NUMBER_OF_MONTH];
		
		//initialize values to zero
		for (int i = 0; i<monthly.length; i++)
		{
			monthly[i] = 0;
		}
		
		while(itr.hasNext())
		{
			IncomeEntry income = itr.next();
			String date = income.getDate();
			String[] tok = date.split("-");
			int y = Integer.parseInt(tok[0]);
			int m = Integer.parseInt(tok[1]);
//			int d = Integer.parseInt(tok[2]);
			if (y == year)
			{
				monthly[m-1] += income.getAmount();
			}
		}
		
		return monthly;
	}
	
	
	public static double[] procesExpenseByMonth(ObservableList<ExpenseEntry> expenseList, int year)
	{
		Iterator<ExpenseEntry> itr = expenseList.iterator();
		double[] monthly = new double[NUMBER_OF_MONTH];
		
		//initialize values to zero
		for (int i = 0; i<monthly.length; i++)
		{
			monthly[i] = 0;
		}
		
		while(itr.hasNext())
		{
			ExpenseEntry expense = itr.next();
			String date = expense.getDate();
			String[] tok = date.split("-");
			int y = Integer.parseInt(tok[0]);
			int m = Integer.parseInt(tok[1]);
//			int d = Integer.parseInt(tok[2]);
			if (y == year)
			{
				monthly[m-1] += expense.getAmount();
			}
		}
		
		return monthly;
	}
}
