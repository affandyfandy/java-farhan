package Assignment5;
import java.util.ArrayList;

public class Try {
    public static void main(String[] args) {
        // Membuat objek ArrayList untuk menyimpan bilangan bulat
        ArrayList<Integer> numbers = new ArrayList<>();

        // Menambahkan elemen ke ArrayList
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(20); // Menambahkan elemen duplikat

        // Mencetak ArrayList
        System.out.println("ArrayList elements: " + numbers);

        // Mengakses elemen ArrayList berdasarkan indeks
        System.out.println("Element at index 2: " + numbers.get(2));

        // Menghapus elemen dari ArrayList
        numbers.remove(1);
        System.out.println("After removing element at index 1: " + numbers);

        // Menghitung jumlah elemen dalam ArrayList
        System.out.println("Size of ArrayList: " + numbers.size());
    }
}
