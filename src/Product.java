
import java.util.*;
import java.util.stream.Stream;
class Product {
    int cost, count;
    String name;
    Product (String name, int count, int cost) {
        this.name = name;
        this.count = count;
        this.cost = cost;
    }
}
class ProductTable {
    HashMap<String, Product> Table;
    int total;
    ProductTable() {
        Table = new HashMap<>();
        total = 0;
    }
    void add(Product p) {
        Table.put(p.name, p);
        total += p.count;
    }
    void add(String name, int cost, int count) {
        Table.put(name, new Product(name, count, cost));
        total += count;
    }
    public Stream<String> nameStream(int v) {
        ArrayList<String> result = new ArrayList<>();
        Table.entrySet().stream()
                .filter(x -> x.getValue().count * x.getValue().cost > v)
                .forEach(x -> result.add(x.getKey()));
        return result.stream();
    }
    public Optional<Product> getProduct() {
        Optional<Product> result = Optional.empty();
        Optional<Map.Entry<String, Product>> tmp = Table.entrySet()
                .stream()
                .filter(x -> x.getValue().count * 2 > total)
                .findFirst();
        if (tmp.isPresent()) {
            result = Optional.ofNullable(tmp.get().getValue());
        }
        return result;
    }
}
class NameComparator implements Comparator<String> {
    public int compare(String a, String b) {
        char a0, b0;
        a0 = a.charAt(0);
        b0 = b.charAt(0);
        if (a0 > b0) { return 1; }
        if (a0 == b0) { return 0; }
        return -1;
    }
}

