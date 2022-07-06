package model.vo;

public class User {
	private int userID;
	private String username;
	private String password;
	private String name;
	private int age;
	private String phone;
	private String gender;
	private boolean isManger;
	private int[] manageWarehouse;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getGender() {
		return gender;
	}

	public boolean isManger() {
		return isManger;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void isManger(boolean isManger) {
		this.isManger = isManger;
	}

	public int[] getManageWarehouse() { return manageWarehouse; }

	public void setManageWarehouse(int[] warehouseId) {
		this.manageWarehouse = warehouseId;
	}

	public int getUserID() { return userID; }

	public void setUserID(int userID) { this.userID = userID; }
}
