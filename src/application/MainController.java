package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
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
	@FXML public TextField expenseAmount, incomeAmount, incomeSource;
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
	@FXML public TableView<expenseLog> expenseLogTable;
	@FXML public TableColumn<expenseLog, Integer> expenseLogTableAmountCol;
	@FXML public TableColumn<expenseLog, String> expenseLogTableDateCol;
	@FXML public TableColumn<expenseLog, String> expenseLogTableCategoriesCol;
	@FXML public TableView<incomeLog> incomeLogTable;
	@FXML public TableColumn<incomeLog, Integer> incomeLogTableAmountCol;
	@FXML public TableColumn<incomeLog, String> incomeLogTableSourceCol;
	@FXML public TableColumn<incomeLog, String> incomeLogTableFrequencyCol;
	//////////Log Page //////////
	// Image
	Image inputImage = new Image("/img/input.png");
	Image logImage = new Image("/img/log.png");
	Image mainImage = new Image("/img/main.png");
	Image addImage = new Image("/img/add.png");
	
	
	
	//income file 
	private String inFile = "income.csv";
	private String expFile = "expence.csv";
	
	// date picker
	@FXML public DatePicker expenseDatePicker = new DatePicker();
	
	
	
	private String initTitle = String.format("%-30s%-40s%s", "Amount", "Date", "Categories");
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

	ObservableList<expenseLog> expenseLogTableViewList = FXCollections.observableArrayList();
	ObservableList<incomeLog> incomeLogTableViewList = FXCollections.observableArrayList();

	
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
		
		expenseLogTableAmountCol.setCellValueFactory(new PropertyValueFactory<expenseLog, Integer>("expenseLogTableAmountCol"));
		expenseLogTableDateCol.setCellValueFactory(new PropertyValueFactory<expenseLog, String>("expenseLogTableDateCol"));
		expenseLogTableCategoriesCol.setCellValueFactory(new PropertyValueFactory<expenseLog, String>("expenseLogTableCategoriesCol"));
		expenseLogTable.setItems(expenseLogTableViewList);
		
		incomeTableAmountCol.setCellValueFactory(new PropertyValueFactory<incomeInput, Integer>("incomeTableAmountCol"));
		incomeTableSourceCol.setCellValueFactory(new PropertyValueFactory<incomeInput, String>("incomeTableSourceCol"));
		incomeTableFrequencyCol.setCellValueFactory(new PropertyValueFactory<incomeInput, String>("incomeTableFrequencyCol"));
		incomeInputTable.setItems(incomeTableViewList);
		
		incomeLogTableAmountCol.setCellValueFactory(new PropertyValueFactory<incomeLog, Integer>("incomeLogTableAmountCol"));
		incomeLogTableSourceCol.setCellValueFactory(new PropertyValueFactory<incomeLog, String>("incomeLogTableSourceCol"));
		incomeLogTableFrequencyCol.setCellValueFactory(new PropertyValueFactory<incomeLog, String>("incomeLogTableFrequencyCol"));
		incomeLogTable.setItems(incomeLogTableViewList);

		
		writetoFile(inFile, "amount, source, frequency\n", false);
		writetoFile(expFile, "amount, date, category\n", false);
		
	}
	
	public void writetoFile(String fname, String s, boolean append)
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
			
			if (incomeAmountInput == null || incomeAmountInput == "" 
					|| incomeSourceInput == null || incomeSourceInput == ""
					|| incomeFrequencyInput == null || incomeFrequencyInput == "") {
				alertHelper();
				return;
			} else {
				incomeTableViewList.add(new incomeInput(Integer.valueOf(incomeAmountInput), incomeSourceInput, incomeFrequencyInput));
				
				String s= String.format("%s,%s,%s\n", incomeAmountInput, incomeSourceInput, incomeFrequencyInput);
				writetoFile(inFile, s, true);
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
				expenseTableViewList.add(new expenseInput(Integer.valueOf(expenseAmountInput), expenseDateInput, expenseCategoryInput));
				
				String s= String.format("%s,%s,%s\n", expenseAmountInput, expenseDateInput, expenseCategoryInput);
				writetoFile(expFile, s, true);
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
		} else if (event.getSource() == btn_input) {
			pn_input.toFront();
		} else if (event.getSource() == btn_log) {
			// read and display the file here
			try {
				incomeLogTableViewList.clear();
				expenseLogTableViewList.clear();
				addToLogInList(readFile(inFile));
				addToLogExpList(readFile(expFile));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			readFile(expFile);
			pn_log.toFront();
		}
	}
	
	public void addToLogInList(ArrayList<String> dataInput) throws Exception {
		for (int i = 1; i < dataInput.size(); i++) {
			String[] eachLine = dataInput.get(i).split(",");
			int incomeAmountLog = 0;
			String incomeSourceLog = "";
			String incomeFrequencyLog = "";
			
			incomeAmountLog = Integer.parseInt(eachLine[0]);
			incomeSourceLog = eachLine[1];
			incomeFrequencyLog= eachLine[2];
			
			
			incomeLogTableViewList.add(new incomeLog(incomeAmountLog, incomeSourceLog, incomeFrequencyLog));
		}
	}
	
	public void addToLogExpList(ArrayList<String> dataInput) throws Exception {
		for (int i = 1; i < dataInput.size(); i++) {
			String[] eachLine = dataInput.get(i).split(",");
			int expenseAmountLog = 0;
			String expenseDateLog = "";
			String expenseCategoriesLog = "";
			
			expenseAmountLog = Integer.parseInt(eachLine[0]);
			expenseDateLog = eachLine[1];
			expenseCategoriesLog= eachLine[2];
			
			
			expenseLogTableViewList.add(new expenseLog(expenseAmountLog, expenseDateLog, expenseCategoriesLog));
		}
	}
	
	public ArrayList<String> readFile(String fileStrInput)  {
		ArrayList<String> res = new ArrayList<>();
		
	    FileInputStream fstream = null;
	    try {
	        File inFile = new File(fileStrInput);
	        fstream = new FileInputStream(inFile);
	        // Get the object of DataInputStream
	        DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        // Do something with the stream
	        String curLine = br.readLine();
	        while (curLine != null && curLine != "") {
//		        System.out.println(curLine); 
	        	res.add(curLine);
		        curLine = br.readLine();
	        }
	        
	    } catch (FileNotFoundException ex) {
	        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
	            // If you don't need the stream open after the constructor
	            // else, remove that block but don't forget to close the 
	            // stream after you are done with it
	            fstream.close();
	        } catch (IOException ex) {
	            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	        }
	    } 
	    
	    return res;
	} 
	
	
}
