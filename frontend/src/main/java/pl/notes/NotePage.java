package pl.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotePage {

    private List<Note> content;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;

    // Konstruktory
    public NotePage() {
        this.content = new ArrayList<>();
    }

    public NotePage(List<Note> content, Integer page, Integer size, Long totalElements, Integer totalPages, Boolean last) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    // Gettery i settery
    public List<Note> getContent() {
        return content;
    }

    public void setContent(List<Note> content) {
        this.content = content;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotePage notePage = (NotePage) o;
        return Objects.equals(content, notePage.content) &&
                Objects.equals(page, notePage.page) &&
                Objects.equals(size, notePage.size) &&
                Objects.equals(totalElements, notePage.totalElements) &&
                Objects.equals(totalPages, notePage.totalPages) &&
                Objects.equals(last, notePage.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, page, size, totalElements, totalPages, last);
    }

    @Override
    public String toString() {
        return "NotePage{" +
                "content=" + content +
                ", page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                '}';
    }
}

