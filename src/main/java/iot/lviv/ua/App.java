package iot.lviv.ua;

import iot.lviv.ua.view.View;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (Session SESSION = HibernateUtil.getSession()){
            System.out.println("Session started");
            View myView = new View();
            myView.mainLoop();
        }
        catch (Throwable ex){
            System.err.println("Session failed");
            ex.printStackTrace();
        }
    }
}
