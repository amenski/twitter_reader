package org.interview.domain;

/**
 * @author aman
 *
 */
public class Author {

    private String id;
    private String name;
    private String screenName;
    private long createdAt;
    
    public String getId() {
        return id;
    }
    public void setId(String userId) {
        this.id = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    public long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(long createAt) {
        this.createdAt = createAt;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (createdAt ^ (createdAt >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        if (createdAt != other.createdAt)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (screenName == null) {
            if (other.screenName != null)
                return false;
        } else if (!screenName.equals(other.screenName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + ", screenName=" + screenName + ", createAt=" + createdAt
                + "]";
    }
}
