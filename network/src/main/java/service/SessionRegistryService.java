package service;

import base.Session;

/**
 * Created by veininguo on 2017/1/30.
 */
public interface SessionRegistryService<T> {
    Session getSession(T key);

    void addSession(T key, Session session);

    boolean removeSession(T key);
}
