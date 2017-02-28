package service.impl;

import base.Session;
import service.SessionRegistryService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by veininguo on 2017/1/30.
 */
public class SessionRegistry<T> implements SessionRegistryService<T> {
    private final Map<T, Session> sessions;

    public SessionRegistry() {
        sessions = new ConcurrentHashMap<>(100);
    }

    @Override
    public Session getSession(T key) {
        return sessions.get(key);
    }

    @Override
    public void addSession(T key, Session session) {
        sessions.put(key, session);
    }

    @Override
    public boolean removeSession(T key) {
        return sessions.remove(key) != null;
    }
}
