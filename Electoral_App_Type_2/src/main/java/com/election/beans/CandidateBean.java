package com.election.beans;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.election.dao.CandidateDao;
import com.election.dao.CandidateDaoImpl;
import com.election.pojos.Candidate;

public class CandidateBean {

	// dependency : dao layer
		private CandidateDao candidateDao;

		public CandidateBean() throws SQLException {
			// create dao instance
			candidateDao = new CandidateDaoImpl();// up casting
			System.out.println("candidate bean n dao created ...");
		}

		public CandidateDao getCandidateDao() {
			return candidateDao;
		}

		// add B.L method to ret top 2 candidates
		public List<Candidate> getTop2Candidates() throws SQLException {
			return candidateDao.getTop2Candidates();
		}

		// add B.L method to ret party votes analysis
		public LinkedHashMap<String, Integer> fetchPartyAnalysis() throws SQLException {
			return candidateDao.getPartywiseVotes();
		}
		
}
