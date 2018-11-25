package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainController implements Initializable{
	
	@FXML private ScrollPane scrpn_main;
	@FXML private Pane pn_main, pn_input, pn_log;
	@FXML private Button btn_main, btn_input, btn_log;
	
	//////////Main Page //////////
	
	@FXML LineChart<String, Number> linechart;
	
	
	//////////Main Page //////////
	
	////////// Input Page //////////
	// Input page
	@FXML public ComboBox<String> expenseCombobox;
	@FXML public ComboBox<String> incopmeCombobox;
//	@FXML public ListView<String> expenseListView;
	@FXML public Button addExpenseList;
	@FXML public TextField expenseAmount, expenseDate, incomeAmount, incomeSource;
	@FXML public TableView<expenseInput> expenseInputTable;
	@FXML public TableColumn<expenseInput, Integer> expenseTableAmountCol;
	@FXML public TableColumn<expenseInput, String> expenseTableDateCol;
	@FXML public TableColumn<expenseInput, String> expenseTableCategoriesCol;
	@FXML public TableView<incomeInput> incomeInputTable;
	@FXML public TableColumn<incomeInput, Integer> incomeTableAmountCol;
	@FXML public TableColumn<incomeInput, String> incomeTableSourceCol;
	@FXML public TableColumn<incomeInput, String> incomeTableFrequencyCol;
	@FXML public ImageView inputIcon;
	@FXML public ImageView logIcon;
	@FXML public ImageView mainIcon;
	@FXML public ImageView incomeAddIcon;
	@FXML public ImageView expenseAddIcon;
	//////////Input Page //////////
	
	//////////Log Page //////////
	// Tables in Log page
	@FXML public TableView<expenseInput> expenseLogTable;
	@FXML public TableColumn<expenseInput, Integer> expenseLogTableAmountCol;
	@FXML public TableColumn<expenseInput, String> expenseLogTableDateCol;
	@FXML public TableColumn<expenseInput, String> expenseLogTableCategoriesCol;
	@FXML public TableView<incomeInput> incomeLogTable;
	@FXML public TableColumn<incomeInput, Integer> incomeLogTableAmountCol;
	@FXML public TableColumn<incomeInput, String> incomeLogTableSourceCol;
	@FXML public TableColumn<incomeInput, String> incomeLogTableFrequencyCol;
	//////////Log Page //////////
	// Image
	Image inputImage = new Image("/img/input.png");
	Image logImage = new Image("/img/log.png");
	Image mainImage = new Image("/img/main.png");
	Image addImage = new Image("/img/add.png");
	
	//income file 
	 private String inFile = "income.csv";
	 private String expFile = "expence.csv";
	 
	 private ArrayList<IncomeEntry> incomeEntries;
	 private ArrayList<ExpenseEntry> expenseEntries;
		
	
//	private String initTitle = String.format("%-30s%-40s%s", "Amount", "Date", "Categories");
	ObservableList<String> expenseList = FXCollections.observableArrayList("Housing", 
			"Transportation", 
			"Food", 
			"Utilities", 
			"Clothing", 
			"Medical", 
			"Insurance", 
			"Persional", 
			"Education", 
			"Entertainment");
	ObservableList<String> incomeList = FXCollections.observableArrayList("Bi-week", 
																			"Hourly", 
																			"Monthly");
	
	ObservableList<expenseInput> expenseTableViewList = FXCollections.observableArrayList();
	ObservableList<incomeInput> incomeTableViewList = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inputIcon.setImage(inputImage);
		logIcon.setImage(logImage);
		mainIcon.setImage(mainImage);
		incomeAddIcon.setImage(addImage);
		expenseAddIcon.setImage(addImage);
		
		expenseCombobox.setItems(expenseList);
		incopmeCombobox.setItems(incomeList);
//		expenseListView.setItems(initListViewTitle);
		
		expenseTableAmountCol.setCellValueFactory(new PropertyValueFactory<expenseInput, Integer>("expenseTableAmountCol"));
		expenseTableDateCol.setCellValueFactory(new PropertyValueFactory<expenseInput, String>("expenseTableDateCol"));
		expenseTableCategoriesCol.setCellValueFactory(new PropertyValueFactory<expenseInput, String>("expenseTableCategoriesCol"));
		expenseInputTable.setItems(expenseTableViewList);
		
		incomeTableAmountCol.setCellValueFactory(new PropertyValueFactory<incomeInput, Integer>("incomeTableAmountCol"));
		incomeTableSourceCol.setCellValueFactory(new PropertyValueFactory<incomeInput, String>("incomeTableSourceCol"));
		incomeTableFrequencyCol.setCellValueFactory(new PropertyValueFactory<incomeInput, String>("incomeTableFrequencyCol"));
		incomeInputTable.setItems(incomeTableViewList);
		
		incomeEntries = FileProcess.readIncomeFromFile(inFile);	
		expenseEntries = FileProcess.readExpenseFromFile(expFile);

	}
	
	@SuppressWarnings("unchecked")
	public void updateLineChart()
	{
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		Iterator<IncomeEntry> inItr = incomeEntries.iterator();

		int count = 0;
		while(inItr.hasNext())
		{
			IncomeEntry income = inItr.next();
			String sCount = String.format("%d", count);
			series.getData().add(new XYChart.Data<String, Number>(sCount, income.getAmount()));
			count++;
		}
//		series.getData().add(new XYChart.Data<String, Number>("1", 1234.1));
		series.setName("Monthly Income"); // here is to set legend
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		Iterator<ExpenseEntry> expItr = expenseEntries.iterator();
		
		count = 0;
		while(expItr.hasNext())
		{
			ExpenseEntry expense = expItr.next();
			String sCount = String.format("%d", count);
			series1.getData().add(new XYChart.Data<String, Number>(sCount, expense.getAmount()));
			count++;
		}
//		series1.getData().add(new XYChart.Data<String, Number>("1",234));
		series1.setName("Monthly Expense"); // here is to set legend
		
		linechart.getData().addAll(series, series1);
		for (final XYChart.Data<String, Number> data : series.getData()) {
			Tooltip.install(data.getNode(), new Tooltip("X: " + data.getXValue() + " Y: " + String.valueOf(data.getYValue())));
		}
	}
	

	
	
	@FXML
	public void expenseAddBtn(ActionEvent event) throws Exception {
		addToList("addExpense");
		expenseAmount.setText("");
		expenseDate.setText("");
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
			incomeTableViewList.add(new incomeInput(Integer.valueOf(incomeAmountInput), incomeSourceInput, incomeFrequencyInput));
			
			String s= String.format("%s,%s,%s\n", incomeAmountInput, incomeSourceInput, incomeFrequencyInput);
			   FileProcess.writetoFile(inFile, s, true);
			   
		} else if (addCommand.equals("addExpense")) {
			String expenseAmountInput = expenseAmount.getText();
			String expenseDateInput = expenseDate.getText();
			String expenseCategoryInput = expenseCombobox.getValue();
			expenseTableViewList.add(new expenseInput(Integer.valueOf(expenseAmountInput), expenseDateInput, expenseCategoryInput));
			
			String s= String.format("%s,%s,%s\n", expenseAmountInput, expenseDateInput, expenseCategoryInput);
			   FileProcess.writetoFile(expFile, s, true);

		}
		
	}
	
//	public void addToList() throws Exception {
//		String incomeAmountInput = incomeAmount.getText();
//		String incomeSourceInput = incomeSource.getText();
//		String incomeFrequencyInput = incopmeCombobox.getValue(); 
//		
//		incomeTableViewList.add(new incomeInput(Integer.valueOf(incomeAmountInput), incomeSourceInput, incomeFrequencyInput));
//	}
	
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		if (event.getSource() == btn_main) {
			scrpn_main.toFront();
			updateLineChart();
		} else if (event.getSource() == btn_input) {
			pn_input.toFront();
		} else if (event.getSource() == btn_log) {
			pn_log.toFront();
		}
	}
	
	
}
