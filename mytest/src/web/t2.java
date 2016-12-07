package web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/t2")
public class t2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		Part p = arg0.getPart("file");
		String r = p.getHeader("Content-Disposition");
		System.out.println(p.getName());
		String fileSavingFolder = this.getServletContext().getRealPath("/UpLoad");
		// 获得存储上传文件的完整路径（文件夹路径+文件名）
		// 文件夹位置固定，文件夹采用与上传文件的原始名字相同
		 String fileName = r.substring(r.lastIndexOf("=") + 2, r.length() - 1);  
		String fileSavingPath = fileSavingFolder + File.separator + fileName;
		// 如果存储上传文件的文件夹不存在，则创建文件夹
		File f = new File(fileSavingFolder + File.separator);
		if (!f.exists()) {
			f.mkdirs();
		}
		p.write(fileSavingPath);
		System.out.println(r);
		arg1.getWriter().print(r);
	}
}
