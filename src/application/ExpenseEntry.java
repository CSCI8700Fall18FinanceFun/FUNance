package application;

public class ExpenseEntry {
	public static final String[] EXPENSE_CATEGORY = {"Housing", 
			"Transportation", 
			"Food", 
			"Utilities", 
			"Clothing", 
			"Medical", 
			"Insurance", 
			"Persional", 
			"Education", 
			"Entertainment"};
			
	private double amount;
	private String date;
	private int category;
	
	public ExpenseEntry(double inAmount, String inDate, int inCategory)
	{
		setAmount(inAmount);
		setDate(inDate);
		setCategory(inCategory);
	}
	
	public void setAmount(double inAmount)
	{
		amount = inAmount;
	}
	
	public void setDate(String inDate)
	{
		//todo check whether inDate is valid
		date = inDate;
	}
	
	public void setCategory(int inCategory)
	{
		if (inCategory < 0 || inCategory >= EXPENSE_CATEGORY.length)
		{
			//todo change it to INVALID_EXCEPTION.
			System.out.printf("invalid category: %d\n", inCategory);
			return;
		}
		category = inCategory;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getCategory()
	{
		return EXPENSE_CATEGORY[category];
	}
	
	public static int convertCategory(String ctg)
	{
		int ctgIndex = -1;
		if (ctg == null)
		{
			return ctgIndex;
		}
		
		for (int i=0; i<EXPENSE_CATEGORY.length; i++)
		{
			if (ctg.equals(EXPENSE_CATEGORY[i]))
			{
				ctgIndex = i;
				break;
			}
		}
		return ctgIndex;
	}

}
