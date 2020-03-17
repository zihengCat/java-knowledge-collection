
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Slover {
    public static void main(String[] args) {
        String str = ".spleh A+lrtC/dmC .thgis fo tuo si ti semitemos ,etihw si txet nehw sa drah kooL .tseretni wohs dluohs uoy ecalp a si ,dessecorp si xat hctuD erehw esac ehT .sedih tseuq fo txen eht erehw si ,deificeps era segaugnal cificeps-niamod tcudorp ehT";
        List<String> list = new LinkedList<>();
        for (String word : str.split(" ")) {
            list.add(new StringBuilder(word).reverse().toString());
        }
        String[] arr = new String[list.size()];
        Collections.reverse(list);
        System.out.println(
            String.join(" ", list.toArray(arr))
        );
        System.out.println(
            0 + 1 + 2 + 6
          + 7 + 9 + 12 + 56 + 70 + 182
          + 305 + 18000
        );
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            System.out.println(ip4.getHostAddress());
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}