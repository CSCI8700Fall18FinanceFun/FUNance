package application;

public class IncomeEntry {
	public static final String[] INCOME_FREQUENCY = {"Bi-week", 
			"Hourly", 
			"Monthly"};
	private double amount;
	private String source;
	private int frequency;
	//todo add in startDate of this income to allow auto-compute accumulated income as time goes along.
//	private String startDate;
	
	public IncomeEntry(double inAmount, String inSource, int inFreq)
	{
		
		setAmount(inAmount);
		setSource(inSource);
		setFrequency(inFreq);
	}
	
	public void setAmount(double inAmount)
	{
		amount = inAmount;
	}
	
	public void setSource(String inSource)
	{
		source = inSource;
	}
	
	public void setFrequency(int inFreq)
	{
		frequency = inFreq;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public String getFrequency()
	{
		return INCOME_FREQUENCY[frequency];
	}
	
	public static int convertFrequency(String f)
	{
		int freqIndex = -1;
		if (f == null)
		{
			return freqIndex;
		}
		
		for (int i=0; i<INCOME_FREQUENCY.length; i++)
		{
			if (f.equals(INCOME_FREQUENCY[i]))
			{
				freqIndex = i;
				break;
			}
		}
		return freqIndex;
	}
}
