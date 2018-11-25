package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcess {

	public static ArrayList<IncomeEntry> readIncomeFromFile(String fname)
	{
		ArrayList<IncomeEntry> incomes = new ArrayList<>();
		try
		{
			Scanner in = new Scanner(new File(fname));
			//read header
			in.nextLine();
			while (in.hasNextLine())
			{
				try
				{
					String line = in.nextLine();
					String[] token = line.split(",");
					if (token.length == 3)
					{
						double amount = Double.parseDouble(token[0]);
						String source = token[1];
						int freq = IncomeEntry.convertFrequency(token[2]);
						incomes.add(new IncomeEntry(amount, source, freq));
			//				System.out.println(amount + "," + source + "," + IncomeEntry.INCOME_FREQUENCY[freq]);
					}
				}
				catch (NumberFormatException nfe)
				{
					
				}
			}
			in.close();
		}
		catch (FileNotFoundException fnfe)
		{
//			System.out.println(fname + "open expection: " + fnfe);
		}
		
		
		return incomes;
	}
	
	public static ArrayList<ExpenseEntry> readExpenseFromFile(String fname)
	{
		ArrayList<ExpenseEntry> expenses = new ArrayList<>();
		try
		{
			Scanner in = new Scanner(new File(fname));
			//read header
			in.nextLine();
			while (in.hasNextLine())
			{
				try
				{
					String line = in.nextLine();
					String[] token = line.split(",");
					if (token.length == 3)
					{
						double amount = Double.parseDouble(token[0]);
						String date = token[1];
						int ctg = ExpenseEntry.convertCategory(token[2]);
						expenses.add(new ExpenseEntry(amount, date, ctg));
		//				System.out.println(amount + "," + date + "," + ExpenseEntry.EXPENSE_CATEGORY[ctg]);
					}
				}
				catch (NumberFormatException nfe)
				{
					
				}
			}
			in.close();
		}
		catch (FileNotFoundException fnfe)
		{
//			System.out.println(fname + "open expection: " + fnfe);
		}
		
		
		return expenses;
	}
	
	public static void writetoFile(String fname, String s, boolean append)
	{
	  try
	  {
	   FileWriter fw = new FileWriter(fname, append);
	   fw.append(s);
	   fw.close();
	  }
	  catch (IOException ioe)
	  {
	   
	  }
	 }
}
