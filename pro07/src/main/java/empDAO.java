import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class empDAO {

	private Statement stmt;
	private Connection conn;
	
	public ArrayList<empDTO> listMember() { // Read - select
		ArrayList<empDTO> list = new ArrayList<empDTO>();
		try {
			connDB();
			String query="select a.employee_id,a.emp_name,b.emp_name manager_name,c.department_name "+
								"from employees a, employees b, departments c "+
								"where a.manager_id=b.employee_id "+
								"   and a.department_id=c.department_id";
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int eid=rs.getInt("employee_id");
				String emp_name=rs.getString("emp_name");
				String m_name = rs.getString("manager_name");
				String d_name = rs.getString("department_name");
				empDTO mvo=new empDTO();
				mvo.setEmployee_id(eid);
				mvo.setEmp_name(emp_name);
				mvo.setManager_name(m_name);
				mvo.setDepartment_name(d_name);
				list.add(mvo);
			}
			rs.close();  // memory반납.
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private void connDB() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String userid="ora_user";
		String passcode="human123";
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, userid, passcode);
//			if(conn==null) {
//				System.out.println("데이터베이스 접속실패");
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
