package application;


//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.URL;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Locale;

import java.util.Iterator;

import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainController implements Initializable{
	
	@FXML private ScrollPane scrpn_main;
	@FXML private Pane pn_main, pn_input, pn_log;
	@FXML private Button btn_main, btn_input, btn_log;
	
	//////////Main Page //////////
	
	@FXML LineChart<String, Number> linechart;
	@FXML BarChart<String, Number> barchart;
	@FXML PieChart piechart;
	@FXML Label status;
	
	//////////Main Page //////////
	
	////////// Input Page //////////
	// Input page
	@FXML public ComboBox<String> expenseCombobox;
	@FXML public ComboBox<String> incopmeCombobox;
//	@FXML public ListView<String> expenseListView;
	@FXML public Button addExpenseList;

	@FXML public TextField expenseAmount, expenseDate, incomeAmount, incomeSource, searchField1, searchField2, barChartInput;
	@FXML public TableView<ExpenseEntry> expenseInputTable;
	@FXML public TableColumn<ExpenseEntry, Double> expenseTableAmountCol;
	@FXML public TableColumn<ExpenseEntry, String> expenseTableDateCol;
	@FXML public TableColumn<ExpenseEntry, String> expenseTableCategoriesCol;
	@FXML public TableView<IncomeEntry> incomeInputTable;
	@FXML public TableColumn<IncomeEntry, Double> incomeTableAmountCol;
	@FXML public TableColumn<IncomeEntry, String> incomeTableSourceCol;
	@FXML public TableColumn<IncomeEntry, String> incomeTableFrequencyCol;
	@FXML public TableColumn<IncomeEntry, String> incomeTableDateCol;

	@FXML public ImageView inputIcon;
	@FXML public ImageView logIcon;
	@FXML public ImageView mainIcon;
	@FXML public ImageView incomeAddIcon;
	@FXML public ImageView expenseAddIcon;
	@FXML public ImageView searchIcon;
	//////////Input Page //////////
	
	//////////Log Page //////////
	// Tables in Log page

	@FXML public TableView<ExpenseEntry> expenseLogTable;
	@FXML public TableView<IncomeEntry> incomeLogTable;
	@FXML private TextField balanceField;


	//////////Log Page //////////
	// Image
	Image inputImage = new Image("/img/input.png");
	Image logImage = new Image("/img/log.png");
	Image mainImage = new Image("/img/main.png");
	Image addImage = new Image("/img/add.png");
	Image searchImage = new Image("/img/search.png");
	
	
	
	//income file 
	private String inFile = "income.csv";
	private String expFile = "expence.csv";
	
	// date picker
	@FXML public DatePicker expenseDatePicker = new DatePicker();
	@FXML public DatePicker incomeDatePicker = new DatePicker();
	@FXML public DatePicker datePickLogStart = new DatePicker();
	@FXML public DatePicker datePickLogEnd = new DatePicker();
	

	
//	private String initTitle = String.format("%-30s%-40s%s", "Amount", "Date", "Categories");
	ObservableList<String> expenseCtg = FXCollections.observableArrayList("Housing", 
			"Transportation", 
			"Food", 
			"Utilities", 
			"Clothing", 
			"Medical", 
			"Insurance", 
			"Persional", 
			"Education", 
			"Entertainment");
	ObservableList<String> incomeFreq = FXCollections.observableArrayList("Bi-week", 
																			"Hourly", 
																			"Monthly");
	
//	ObservableList<ExpenseEntry> expenseTableViewList = FXCollections.observableArrayList();
//	ObservableList<IncomeEntry> incomeTableViewList = FXCollections.observableArrayList();
	
	ObservableList<ExpenseEntry> expenseList = FXCollections.observableArrayList();
	ObservableList<IncomeEntry> incomeList = FXCollections.observableArrayList();
	double balance;


//	ObservableList<expenseLog> expenseLogTableViewList = FXCollections.observableArrayList();
//	ObservableList<incomeLog> incomeLogTableViewList = FXCollections.observableArrayList();

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inputIcon.setImage(inputImage);
		logIcon.setImage(logImage);
		mainIcon.setImage(mainImage);
		incomeAddIcon.setImage(addImage);
		expenseAddIcon.setImage(addImage);
		searchIcon.setImage(searchImage);
		
		expenseCombobox.setItems(expenseCtg);
		incopmeCombobox.setItems(incomeFreq);
//		expenseListView.setItems(initListViewTitle);
		
		expenseTableAmountCol.setCellValueFactory(new PropertyValueFactory<ExpenseEntry, Double>("Amount"));
		expenseTableDateCol.setCellValueFactory(new PropertyValueFactory<ExpenseEntry, String>("Date"));
		expenseTableCategoriesCol.setCellValueFactory(new PropertyValueFactory<ExpenseEntry, String>("Category"));
//		expenseInputTable.setItems(expenseTableViewList);
		

		incomeTableAmountCol.setCellValueFactory(new PropertyValueFactory<IncomeEntry, Double>("Amount"));
		incomeTableSourceCol.setCellValueFactory(new PropertyValueFactory<IncomeEntry, String>("Source"));
		incomeTableFrequencyCol.setCellValueFactory(new PropertyValueFactory<IncomeEntry, String>("Frequency"));
		incomeTableDateCol.setCellValueFactory(new PropertyValueFactory<IncomeEntry, String>("Date"));
//		incomeInputTable.setItems(incomeTableViewList);
		
		//update log page
		incomeList = FileProcess.readIncomeFromFile(inFile);	
		expenseList = FileProcess.readExpenseFromFile(expFile);
		
		populateLogTable(expenseList, incomeList);
		updateLineChart();
		updatePieChart();
		updateBarChart(1);
	}
	
	
	public void populateLogTable(ObservableList<ExpenseEntry> expList, ObservableList<IncomeEntry> incList)
	{
		expenseLogTable.getColumns().clear();
		TableColumn<ExpenseEntry, Double> expAmountColumn = new TableColumn<>("Amount");
		expAmountColumn.setMinWidth(100);
		expAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		TableColumn<ExpenseEntry, Double> expDateColumn = new TableColumn<>("Date");
		expDateColumn.setMinWidth(100);
		expDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<ExpenseEntry, Double> expCatColumn = new TableColumn<>("Category");
		expCatColumn.setMinWidth(100);
		expCatColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		
		expenseLogTable.setItems(expList);
		expenseLogTable.getColumns().add(expAmountColumn);
		expenseLogTable.getColumns().add(expDateColumn);
		expenseLogTable.getColumns().add(expCatColumn);
		
		
		//populate income table
		incomeLogTable.getColumns().clear();
		TableColumn<IncomeEntry, Double> incomeAmountColumn = new TableColumn<>("Amount");
		incomeAmountColumn.setMinWidth(40);
		incomeAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		TableColumn<IncomeEntry, String> incomeSourceColumn = new TableColumn<>("Source");
		incomeSourceColumn.setMinWidth(40);
		incomeSourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
		
		TableColumn<IncomeEntry, String> incomeFreqColumn = new TableColumn<>("Frequency");
		incomeFreqColumn.setMinWidth(40);
		incomeFreqColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
		
		TableColumn<IncomeEntry, String> incomeDateColumn = new TableColumn<>("Date");
		incomeDateColumn.setMinWidth(40);
		incomeDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		incomeLogTable.setItems(incList);
		incomeLogTable.getColumns().add(incomeAmountColumn);
		incomeLogTable.getColumns().add(incomeSourceColumn);
		incomeLogTable.getColumns().add(incomeFreqColumn);
		incomeLogTable.getColumns().add(incomeDateColumn);
		
		//display the balanceTextField
		balance = DataProcess.balance(expList, incList);
		balanceField.setText(Double.toString(balance));
	}
	
	public void updatePieChart()
	{
		ObservableList<Data> list = FXCollections.observableArrayList();
		
		double[] ctgValues = DataProcess.procesExpensePieChart(expenseList, 2018);
		for (int i=0; i< ctgValues.length; i++)
		{
			list.add(new PieChart.Data(ExpenseEntry.EXPENSE_CATEGORY[i], (int)ctgValues[i]));
		}
		
		piechart.setData(list);
		piechart.setLegendSide(Side.LEFT);
		
//		status = new Label("");

//		status.setTextFill(Color.BLACK);
//		status.setStyle("-fx-font: 24 arial;");
//		
		
		
		
		for (final PieChart.Data data : piechart.getData()) {
			Node node = data.getNode();
			node.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
//					status.setTranslateX(event.getSceneX());
//					status.setTranslateY(event.getSceneY());
					
					status.setText(String.valueOf(data.getPieValue()) + "%"); 
					status.setVisible(true);
					node.setEffect(new Glow());
					System.out.println(String.valueOf(data.getPieValue() ) + "%");
					
//					status.setText(String.valueOf(data.getPieValue() ) + "%");
				}
			});
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateLineChart()
	{
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		double[] monthlyIncome = DataProcess.procesIncomeByMonth(incomeList, 2018);
		for (int i=0; i< monthlyIncome.length; i++)
		{
			series.getData().add(new XYChart.Data<String, Number>( DataProcess.MONTHES[i], monthlyIncome[i]) );
		}
		series.setName("2018 Monthly Income"); // here is to set legend
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		double[] monthlyExpense = DataProcess.procesExpenseByMonth(expenseList, 2018);
		for (int i=0; i< monthlyExpense.length; i++)
		{
			series1.getData().add(new XYChart.Data<String, Number>( DataProcess.MONTHES[i], monthlyExpense[i]) );
		}
		series1.setName("2018 Monthly Expense"); // here is to set legend
		
		linechart.getData().clear();
		linechart.getData().addAll(series, series1);
		for (final XYChart.Data<String, Number> data : series.getData()) {
			Tooltip.install(data.getNode(), new Tooltip("X: " + data.getXValue() + " Y: " + String.valueOf(data.getYValue())));
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateBarChart(int inputMonth)
	{
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		double[] ctg = DataProcess.procesExpenseBarChart(expenseList, 2018, inputMonth);
		
		for (int i=0; i< ctg.length; i++)
		{
			series.getData().add(new XYChart.Data<String, Number>( ExpenseEntry.EXPENSE_CATEGORY[i], ctg[i]) );
		}
		series.setName("2018 " + DataProcess.MONTHES[inputMonth - 1] + " Expense"); // here is to set legend
		
		barchart.getData().clear();
		barchart.getData().addAll(series);
//		for (final XYChart.Data<String, Number> data : series.getData()) {
//			Tooltip.install(data.getNode(), new Tooltip("X: " + data.getXValue() + " Y: " + String.valueOf(data.getYValue())));
//		}
	}
	
	@FXML
	public void searchBtnActionChart(ActionEvent event) throws Exception {
		String startDate = searchField1.getText();
		String endDate = searchField2.getText();
		
		
		searchField1.clear();
		searchField2.clear();
	}
	
	@FXML
	public void searchCategoryBtnActionChart(ActionEvent event) throws Exception {
		String barMonth = barChartInput.getText();
		int inputMonth = Integer.parseInt(barMonth);
		if (inputMonth >= 1 && inputMonth <= 12)
			updateBarChart(inputMonth);
		barChartInput.clear();
	}
	
	@FXML
	public void searchBtnActionLog(ActionEvent event) throws Exception {
		if (datePickLogStart.getValue() != null && datePickLogEnd.getValue() != null) {
			String startDate = datePickLogStart.getValue().toString();
			String endDate = datePickLogEnd.getValue().toString();
			
			ObservableList<ExpenseEntry> filteredExpList = DataProcess.filterExpenseByDateRange(expenseList, startDate, endDate);
			ObservableList<IncomeEntry> filteredIncList = DataProcess.filterIncomeByDateRange(incomeList, startDate, endDate);
			
			populateLogTable(filteredExpList, filteredIncList);
			
			System.out.println(startDate);
			System.out.println(endDate);
		}
		
	}
	
	@FXML
	public void expenseAddBtn(ActionEvent event) throws Exception {
		addToList("addExpense");
		expenseAmount.setText("");
//		expenseDatePick
//		expenseDate.setText("");
		expenseCombobox.setValue("Select Category");
	}
	
	@FXML
	public void incomeAddBtn(ActionEvent event) throws Exception {
		addToList("addIncome");
		incomeAmount.setText("");
		incomeSource.setText("");
		incopmeCombobox.setValue("Select Frequency");
	}

	public void addToList(String addCommand) throws Exception {
		if (addCommand.equals("addIncome")) {
			String incomeAmountInput = incomeAmount.getText();
			String incomeSourceInput = incomeSource.getText();
			String incomeFrequencyInput = incopmeCombobox.getValue(); 
			String incomeDateInput = "";

			
			if (incomeAmountInput == null || incomeAmountInput == "" 
					|| incomeDatePicker.getValue() == null
					|| incomeSourceInput == null || incomeSourceInput == ""
					|| incomeFrequencyInput == null || incomeFrequencyInput == "") {
				alertHelper();
				return;
			} else {
			incomeDateInput = incomeDatePicker.getValue().toString();
			IncomeEntry newIncome = new IncomeEntry( Double.valueOf(incomeAmountInput), incomeSourceInput, incomeFrequencyInput, incomeDateInput);
			incomeList.add(newIncome);
			incomeInputTable.getItems().add(newIncome);
			
			String s= String.format("%s,%s,%s,%s\n", incomeAmountInput, incomeSourceInput, incomeFrequencyInput, incomeDateInput);
//System.out.println(s);
		    FileProcess.writetoFile(inFile, s, true);  
			}

		} else if (addCommand.equals("addExpense")) {
			String expenseAmountInput = expenseAmount.getText();
			String expenseDateInput = "";
			String expenseCategoryInput = expenseCombobox.getValue();

			
			if (expenseAmount.getText() == null || expenseAmount.getText().equals("") 
					|| expenseDatePicker.getValue() == null
					|| expenseCombobox.getValue() == null || expenseCombobox.getValue().equals("")) {
				alertHelper();
				return;
			} else {
				expenseDateInput = expenseDatePicker.getValue().toString();
			
			ExpenseEntry newExpense = new ExpenseEntry( Double.valueOf(expenseAmountInput), expenseDateInput, expenseCategoryInput );
			expenseList.add(newExpense);
			expenseInputTable.getItems().add(newExpense);
			
			String s= String.format("%s,%s,%s\n", expenseAmountInput, expenseDateInput, expenseCategoryInput);
		    FileProcess.writetoFile(expFile, s, true);
			}

		}
		
	}
	

	private void alertHelper() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Wrong Input!");

		alert.showAndWait();
	}

	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		if (event.getSource() == btn_main) {
			scrpn_main.toFront();
			updateLineChart();
			updatePieChart();
//			updateBarChart();
		} else if (event.getSource() == btn_input) {
			pn_input.toFront();
		} else if (event.getSource() == btn_log) {
			// read and display the file here
//			try {
////				incomeLogTableViewList.clear();
//				expenseLogTableViewList.clear();
//				addToLogInList(readFile(inFile));
//				addToLogExpList(readFile(expFile));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			readFile(expFile);
			pn_log.toFront();
		}
	}
	
//	public void addToLogInList(ArrayList<String> dataInput) throws Exception {
//		for (int i = 1; i < dataInput.size(); i++) {
//			String[] eachLine = dataInput.get(i).split(",");
//			int incomeAmountLog = 0;
//			String incomeSourceLog = "";
//			String incomeFrequencyLog = "";
//			
//			incomeAmountLog = Integer.parseInt(eachLine[0]);
//			incomeSourceLog = eachLine[1];
//			incomeFrequencyLog= eachLine[2];
//			
//			
//			incomeLogTableViewList.add(new incomeLog(incomeAmountLog, incomeSourceLog, incomeFrequencyLog));
//		}
//	}
//	
//	public void addToLogExpList(ArrayList<String> dataInput) throws Exception {
//		for (int i = 1; i < dataInput.size(); i++) {
//			String[] eachLine = dataInput.get(i).split(",");
//			int expenseAmountLog = 0;
//			String expenseDateLog = "";
//			String expenseCategoriesLog = "";
//			
//			expenseAmountLog = Integer.parseInt(eachLine[0]);
//			expenseDateLog = eachLine[1];
//			expenseCategoriesLog= eachLine[2];
//			
//			
//			expenseLogTableViewList.add(new expenseLog(expenseAmountLog, expenseDateLog, expenseCategoriesLog));
//		}
//	}
	
//	public ArrayList<String> readFile(String fileStrInput)  {
//		ArrayList<String> res = new ArrayList<>();
//		
//	    FileInputStream fstream = null;
//	    try {
//	        File inFile = new File(fileStrInput);
//	        fstream = new FileInputStream(inFile);
//	        // Get the object of DataInputStream
//	        DataInputStream in = new DataInputStream(fstream);
//	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//	        // Do something with the stream
//	        String curLine = br.readLine();
//	        while (curLine != null && curLine != "") {
////		        System.out.println(curLine); 
//	        	res.add(curLine);
//		        curLine = br.readLine();
//	        }
//	        
//	    } catch (FileNotFoundException ex) {
//	        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//	    } catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//	        try {
//	            // If you don't need the stream open after the constructor
//	            // else, remove that block but don't forget to close the 
//	            // stream after you are done with it
//	            fstream.close();
//	        } catch (IOException ex) {
//	            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//	        }
//	    } 
//	    
//	    return res;
//	} 
	/**
	 * -fx-background-color  transparent
	 * 
	 * 
	 */
	
	
}
