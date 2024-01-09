Project focused on implementing Java EE

# Electoral App TYPE 1 of 2 (Servlet and JDBC based)

## Phase 1 : Setup STS Project

1. Configure TomCat Server v9 in STS Workspace
2. Create a Dynamic Web Project in STS 

## Phase 2 : Database and Java setup

1. Create Database [ election_webjava ] in MySQL
2. Create tables : candidates & users
3. Write DBUtil class for JDBC (com.sunbeam.util)
4. Write POJO class for Candidate (com.sunbeam.pojos)
5. Write POJO class for User (com.sunbeam.pojos)
6. Write Dao class for single JDBC connection to multiple Days (com.sunbeam.pojos)
7. Write interface ICandidateDao for Candidate Dao (com.sunbeam.daos)
8. Write implementation of ICandidateDao as CandidateDao class for Candidate DAO (com.sunbeam.daos)
9. Write interface IUserDao for UserDao (com.sunbeam.daos)
10. Write implementation of IUserDao as UserDao class for User DAO (com.sunbeam.daos)
11. Add my-sql-connector file in Election_App/src/main/webapp/WEB-INF/lib/<mysql-connector-j-8.1.0.jar> [Corresponding .class file is automatically updated in Libraries of of the Project]

## Phase 3 : Index File and Servlets

1. Write index.html (entry file of the application) in webapp
2. Write LoginServlet.java file
3. Write CandidateListServlet.java file
4. Write VoteServlet.java file
5. Write LogoutServlet.java file
6. Write ResultServlet.java file
7. Write register.html (
8. Write SignUpServlet.java file 

## Phase 4 : State Management 

### Part 1 : Cookies (Client side on browser)
1. Implement Cookie in loginServlet (Create cookie and setMaxAge) 
2. Implement Cookie for after login -> CandidateLoginServlet, ResultServlet, VoteServlet (Use cookie)
3. Implement Cookie in logoutServlet (Destroy cookie)

### Part 2 : Http Session (Server side on tomcat server)
1. Create session and set attribute in loginServlet [req.getSession( )]
2. Get and use current session of user. Also validate if user has already voted and take appropriate action accordingly in VoteServlet [session.getAttribute( )]
3. Also set session timeout in web.xml
4. End / destroy session of current user in logOutServlet [session.inValidate( )]

### Part 3 : URL Tracking (Alternate way of Session Management in case if Cookies are disabled on browser)
1. Modify web.xml with tag <tracking-mode>
2. Modify LoginServlet’s resp.sendRedirect with resp.encodeRedirectURL
3. Modify other servlets - CandidateListServlet, VoteServlet and others too (Not implemented for admin)

-> Reverted changes of URL tracking as we want to do it using Cookie

### Part 4: Delete Functionality implementation using Query String       
1. Implement CandidateDeleteServlet
2. Add Delete and Edit buttons in ResultServlet if not added

### Part 5: Edit Functionality implementation using Query String GET   
1. Implement CandidateEditServlet

### Part 6: Edit Functionality implementation using Query String POST    
1. Modify CandidateEditServlet

### Part 7: Edit/Delete message using Request Attributes (Inter-Servlet Communication)
1. Modify CandidateDeleteServlet
2. Modify CandidateEditServlet
3. Modify Result Servlet to add the servlet message

### Part 8:  Election Announcement Functionality and Servlet Context (Communication across all servlets)
1. Implement Announcement.html (For msg area) 
2. Implement AnnouncementServlet
3. Add Announcement msg area in ResultServlet
4. Add Announcement msg area in CandidateListServlet

# Electoral App TYPE 2 of 2 (JSP, JSTL JavaBeam and JDBC based)

## Phase 1 - Setting up  DB, JDBC, POJOs and DAOs
1. Create database named election_webjava
2. Create tables users and candidates
3. Add dependency, my-sql-connector jar file 
4. Write DBUtil class 
5. Write User POJO and Candidate POJO classes
6. Write interfaces UserDao and CandidateDao
7. Write their implementations - UserDaoImpl and CandidateDaoImpl classes

## Phase 2 -Basic pages and functional app using JSP, JSTL and JAVA Bean  

1. Write login.jsp ( User Sign in using email and password ) and understand <jsp:useBean id =“” class =“” scope=“”>
2. Setup web.xml file - login.jsp as <welcome-file>
3. Write UserBean.java with user input data state holder and implement business logic methods : public String validateUser() throws SQLException and public String registerUser() throws SQLException
4. Write CandidateBean.java and implement business logic methods : public List<Candidate> getTop2Candidates() throws SQLException and public LinkedHashMap<String, Integer> fetchPartyAnalysis() throws SQLException 
5. Write authenticate.jsp for checking user, creating a session and redirecting  
6. Write register.jsp for new user registration with age validation
7. Add centralised error handling - Update in web.xml and Write my_err_handler.jsp
8. Write admin_main.jsp - Displays vote analysis and top 2 candidates
9. Write candidate_list.jsp - Dummy implementation for now
10. Write logout.jsp - To invalidate user and destroy current session. Also allow user to re-login
11. Add cleanup functionalities : public void cleanUp() throws SQLException in UserDaoImpl and CandidateDaoImpl 

