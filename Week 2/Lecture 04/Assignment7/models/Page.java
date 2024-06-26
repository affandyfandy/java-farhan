package Assignment7.models;

import java.util.List;

public class Page<T> {
    private List<T> data;
    private int pageNumber;
    private int pageSize;
    private int totalItems;

    public Page(List<T> data, int pageNumber, int pageSize, int totalItems) {
        this.data = data;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    public List<T> getData() {
        return data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public boolean hasNext() {
        return (pageNumber * pageSize) < totalItems;
    }
}
