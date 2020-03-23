//import java.lang.*;-nie wiem czy potrzebne

public class IpOperations {

    public static final long MAX_32_BIT_VAL = ((long) (Integer.MAX_VALUE) << 1) + 1;//32 jedynki, final oznacza ze to stala

    public static long getSubnetAddress(long longIpAddress, long longMask) {
        return MAX_32_BIT_VAL & (longIpAddress & longMask);
    }

    public static long getFirstHostAddress(long subnetAddress) {
        return MAX_32_BIT_VAL & (subnetAddress + 1);
    }

    public static long getBroadcastAddress(long longIpAddress, long longMask) {
        return MAX_32_BIT_VAL & (~longMask | longIpAddress);
    }

    public static long getLastHostAddress(long broadcastAddress) {
        return MAX_32_BIT_VAL & (broadcastAddress - 1);
    }
    //zwraca ilosc hostow
    public static long getMaxHostAmount(long mask) {
        long val = (~(mask)) - 1;
        val &= MAX_32_BIT_VAL;
        return val;
    }

    public static long calculateMaskValue(int maskSize) {
        return MAX_32_BIT_VAL & (MAX_32_BIT_VAL << 32 - maskSize);
    }
}
