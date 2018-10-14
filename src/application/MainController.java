package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
