package com.sunbeam.daos;

import com.sunbeam.utils.DBUtil;

import java.sql.Connection;

public class Dao implements AutoCloseable{
	protected Connection con;
	public Dao() throws Exception {
		con = DBUtil.getConnection();
	}
	public void close() throws Exception {
		if(con != null)
			con.close();
	}
}
