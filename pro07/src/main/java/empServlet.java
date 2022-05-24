

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class empServlet
 */
@WebServlet("/empList")
public class empServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public empServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");  // 출력에 한글이 깨지지 않게.
		PrintWriter out = response.getWriter();
		empDAO dao = new empDAO();
		ArrayList<empDTO> list = dao.listMember();
		
		out.print("<html><head><title>Result from t_member</title></head><body>");
		out.print("<table border=1><tr><th>사번</th><th>이름</th><th>매니저이름</th><th>부서명</th></tr>"); // head line
		for(int i=0; i<list.size(); i++) {
			empDTO mvo = list.get(i);
			int eid = mvo.getEmployee_id();
			String name = mvo.getEmp_name();
			String m_name = mvo.getManager_name();
			String d_name = mvo.getDepartment_name();
			out.print("<tr><td>"+eid+"</td><td>"+name+"</td><td>"+m_name+"</td><td>"+d_name+"</td></tr>");
		}
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
