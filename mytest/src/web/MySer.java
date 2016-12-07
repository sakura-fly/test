package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Contans;
import dao.WebInfoDao;
import model.WebInfo;

public class MySer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		WebInfoDao wid = new WebInfoDao();
		WebInfo wi = wid.getWebInfo(request);
		wid.addInfo(wi);
		if (wi.getPathInfo() == null || wi.getPathInfo().isEmpty()||wi.getPathInfo().equals("/")) {
			System.err.println(wi);
			request.getRequestDispatcher(Contans.ifPath + "index.jsp").forward(request, response);
		}
	}
}
