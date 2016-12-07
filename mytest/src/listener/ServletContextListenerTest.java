package listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ServletContextListenerTest implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("1111111111111111111111111111");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("2222222222222222222222222222");
	}


}
