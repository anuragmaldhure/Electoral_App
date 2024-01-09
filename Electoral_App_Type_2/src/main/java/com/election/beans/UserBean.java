package com.election.beans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import com.election.dao.UserDaoImpl;
import com.election.pojos.User;

public class UserBean {

	private UserDaoImpl userDao;
	// clnt req params
	private String em;
	private String pass;
	private String fname;
	private String lname;
	private String dob;// since WC CAN NOT translate to non primitive types
	private User userDetails;

	public UserBean() throws SQLException {
		// creat user dao instance
		userDao = new UserDaoImpl();
		System.out.println("user bean n dao created...");
	}

	// generate getters n setters
	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	// add B.L method for authentication + authorization
	public String validateUser() throws SQLException {
		System.out.println("in validate " + em + " " + pass);// not null
		// bean --> dao's method for auth
		userDetails = userDao.authenticateUser(em, pass);
		if (userDetails == null) {
			return "login";
		}
		// => valid login --> continue to role based auth
		if (userDetails.getRole().equals("admin"))
			return "admin_main";
		// => voter
		if (userDetails.isVotingStatus())
			return "logout";
		return "candidate_list";

	}

	// add B.L method for validation + registration
	public String registerUser() throws SQLException{
		System.out.println("in register " + dob);
		// validate age
		// string dob --> localdate
		LocalDate date = LocalDate.parse(dob);
		int age = Period.between(date, LocalDate.now()).getYears();
		if (age < 21)
			return "Reg failed : Invalid age!!!!!";
		// age : valid --create User pojo
		User newUser = new User(fname, lname, em, pass, Date.valueOf(date));
		//invoke dao's method
		return userDao.registerNewVoter(newUser);
	}
	
}
