package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import Dao.DBConnect;

public class ClientModel extends DBConnect implements User<Bank>{

	private int cid; // client ID
	private int tid; // transaction ID
	private double balance;
	
	private int iid;
	private int skuNumber;
	private int quantity;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;
	
	Bank custBank; //set up Bank object

	public ClientModel() {

		conn = new DBConnect();
		
		//simulate bank data affiliation of client
		custBank = new Bank();
		custBank.setBankId(100);
		custBank.setBankName("Bank of IIT");
		custBank.setBankAddress("10 W 35th St, Chicago, IL 60616");
	}
 
	/* getters & setters */
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTid() {
		return tid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
	
	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public void setSkuNumber(int skuNumber) {
		this.skuNumber = skuNumber;
	}

	public int getSkuNumber() {
		return skuNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	// INSERT INTO METHOD
	public void insertRecord(int cid, int bal) {

		try {
			setCid(cid);
			// Execute a query
			System.out.println("Updating record in table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include data to the database table

			sql = "update g_halsey_inventory set quantity = '" + bal + "' where iid = '" + cid	+ "'";

			stmt.executeUpdate(sql);
			conn.getConnection().close();

			System.out.println("Quantity updated to " + bal + " for inventory ID " + cid);

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public List<ClientModel> getAccounts(int iid) {
		List<ClientModel> accounts = new ArrayList<>();
		String query = "SELECT * FROM g_halsey_inventory WHERE iid = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, iid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ClientModel account = new ClientModel();
				// grab record data by table field name into ClientModel account object
				account.setIid(resultSet.getInt("iid"));
				account.setSkuNumber(resultSet.getInt("sku_number"));
				account.setQuantity(resultSet.getInt("quantity"));
				accounts.add(account); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Accounts: " + e);
		}
		return accounts; // return arraylist
	}

	@Override
	public Bank getClientInfo() {
		// TODO Auto-generated method stub
		return custBank;
	}
}