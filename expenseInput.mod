
mod MYEXPENSE* {
	sort Sector
	imports DOUBLE, STRING
	[file] -- csv file with records
	[List] -- list of expense types

	-- Defines afunction where Amount, Date can be input and where Exepense Type can selected from a drop-down list. By clicking the plus button, the input info should be stored in a file. 
	-- Operations are clickPluscons, inputAmounthead, inputDate, clickTypeList and selectType.
	
	-- signatures
	op clickPlus:  record -> file
	op clickPlus:  amount, data, type-> file
	op inputAmount: record amount-> record
	op inputDate: record date -> record
	op clickTypeList: List -> List
	op selectType: record List type -> record

	-- axioms 
	var a : Double
	var d, t: String
	var L : List
	eq clickPlus (a, d, t) = clickPlus (selectType(inputDate(inputAmount (record, a), date), clickTypeList(L), t))
}
