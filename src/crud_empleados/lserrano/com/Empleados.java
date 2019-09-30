package crud_empleados.lserrano.com;

import java.util.Date;

public class Empleados {
	
	private int emp_no;
	private String first_name;
	private String last_name;
	private String gender;
	private Date hire_date;
	private Date birth_date;
	private String id_card;
	
	
	
	public Empleados(int emp_no, String id_card, String first_name, String last_name, String gender, Date hire_date, Date birth_date) {
		//super();
		this.emp_no     = emp_no;
		this.id_card    = id_card;
		this.first_name = first_name;
		this.last_name  = last_name;
		this.gender     = gender;
		this.hire_date  = hire_date;
		this.birth_date = birth_date;		
	}
	
	
	
	public Empleados(String id_card, String first_name, String last_name, String gender, Date hire_date, Date birth_date) {
		//super();
		this.id_card = id_card;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.birth_date = birth_date;
	}
	
	



	public String getId_card() {
		return id_card;
	}



	public void setId_card(String id_card) {
		this.id_card = id_card;
	}



	public int getEmp_no() {
		return emp_no;
	}



	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public Date getHire_date() {
		return hire_date;
	}
	

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	
	public Date getBirth_date() {
		return birth_date;
	}
	
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	
	
	@Override
	public String toString() {
		return "Empleados [emp_no=" + emp_no + ", first_name=" + first_name + ", last_name=" + last_name + ", gender="
				+ gender + ", hire_date=" + hire_date + " , birth_date=" + birth_date + "  ]";
	}


}
