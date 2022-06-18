import java.math.BigDecimal;

public class testIntToFloat {
    public static void main(String[] args) {
        int i=123456789;
        float f=i;
        double d=i;
        Float F= Float.valueOf(i);
        final BigDecimal F_BD = new BigDecimal(F.toString());
        System.out.println(i);
        System.out.println(f);
        System.out.println("Float: "+F.toString());
        System.out.println("F_BD: "+F_BD);
        System.out.println(d);
    }
}
