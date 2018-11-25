package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileProcess {

	public static ObservableList<IncomeEntry> readIncomeFromFile(String fname)
	{
		ObservableList<IncomeEntry> incomes = FXCollections.observableArrayList();
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
					if (token.length == 4)
					{
						Double amount = Double.parseDouble(token[0]);
						String source = token[1];
						String freq = token[2];
						String date = token[3];
						//int freq = IncomeEntry.convertFrequency(token[2]);
						incomes.add(new IncomeEntry(amount, source, freq, date));
//							System.out.println(amount + "," + source + "," + freq + date);
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
	
	public static ObservableList<ExpenseEntry> readExpenseFromFile(String fname)
	{
		ObservableList<ExpenseEntry> expenses = FXCollections.observableArrayList();
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
						Double amount = Double.parseDouble(token[0]);
						String date = token[1];
						String ctg = token[2];
//						int ctg = ExpenseEntry.convertCategory(token[2]);
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
