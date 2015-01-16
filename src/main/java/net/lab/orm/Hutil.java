package net.lab.orm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by lab on 17.11.2014.
 */
public class Hutil {
    private static SessionFactory ses = null;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

            ses = cfg.buildSessionFactory(sr);
            }
        catch (Exception e){System.out.println(e);}

    }

public static SessionFactory getSessionFactory(){
    return ses;
}

}
