package de.rieckpil.blog;

public class CustomMessage {

  private Long id;
  private String content;
  private String author;
  private long createdAt;

  public CustomMessage() {
  }

  public CustomMessage(Long id, String content, String author, long createdAt) {
    this.id = id;
    this.content = content;
    this.author = author;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "CustomMessage{" +
      "id=" + id +
      ", content='" + content + '\'' +
      ", author='" + author + '\'' +
      ", createdAt=" + createdAt +
      '}';
  }
}
