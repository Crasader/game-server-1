package base;

import communication.MessageSender;
import util.SimpleUniqueGenerator;
import util.UniqueIDGeneratorService;

/**
 * Created by veininguo on 2017/1/30.
 */
public class DefaultSession implements Session {
    protected final Object id;
    protected final long creationTime;
    protected long lastReadWriteTime;
    protected Status status;
    protected MessageSender sender;

    public DefaultSession(SessionBuilder builder) {
        this.id = builder.id;
        this.creationTime = builder.creationTime;
        this.lastReadWriteTime = builder.lastReadWriteTime;
        this.status = builder.status;
        this.sender = builder.sender;
    }

    public static class SessionBuilder {
        protected static final UniqueIDGeneratorService ID_GENERATOR_SERVICE = new SimpleUniqueGenerator();
        protected String id;
        protected long creationTime;
        protected long lastReadWriteTime;
        protected Status status;
        protected MessageSender sender;

        protected void validateAndSetValues() {
            if (null == id) {
                id = String.valueOf(ID_GENERATOR_SERVICE.generateFor(DefaultSession.class));
            }
        }

        public Session build() {
            validateAndSetValues();
            return new DefaultSession(this);
        }

        public SessionBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public SessionBuilder creationTime(long creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public SessionBuilder lastReadWriteTime(long lastReadWriteTime) {
            this.lastReadWriteTime = lastReadWriteTime;
            return this;
        }

        public SessionBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public SessionBuilder sender(MessageSender sender) {
            this.sender = sender;
            return this;
        }
    }

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public long getLastReadWriteTime() {
        return lastReadWriteTime;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public boolean isConnected() {
        return this.status == Status.CONNECTED;
    }

    @Override
    public boolean isClosed() {
        return this.status == Status.CLOSED;
    }

    @Override
    public synchronized void close() {
        this.status = Status.CLOSED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultSession that = (DefaultSession) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DefaultSession{" +
                "id='" + id + '\'' +
                '}';
    }
}
