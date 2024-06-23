package Assignment7.utils;

import Assignment7.models.Page;

import java.util.List;

public class PageUtils {
    public static <T> Page<T> getPage(List<T> items, int pageNumber, int size) {
        int totalItems = items.size();
        int fromIndex = (pageNumber - 1) * size;
        int toIndex = Math.min(fromIndex + size, totalItems);

        List<T> pageContent = items.subList(fromIndex, toIndex);
        return new Page<>(pageContent, pageNumber, size, totalItems);
    }
}
