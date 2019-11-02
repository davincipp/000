package _00.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import _00.util.HibernateUtils;

@WebListener
public class CreateSessionFactoryListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	HibernateUtils.getSessionFactory().close();
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	HibernateUtils.getSessionFactory();
    }	
}
