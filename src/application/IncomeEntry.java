package application;

public class IncomeEntry {
	public static final String[] INCOME_FREQUENCY = {"Bi-week", 
			"Hourly", 
			"Monthly"};
	private Double amount;
	private String date;
	private String source;
	private String frequency;
	//todo add in startDate of this income to allow auto-compute accumulated income as time goes along.
//	private String startDate;
	
	public IncomeEntry(Double inAmount, String inSource, int inFreq, String inDate)
	{
		setDate(inDate);
		setAmount(inAmount);
		setSource(inSource);
		setFrequency(inFreq);
	}
	
	public IncomeEntry(Double inAmount, String inSource, String inFreq, String inDate)
	{
		setDate(inDate);
		setAmount(inAmount);
		setSource(inSource);
		setFrequency(inFreq);
	}
	
	public void setAmount(Double inAmount)
	{
		amount = inAmount;
	}
	
	public void setSource(String inSource)
	{
		source = inSource;
	}
	
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	public void setFrequency(String inFreq)
	{
		frequency = inFreq;
	}
	
	public void setFrequency(int inFreq)
	{
		if (inFreq < 0 || inFreq >= INCOME_FREQUENCY.length)
		{
			//todo change it to INVALID_EXCEPTION.
			System.out.printf("invalid category: %d\n", inFreq);
			return;
		}
		
		frequency = INCOME_FREQUENCY[inFreq];
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
		return frequency;
	}
	
	public String getDate() 
	{
		return date;
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
