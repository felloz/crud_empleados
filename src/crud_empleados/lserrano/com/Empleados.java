package crud_empleados.lserrano.com;

import java.util.Date;

public class Empleados {
	
	private int empNo;
	private String firstName;
	private String lastName;
	private String gender;
	private Date hireDate;
	private Date birthDate;
	private String idCard;
	
	
	
	public Empleados(int empNo, String idCard, String firstName, String lastName, String gender, Date hireDate, Date birthDate) {

		this.empNo     = empNo;
		this.idCard    = idCard;
		this.firstName = firstName;
		this.lastName  = lastName;
		this.gender     = gender;
		this.hireDate  = hireDate;
		this.birthDate = birthDate;		
	}
	
	
	
	public Empleados(String idCard, String firstName, String lastName, String gender, Date birthDate) {

		this.idCard = idCard;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
	}
	
	



	public String getIdCard() {
		return idCard;
	}



	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}



	public int getEmpNo() {
		return empNo;
	}



	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public Date getHireDate() {
		return hireDate;
	}
	

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	@Override
	public String toString() {
		return "Empleados [empNo=" + empNo + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", hireDate=" + hireDate + " , birthDate=" + birthDate + "  ]";
	}


}
