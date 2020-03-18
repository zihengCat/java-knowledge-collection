
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
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
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    System.out.println("本机的IP = " + ip.getHostAddress());
                    if (ip != null 
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        System.out.println("本机的IP = " + ip.getHostAddress());
                    } 
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}