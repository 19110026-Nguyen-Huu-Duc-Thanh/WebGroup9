package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import dao.UserDAO;
import model.Content;
import model.User;
import net.bytebuddy.utility.RandomString;
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UserDAO userDAO;

	    public void init() {
	        userDAO = new UserDAO();
	    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                  //  showNewForm(request, response);
                    break;
                case "/insertUser":
                    insertUser(request, response);
                    break;
                case "/register":
                      showRegisterForm(request, response);
                      break;
                case "/delete":
                  //  deleteUser(request, response);
                    break;
                case "/edit":
                  //  showEditForm(request, response);
                    break;
                case "/update":
                  //  updateUser(request, response);
                    break;
                case "/loginForm":
                	loginForm(request, response);
                    break;
                case "/Home":
                	loginUser(request, response);
                    break;
                case "/logout":
                	logoutUser(request, response);
                    break;
                case "/AddContent":
                	insertContent(request, response);
                    break;
                case "/view-content":
                	viewcontentForm(request, response);
                    break;
                case "/editProfileForm":
                	editProfileForm(request, response);
                    break;
                case "/editProfile":
                	editProfile(request, response);
                    break;
                case "/updateContentForm":
                	updateContentForm(request, response);
                    break;
                case "/deleteContent":
                	deleteContent(request, response);
                    break;
                case "/updateContent":
                	updateContent(request, response);
                    break;
                case "/viewContentForm":
                	viewContentForm(request, response);
                    break;
                case "/viewContentPage":
                	viewContentPage(request, response);
                    break;
                case "/verify":
                	verifyForm(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 private void insertContent(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
		 response.setContentType("text/html;charset=UTF-8");
    	 request.setCharacterEncoding("UTF-8");
		 String tiles = request.getParameter("tiles");
	        String brief = request.getParameter("brief");
	        String content = request.getParameter("content");
	        String checker = request.getParameter("name");
	        int authorid = Integer.parseInt(request.getParameter("id"));
	        Content a = new Content(tiles,brief,content,authorid);
	        User checkname = userDAO.selectUserBYidname(authorid,checker);
	        if(checkname==null)
	        {
	             request.getRequestDispatcher("logout").forward(request, response);
	        }
	        else 
	        {
	        userDAO.insertContent(a);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker+"&static=Add_successful");	       
	        dispatcher.forward(request, response);
	        }
			    }
		    private void insertUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		    	response.setContentType("text/html;charset=UTF-8");
		        String user = request.getParameter("username");
		        String pass = request.getParameter("password");
		        String email = request.getParameter("email");
		        User checkname = userDAO.selectUserBYname(email);
		        String randomCode = RandomString.make(64);
		        User newUser = new User(user, pass, email,randomCode);
		        if(checkname!=null)
		        {
		        	 request.setAttribute("mess", "Email already exists");
		        	 request.setAttribute("user", newUser);
		             request.getRequestDispatcher("views/register.jsp").forward(request, response);
		             
		        }
		        else        
		        {
		        userDAO.insertUser(newUser);
		        HttpSession session = request.getSession();
		        session.setAttribute("user", newUser);
		        final String username = "webcardbank74@gmail.com";
		        final String password = "card@!1234";

	        	System.out.print("preparing");
		        Properties prop = new Properties();
		        prop.put("mail.smtp.host","smtp.gmail.com");
		        prop.put("mail.smtp.port","587");
		        prop.put("mail.smtp.auth","true");
		        prop.put("mail.smtp.starttls.enable","true");
		        Session sesion = Session.getInstance(prop, new Authenticator()
		        		{
		        	protected PasswordAuthentication getPasswordAuthentication()
		        	{
		        		return new PasswordAuthentication(username,password);
		        	}
		        		}
		        );
		        String emailTo = email;
		        String emailSubject = "Dear "+ user+", Please verify your registration";
		        String emailContent =  "<h1><b>The Company of Group 9 </b></h1><br>"
						+ "Please click the link below to verify your registration:<br>"
						+ "<h3><a href=\"[[URL]]\" \">VERIFY</a></h3>"
						+ "Thank you,<br>"
						+ "The Company of Group 9.";
				String verifyURL = getSiteURL(request) + "/verify?code=" + randomCode;
				
				emailContent = emailContent.replace("[[URL]]", verifyURL);
		        try
		        {
		        	Message message = new MimeMessage(sesion);
		        	message.setFrom(new InternetAddress(username));
		        	message.setRecipient(
		        			Message.RecipientType.TO,
		        			new InternetAddress(emailTo)
		        			);
		        	message.setSubject(emailSubject);
		        	message.setContent(emailContent,"text/html");
		        	Transport.send(message);
		        	System.out.print("Done");
		        }
		        catch(Exception e)
		        {
		        	System.out.print("error");
		        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE,null,e);	
		        }
		        RequestDispatcher dispatcher = request.getRequestDispatcher("views/registers_success.jsp?static=1");
		        dispatcher.forward(request, response);
		        }
		    }
		    private String getSiteURL(HttpServletRequest request) {
				String siteURL = request.getRequestURL().toString();
				return siteURL.replace(request.getServletPath(), "");
			}	
		    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
				        RequestDispatcher dispatcher = request.getRequestDispatcher("views/register.jsp");
				        dispatcher.forward(request, response);
				    }
		    private void editProfileForm(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
		        User user = userDAO.selectUserBYidname(authorid,checker);
		        if(user == null)
		        {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker);
			        dispatcher.forward(request, response);
		        }
		        else {
				        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.tiles?id="+authorid+"&name="+checker+"&lname="+user.getLname()+"&fname="+user.getFname()+"&phone="+user.getPhone()+"&email="+user.getEmail()+"&des="+user.getDescription());
				        dispatcher.forward(request, response);
				    }
		    }
		    private void editProfile(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException, ServletException {
		    	response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
		        int phone = Integer.parseInt(request.getParameter("phone"));
		        String firstname = request.getParameter("firstname");
		        String lastname = request.getParameter("lastname");
		        String email = request.getParameter("email");
		        String des = request.getParameter("des");
		        User newuser = new User(authorid,firstname, lastname,phone,email, des);
		        if(userDAO.updateUser(newuser))
		        {
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker+"&static=update_profile_succssfull");
			        dispatcher.forward(request, response);
		        }
		        else
		        {
		        	String mess= "Error_Infomation_Can't_Update_Content";
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker+"&static="+mess);
			        dispatcher.forward(request, response);
		        }
		    }
		    private void viewContentForm(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
		    	RequestDispatcher dispatcher;
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		    	String search = request.getParameter("search");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
		        UserDAO content = new UserDAO();
				int numPage = content.getNumPage(authorid,search);
				int numId = content.getNumId(authorid,search);
				if (numId == 0) {
					response.sendRedirect("view-content.tiles?Page=0&numId=0&id="+authorid+"&name="+checker+"&search="+search);
				} else {
					String NumPageButton = "&id="+authorid+"&name="+checker+"&search="+search;
					int currentPage = 1;
					while (currentPage <= 5) {
						if (currentPage <= numPage) {
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentPage + 5) + "="
									+ Integer.toString(currentPage);
							currentPage++;
						} else
							break;
						if (currentPage != numPage && currentPage == 5)
							NumPageButton = NumPageButton + "&BtLast=" + Integer.toString(numPage);
					}
					if (numPage >= 1) {
						List<Content> listContent = content.selectAllContents(10, 0,authorid,search);
						request.setAttribute("listContent", listContent);
						 dispatcher = request
								.getRequestDispatcher("view-content.tiles?Page=0&numId=10" + NumPageButton);
						dispatcher.forward(request, response);
					} else {
						List<Content> listContent = content.selectAllContents(numId, 0,authorid,search);
						request.setAttribute("listContent", listContent);
						 dispatcher = request.getRequestDispatcher("view-content.tiles?Page=0&numId= " + numId + NumPageButton);
						dispatcher.forward(request, response);
					}
				}
		    }
		    private void viewContentPage(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		    	String search = request.getParameter("search");
		        int authorid = Integer.parseInt(request.getParameter("id"));		       
		        UserDAO content = new UserDAO();
				int numPage = content.getNumPage(authorid,search);
				int pageReq = Integer.parseInt(request.getParameter("Page"));
				if (numPage < pageReq || 0 > pageReq) {
					response.sendRedirect("view-content.tiles?Page=0&numId=0&id="+authorid+"&name="+checker+"&search="+search);
				} else {
					 String NumPageButton = "&id="+authorid+"&name="+checker+"&search="+search;
					int currentBt = 1;
					int butP = 1;
					if (numPage != 0) {
						{
							while (currentBt <= 5) {
								if (pageReq - butP >= 0) {
									NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentBt) + "="
											+ Integer.toString(pageReq - butP);
									currentBt++;
									butP++;
								} else
									break;
								if (pageReq - butP - 1 >0 && currentBt == 5) {
									NumPageButton = NumPageButton + "&BtFirst=" + Integer.toString(0);
								}
							}
							currentBt=6;
							butP = 1;
							while (currentBt <= 10) {
								if (pageReq + butP <= numPage) {
									NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentBt) + "="
											+ Integer.toString(pageReq + butP);
									currentBt++;
									butP++;
								} else
									break;
								if (pageReq + butP - 1 < numPage && currentBt == 10) {
									NumPageButton = NumPageButton + "&BtLast=" + Integer.toString(numPage);
								}
							}
						}
					}
					int numId = content.getNumId(authorid,search);
					if (pageReq != 0) {
						numId = numId - numPage * 10;
						List<Content> listContent = content.selectAllContents(10, pageReq,authorid,search);
						request.setAttribute("listContent", listContent);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("view-content.tiles?Page=" + pageReq + "&numId=" + numId + NumPageButton);
						dispatcher.forward(request, response);
					} else {

						if (numPage >= 1) {
							List<Content> listContent = content.selectAllContents(10, pageReq,authorid,search);
							request.setAttribute("listContent", listContent);
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("view-content.tiles?Page=0&numId=10" + NumPageButton);
							dispatcher.forward(request, response);
						} else {
							List<Content> listContent = content.selectAllContents(numId, pageReq,authorid,search);
							request.setAttribute("listContent", listContent);
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("view-content.tiles?Page=0&numId=" + numId + NumPageButton);
							dispatcher.forward(request, response);
						}
					}
				}
		    }
		    private void deleteContent(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException {
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
				int id = Integer.parseInt(request.getParameter("idC"));
				        userDAO.deleteContent(id);
				        response.sendRedirect("viewContentForm?id="+authorid+"&name="+checker);

				    }
		    private void verifyForm(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException, ServletException {
		    	response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String check = request.getParameter("code");		       
		        User user = userDAO.selectUserBYPIN(check);
		        if(user == null)
		        {

			        RequestDispatcher dispatcher = request.getRequestDispatcher("views/registers_success.jsp?static=2");
			        dispatcher.forward(request, response);
		        }
		        else {
		        	if(userDAO.updateverify(user))
		        				        	{
				        RequestDispatcher dispatcher = request.getRequestDispatcher("views/registers_success.jsp?static=1");
				        dispatcher.forward(request, response);}
		        	else
		        	{
				        RequestDispatcher dispatcher = request.getRequestDispatcher("views/registers_success.jsp?static=2");
				        dispatcher.forward(request, response);
		        	}
		        	}
				    
		    }
		    private void updateContentForm(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException, ServletException {
		    	response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
		        int id = Integer.parseInt(request.getParameter("idC"));
		        Content newcontent = userDAO.selectContentBYid(id,authorid);
		        if(newcontent == null)
		        {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker+"&static=fail");
			        dispatcher.forward(request, response);
		        }
		        else {
				        RequestDispatcher dispatcher = request.getRequestDispatcher("add-content.tiles?id="+authorid+"&name="+checker+"&static=0&idC="+newcontent.getId()+"&title="+newcontent.getTitle()+"&brief="+newcontent.getBrief()+"&contentn="+newcontent.getContent()+"&sort="+newcontent.getSort());
				        dispatcher.forward(request, response);
				    }
		    }
		    
		    private void updateContent(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException, ServletException {
		    	response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
		        int id = Integer.parseInt(request.getParameter("idC"));
		        int sort = Integer.parseInt(request.getParameter("sort"));
		        String tiles = request.getParameter("tiles");
		        String brief = request.getParameter("brief");
		        String content = request.getParameter("content");
		        Content newcontent = new Content(id, tiles,brief,content, sort, authorid);
		        if(userDAO.updateContent(newcontent))
		        {
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker+"&static=Update_successfull");
			        dispatcher.forward(request, response);
		        }
		        else
		        {
		        	String mess= "Error Infomation - Can't Update Content";
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker+"&idC="+id+"&static="+mess);
			        dispatcher.forward(request, response);
		        }
		    }
		    private void updateProfileForm(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String checker = request.getParameter("name");
		        int authorid = Integer.parseInt(request.getParameter("id"));
		        User user = userDAO.selectUserBYidname(authorid,checker);
		        if(user == null)
		        {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.tiles?id="+authorid+"&name="+checker);
			        dispatcher.forward(request, response);
		        }
		        else {
				        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.tiles?id="+authorid+"&name="+checker+"&lname="+user.getLname()+"&fname="+user.getFname()+"&phone="+user.getPhone()+"&email="+user.getEmail()+"&des="+user.getDescription());
				        dispatcher.forward(request, response);
				    }
		    }
		    private void loginForm(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
				        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				        dispatcher.forward(request, response);
				    }
		    private void viewcontentForm(HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException {
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        String user = request.getParameter("");
		        int authorid = Integer.parseInt(request.getParameter("id"));
				response.sendRedirect("view-content.tiles?id="+authorid+"&name="+user);
				    }
		    private void loginUser(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException, ServletException {
		    	 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
		        User user = userDAO.login(email,password);
		        if(user==null)
		        {
		        	 request.setAttribute("mess", "Wrong user or pass");
		             request.getRequestDispatcher("loginForm").forward(request, response);
		        }
		        else        
		        {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("editProfileForm?id="+user.getId()+"&name="+user.getUsername());
		        HttpSession session = request.getSession();
		        session.setAttribute("user", user);
		        session.setMaxInactiveInterval(1000);
		        dispatcher.forward(request, response);
		        
		        }
				    }
			private void logoutUser(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, IOException, ServletException {
				 response.setContentType("text/html;charset=UTF-8");
		    	 request.setCharacterEncoding("UTF-8");
		        HttpSession session = request.getSession();
		        session.removeAttribute("user");
		        request.getSession().invalidate();
		        response.sendRedirect("loginForm");
				    }
		    
}
