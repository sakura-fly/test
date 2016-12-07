package listener;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener()
public class ServletRequestListenerTest implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("wwwwwwwwwwwwwwwwwwwwwww");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
		System.out.println(request.getMethod());
		try {
			System.out.println(gets(request.getReader()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("qqqqqqqqqqqqqqqqqqq");
	}

	private String gets(BufferedReader reader) throws IOException {
		StringBuffer sb = new StringBuffer();
		String s;
		while ((s = reader.readLine()) != null) {
			sb.append(s);
		}
		return sb.toString();
	}

}
