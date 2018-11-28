package application;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
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
	
	public static ObservableList<ExpenseEntry> filterExpenseByDateRange(ObservableList<ExpenseEntry> expenseList, String startDate, String endDate)
	{
		Iterator<ExpenseEntry> itr = expenseList.iterator();
		ObservableList<ExpenseEntry> outList = FXCollections.observableArrayList();	
		
		while(itr.hasNext())
		{
			ExpenseEntry expense = itr.next();
			String date = expense.getDate();
			if (withinDateRange(startDate, endDate, date))
			{
				outList.add(expense);
			}
		}
		
		return outList;
	}
	
	public static ObservableList<IncomeEntry> filterIncomeByDateRange(ObservableList<IncomeEntry> incomeList, String startDate, String endDate)
	{
		Iterator<IncomeEntry> itr = incomeList.iterator();
		ObservableList<IncomeEntry> outList = FXCollections.observableArrayList();	
		
		while(itr.hasNext())
		{
			IncomeEntry income = itr.next();
			String date = income.getDate();
			if (withinDateRange(startDate, endDate, date))
			{
				outList.add(income);
			}
		}
		
		return outList;
	}
	public static double balance(ObservableList<ExpenseEntry> expenseList, ObservableList<IncomeEntry> incomeList)
	{
 		Iterator<ExpenseEntry> itrE = expenseList.iterator();
		Iterator<IncomeEntry> itrI = incomeList.iterator();
		double balance = 0;
		while(itrI.hasNext())
		{
			IncomeEntry income = itrI.next();
			Double i = income.getAmount();
			balance += i;
		}
		while(itrE.hasNext())
		{
			ExpenseEntry expense = itrE.next();
			Double e = expense.getAmount();
			balance -= e;
		}
		return balance;
	}
	public static double[] procesExpenseBarChart(ObservableList<ExpenseEntry> expenseList, int year, int month)
	 {
	  Iterator<ExpenseEntry> itr = expenseList.iterator();
	  double[] categories = new double[ExpenseEntry.EXPENSE_CATEGORY.length];
	  
	  //initialize values to zero
	  for (int i = 0; i<categories.length; i++)
	  {
	   categories[i] = 0;
	  }
	  
	  while(itr.hasNext())
	  {
	   ExpenseEntry expense = itr.next();
	   String date = expense.getDate();
	   String[] tok = date.split("-");
	   int y = Integer.parseInt(tok[0]);
	   int m = Integer.parseInt(tok[1]);
	//   int d = Integer.parseInt(tok[2]);
	   if (y == year && m == month)
	   {
	    int ctg = ExpenseEntry.convertCategory( expense.getCategory() );
	    categories[ctg] += expense.getAmount();
	   }
	  }
	  
	  return categories;
	 }
	 
	 public static double[] procesExpensePieChart(ObservableList<ExpenseEntry> expenseList, int year)
	 {
	  Iterator<ExpenseEntry> itr = expenseList.iterator();
	  double[] categories = new double[ExpenseEntry.EXPENSE_CATEGORY.length];
	  
	  //initialize values to zero
	  for (int i = 0; i<categories.length; i++)
	  {
	   categories[i] = 0;
	  }
	  
	  double total = 0.0;
	  while(itr.hasNext())
	  {
	   ExpenseEntry expense = itr.next();
	   String date = expense.getDate();
	   String[] tok = date.split("-");
	   int y = Integer.parseInt(tok[0]);
	//   int m = Integer.parseInt(tok[1]);
	//   int d = Integer.parseInt(tok[2]);
	   if (y == year)
	   {
	    int ctg = ExpenseEntry.convertCategory( expense.getCategory() );
	    categories[ctg] += expense.getAmount();
	    total += expense.getAmount();
	   }
	  }
	  
	  for (int i=0; i<categories.length; i++)
	  {
	   categories[i] = categories[i] / total * 100.0;
	  }
	  
	  return categories;
	 }
	
	
	public static boolean withinDateRange(String startDate, String endDate, String date)
	{
		return ( compareDate(startDate, date) <=0 && compareDate(date, endDate) <=0);
			
	}
	
//	public static void main(String[] args) {
//		compareDate("2018-11-13", "2018-11-14");
//		withinDateRange 
//		System.out.println(compareDate("2018-11-13", "2018-11-12"));
//	}
	
	public static int compareDate(String date1, String date2)
	{
		String[] tok = date1.split("-");
		int year1 = Integer.parseInt(tok[0]);
		int mon1 = Integer.parseInt(tok[1]);
		int day1 = Integer.parseInt(tok[2]);
		
		String[] date2Tok = date2.split("-");
		int year2 = Integer.parseInt(date2Tok[0]);
		int mon2 = Integer.parseInt(date2Tok[1]);
		int day2 = Integer.parseInt(date2Tok[2]);
		
		int val = -1;
		if ( year1 > year2)
		{
			val = 1;
		}
		else if (year1 == year2)
		{
			if (mon1 > mon2)
			{
				val = 1;
			}
			else if (mon1 == mon2)
			{
				if (day1 > day2)
				{
					val = 1;
				}
				else if (day1 == day2)
				{
					val = 0;
				}
			}
		}
		
		return val;
	}
}
