
import java.util.*;
import java.util.stream.Stream;
class MyInt{
    int num, len;
    String name;
    MyInt (String s, int n) {
        this.name = s;
        this.num = n;
        this.len = 0;
        while(n > 0){
            ++len;
            n = n / 10;
        }
    }
}
class MyIntComparator implements Comparator <MyInt>{
    public int compare( MyInt a, MyInt b){
        return a.num - b.num;
    }
}
class MyIntTable {
    HashMap<String, MyInt> Table;
    MyIntTable() {
        Table = new HashMap<>();
    }
    void add(MyInt n) {
        Table.put(n.name, n);
    }
    void add(String name, int n) {
        Table.put(name, new MyInt(name, n));
    }
    public boolean checkTemplate (String template, MyInt N) {
        int n = N.num;
        String ns = Integer.toString(n);
//        System.out.println(template + " " + ns);
        if (template.length() != ns.length())
            return false;
        boolean ans = true;
        for (int i = 0; i < template.length(); ++i){
            if ((template.charAt(i) != '?') && (template.charAt(i) != ns.charAt(i))) {
                ans = false;
                break;
            }
        }
        return ans;
    }
    public Stream<String> nameStream(String template) {
        ArrayList<String> result = new ArrayList<>();
        Table.entrySet().stream()
                .filter(x -> checkTemplate(template, x.getValue()))
                .forEach(x -> result.add(Integer.toString(x.getValue().num)));
        return result.stream();
    }
    public Optional<MyInt> getInt(String template) {
        ArrayList<MyInt> toMyIntList = new ArrayList<>();
        Table.entrySet()
                .stream()
                .filter(x -> checkTemplate(template, x.getValue()))
                .forEach(x -> toMyIntList.add(x.getValue()));

        Optional <MyInt> result = toMyIntList.stream().max(new MyIntComparator());
        return result;
    }
}

class MyIntCmp implements Comparator<String> {
    public int compare(String a, String b) {
        if(a.length() < b.length())
            return -1;
        if (a.length() == b.length())
            return 0;
        return 1;
    }
}
