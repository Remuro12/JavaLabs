public class Main {
    public static void main(String[] args) {
        MyIntTable d = new MyIntTable();
        d.add("a", 100);
        d.add("b", 222);
        d.add("sss", 199);
        d.nameStream("???").sorted(new MyIntCmp()).forEach(System.out::println);
        System.out.println(d.getInt("???").get().name);
    }
}