package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBConnect;
import application.DynamicTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private Pane pane4;
	@FXML
	private Pane pane5;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtClientID;
	@FXML
	private TextField txtInventoryID;
	@FXML
	private TextField txtQuantity;
	@FXML
	private TextField txtDeleteInventoryID;
	@FXML
	private TextField txtSkuNumber;
	@FXML
	private TextField txtQuantity2;


	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public AdminController() {
		conn = new DBConnect();
	}

	public void viewAccounts() {

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		d.buildData("Select iid,sku_number,quantity from g_halsey_inventory");
	}

	public void updateRec() {

		pane4.setVisible(false);
		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);
		pane5.setVisible(false);

	}

	public void deleteRec() {

		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
		pane4.setVisible(false);
		pane5.setVisible(false);
	}
	
	public void deleteInvRec() {
		
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(true);
		pane5.setVisible(false);
	}

	public void addBankRec() {

		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);
		pane4.setVisible(false);
		pane5.setVisible(false);

	}
	
	public void addInvRec() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(false);
		pane5.setVisible(true);
	}

	public void submitBank() {

		// INSERT INTO CLIENTS TABLE
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "insert into g_halsey_clients(name,address) values ('" + txtName.getText() + "','" + txtAddress.getText()
					+ "')";
			stmt.executeUpdate(sql);
			System.out.println("Client record created");
 
			conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	

	
	public void submitInventoryItem() {
		
		// INSERT INTO INVENTORY TABLE
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "insert into g_halsey_inventory(sku_number,quantity) values ('" + txtSkuNumber.getText() + "','" + txtQuantity2.getText()
					+ "')";
			stmt.executeUpdate(sql);
			System.out.println("Inventory record created");
 
			conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	
	public void submitUpdate() {

		System.out.println("Update Submit button pressed");
		
	
//		UPDATE INVENTORY TABLE
		try {
			// Execute a query
			System.out.println("Updating a record in the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

		      sql = "update g_halsey_inventory set quantity = '" + txtQuantity.getText() + "' where iid = '" + txtInventoryID.getText()	+ "'";
			//sql = "update g_halsey_inventory set quantity = 1199 where iid = 1"; //'" + txtQuantity.getText() + "' where iid = '" + txtInventoryID.getText()
			 
			stmt.executeUpdate(sql);
			System.out.println("Client record updated");

			conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		
	}

	public void submitDelete() {

		System.out.println("Delete Submit button pressed");
		
//		DELETE FROM CLIENTS TABLE
		try {
			// Execute a query
			System.out.println("Deleting records from the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "delete from g_halsey_clients where id = '" + txtClientID.getText()
					+ "'";
			stmt.executeUpdate(sql);
			System.out.println("Client record deleted");

			conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	

	}
	
	
	public void submitDeleteItem() {

		System.out.println("Delete Item Submit button pressed");
		
//		DELETE FROM INVENTORY TABLE
		try {
			// Execute a query
			System.out.println("Deleting records from the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "delete from g_halsey_inventory where iid = '" + txtDeleteInventoryID.getText()
					+ "'";
			stmt.executeUpdate(sql);
			System.out.println("Inventory record deleted");

			conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	

	}

}
