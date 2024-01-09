package com.election.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.election.pojos.User;

import static com.election.utils.DBUtil.openConnection;
import static com.election.utils.DBUtil.closeConnection;

public class UserDaoImpl implements UserDao {

	// fields
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;

	public UserDaoImpl() throws SQLException {
		// open conn
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2=cn.prepareStatement("update users set status=1 where id=?");
		pst3 = cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		System.out.println("user dao created!");
	}
	
	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);		
		pst1.setString(2, password);
		
		try(ResultSet rst=pst1.executeQuery()) {
			System.out.println("execed auth query...");
			if(rst.next()) //=> success
			/*
			* int id, String firstName, String lastName, String email, String password, Date dob,
					boolean votingStatus, String role
			*/
			return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, 
								rst.getDate(6), rst.getBoolean(7), rst.getString(8));
			}//rst.close()
		return null;
	}

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {		
		// set IN param : voter id
		pst2.setInt(1, voterId);
		//exec method : exec update
		int updateCount=pst2.executeUpdate();
		if(updateCount==1)
			return "Updated voting status";
		
		return "Updation failed!!!!!";
	}

	@Override
	public String registerNewVoter(User newVoter) throws SQLException {
		// set IN params (first_name | last_name | email| password | dob| status | role
		pst3.setString(1, newVoter.getFirstName());
		pst3.setString(2, newVoter.getLastName());
		pst3.setString(3, newVoter.getEmail());
		pst3.setString(4, newVoter.getPassword());
		pst3.setDate(5, newVoter.getDob());
		pst3.setBoolean(6,newVoter.isVotingStatus());
		pst3.setString(7,newVoter.getRole());
		// exec : execUpdate
		int rowCount = pst3.executeUpdate();
		if (rowCount == 1)
			return "Voter registered";
		
		return "Voter reg failed!!!!!!!!!!!!!";
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		closeConnection();
		System.out.println("user dao cleaned up!");
	}

}
