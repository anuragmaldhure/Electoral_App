package com.election.dao;

import java.sql.SQLException;

import com.election.pojos.User;

public interface UserDao {
//add a method for user authentication(login)
	User authenticateUser(String email, String password) throws SQLException;

	// add a method to update voting status
	String updateVotingStatus(int voterId) throws SQLException;

	// add a method for voter reg.
	String registerNewVoter(User newVoter) throws SQLException;
}
