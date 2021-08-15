import org.junit.Test;

public class test {
    @Test
    public void test(){
        String s = "123-456---4532";
        String s1 = s.replaceAll("-", "");
        System.out.println(s1);
    }

    public static void main(String[] args) {
        test test = new test();
        test.te();
    }

    public static void te(){
        System.out.println("...");
    }

    @Test
    public void test2(){
        byte[] by = new String("adfsadfag").getBytes();
        System.out.println(by.toString());
        System.out.println(new String(by));
    }
}
