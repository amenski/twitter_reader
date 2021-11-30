package org.interview.domain;

/**
 * @author aman
 *
 */
public class Message implements Comparable<Message> {

    private String id;
    private long createdAt;
    private String text;
    private Author author;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(long creationDate) {
        this.createdAt = creationDate;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + (int) (createdAt ^ (createdAt >>> 32));
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        Message other = (Message) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (createdAt != other.createdAt)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Message [id=" + id + ", createdAt=" + createdAt + ", text=" + text + ", author=" + author + "]";
    }
    
    @Override
    public int compareTo(Message o) {
        return this.id.compareTo(o.getId());
    }
}
