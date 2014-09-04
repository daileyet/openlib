package web.upload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = -7898641199559369739L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fname = "test.xls";
			response.setCharacterEncoding("UTF-8");
			fname = java.net.URLEncoder.encode(fname, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fname);
			response.setContentType("application/msexcel");// 定义输出类型	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}