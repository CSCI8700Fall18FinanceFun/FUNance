package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.opencsv.CSVReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import com.opencsv.CSVReader; 
import javafx.application.Application;
import javafx.scene.chart.ScatterChart;



public class MainController implements Initializable{
	
	@FXML private ScrollPane scrpn_main;
	@FXML private Pane pn_main, pn_input, pn_log;
	@FXML private Button btn_main, btn_input, btn_log;
	
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
	@FXML public CategoryAxis linex;
	@FXML public NumberAxis	liney;
	@FXML public LineChart<?,?> lineChart;
	@FXML public PieChart pieChart;
	@FXML public BarChart<?,?> barChart;
		
	// Image
	Image inputImage = new Image("/img/input.png");
	Image logImage = new Image("/img/log.png");
	Image mainImage = new Image("/img/main.png");
	Image addImage = new Image("/img/add.png");
	
	private String initTitle = String.format("%-30s%-40s%s", "Amount", "Date", "Categories");
	ObservableList<String> expenseList = FXCollections.observableArrayList("Housing", 
			"Transportation", 
			"Food", 
			"Utilities", 
			"Clothing", 
			"Medical", 
			"Insurance", 
			"Personal", 
			"Education", 
			"Entertainment");
	ObservableList<String> incomeList = FXCollections.observableArrayList("Bi-week", 
																			"Hourly", 
																			"Monthly");
	
	ObservableList<expenseInput> expenseTableViewList = FXCollections.observableArrayList();
	ObservableList<incomeInput> incomeTableViewList = FXCollections.observableArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
		// Pie Chart
		ObservableList<PieChart.Data> pieChartData =
	            FXCollections.observableArrayList(
	            new PieChart.Data("Housing", 25),
	            new PieChart.Data("Food", 20),
	            new PieChart.Data("Personal", 15),
	            new PieChart.Data("Utilities", 10),
	            new PieChart.Data("Entertainment", 15),
	            new PieChart.Data("Insurance", 10),
	            new PieChart.Data("Transportation", 5));

		pieChart.setData(pieChartData);

		// Line Chart
		XYChart.Series seriesB = new XYChart.Series();
	   
		seriesB.setName("Balance Total");	

		seriesB.getData().add(new XYChart.Data<String, Integer>("Jan 2018", 2000-1000));
		seriesB.getData().add(new XYChart.Data<String, Integer>("Feb 2018", 2000-1000+2500-2500));
		seriesB.getData().add(new XYChart.Data<String, Integer>("Mar 2018", 2000-1000+2500-2500+2000-700));
		seriesB.getData().add(new XYChart.Data<String, Integer>("Apr 2018", 2000-1000+2500-2500+2000-700+3000-5050));
		seriesB.getData().add(new XYChart.Data<String, Integer>("May 2018", 2000-1000+2500-2500+2000-700+3000-5050+2500-800));

		lineChart.getData().addAll(seriesB);

//		try {
//			CSVReader dataReader = new CSVReader(new FileReader("C:/Users/casey/Documents/income.csv"));
//			String[] nextLine;
//			while ((nextLine = dataReader.readNext()) != null) {
//				int amount = Integer.parseInt(nextLine[0]);
//				String source = String.valueOf(nextLine[1]);
//				String frequency = String.valueOf(nextLine[2]);
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
		
		// Bar Chart
		XYChart.Series seriesE = new XYChart.Series();
		XYChart.Series seriesI = new XYChart.Series();
		
		seriesE.setName("Expense Total");
		seriesI.setName("Income Total");
		
		seriesE.getData().add(new XYChart.Data<String, Integer>("Jan 2018", 1000));
		seriesE.getData().add(new XYChart.Data<String, Integer>("Feb 2018", 2500));
		seriesE.getData().add(new XYChart.Data<String, Integer>("Mar 2018", 700));
		seriesE.getData().add(new XYChart.Data<String, Integer>("Apr 2018", 5050));
		seriesE.getData().add(new XYChart.Data<String, Integer>("May 2018", 800));
		
		seriesI.getData().add(new XYChart.Data<String, Integer>("Jan 2018", 2000));
		seriesI.getData().add(new XYChart.Data<String, Integer>("Feb 2018", 2000));
		seriesI.getData().add(new XYChart.Data<String, Integer>("Mar 2018", 2500));
		seriesI.getData().add(new XYChart.Data<String, Integer>("Apr 2018", 2500));
		seriesI.getData().add(new XYChart.Data<String, Integer>("May 2018", 2500));
		
		barChart.getData().addAll(seriesE,seriesI);
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
		} else if (addCommand.equals("addExpense")) {
			String expenseAmountInput = expenseAmount.getText();
			String expenseDateInput = expenseDate.getText();
			String expenseCategoryInput = expenseCombobox.getValue();
			expenseTableViewList.add(new expenseInput(Integer.valueOf(expenseAmountInput), expenseDateInput, expenseCategoryInput));
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
		} else if (event.getSource() == btn_input) {
			pn_input.toFront();
		} else if (event.getSource() == btn_log) {
			pn_log.toFront();
		}
	}	
}
