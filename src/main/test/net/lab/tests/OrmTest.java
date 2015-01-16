package net.lab.tests;

import net.lab.orm.Hutil;
import net.lab.util.Obrabotka;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by lab on 17.11.2014.
 */
public class OrmTest {
    private static Session session = null;

    @Test
    public void OpenSessionTest() {
        try {
            session = Hutil.getSessionFactory().openSession();
            System.out.println("Session is opened!");
        } catch (Exception e) {
            System.out.println("ERROR!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                System.out.println("Session is closed!");
            }
        }
    }

    @Test
    public void OpenFile(){
        Obrabotka obrabotka = new Obrabotka("ITOG.txt");
        obrabotka.Razbor();
    }

}
