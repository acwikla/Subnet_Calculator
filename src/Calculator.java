import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import sun.net.util.IPAddressUtil;

import static java.lang.Math.abs;

public class Calculator {
    public String IP;
    public int firstOctet;
    public int secondOctet;
    public int thirdOctet;
    public int fourthOctet;
    public int maskSize;
    public long longIpAddress;
    public long longMask;
    public long subnetAddress;
    public long firstHostAddress;
    public long broadcastAddress;
    public long lastHostAddress;
    public long maxHostAmount;

    public enum AddressClassType {
        A,
        B,
        C,
        D,
        E
    }

    Calculator(String givenIpAddress) {
        splitIpAddress(givenIpAddress);
    }

    Calculator() throws SocketException, UnknownHostException {
        setMaskAndIpAddress();
        splitIpAddress(IP);
    }

    public void setMaskAndIpAddress() throws UnknownHostException, SocketException {
        try {
            InetAddress myIp = InetAddress.getLocalHost();
            String name = myIp.getHostAddress();
            IP = name;
            //pobieranie maski:
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(myIp);
            int netPrefix = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
            maskSize = netPrefix;

        } catch (Exception e) {
            e.printStackTrace();
        }
        String exception = "sysko sie zjebao";
        //return exception;

    }

    public void splitIpAddress(String ip) {

        boolean decision = false;

        for (int i = 0; i < ip.length(); i++) {

            if (ip.charAt(i) == '/')
                decision = true;
        }
        String[] split = ip.split("/");
        IP = split[0];
        if (decision) {
            maskSize = Integer.parseInt(split[1]);
        }
        String[] secondSplit = split[0].split("\\.");
        firstOctet = Integer.parseInt(secondSplit[0]);
        secondOctet = Integer.parseInt(secondSplit[1]);
        thirdOctet = Integer.parseInt(secondSplit[2]);
        fourthOctet = Integer.parseInt(secondSplit[3]);
        System.out.println("firstOctet:" + firstOctet + " secondOctet:" + secondOctet + " thirdOctet:" + thirdOctet + " fourthOctet:" + fourthOctet);
        longIpAddress = (fourthOctet) + (thirdOctet * 256) + (secondOctet * 256 * 256) + ((long) firstOctet * 256 * 256 * 256);
        longMask = IpOperations.calculateMaskValue(maskSize);
    }


    public boolean isIpAddressCorrtect(String IP) {
        boolean isCorrect = IPAddressUtil.isIPv4LiteralAddress(IP);
        return isCorrect;
    }

    public boolean isIpAddressPublic(String IP) {
        if (firstOctet == 10) return false;
        if (firstOctet == 172 && secondOctet >= 16 && secondOctet <= 31) return false;
        if (firstOctet == 192 && secondOctet == 168) return false;
        return true;
    }

    public String printIsIpAddressCorrect(){
        if(isIpAddressCorrtect(IP))return "Address is correct.";
        else return "Address is incorrect.";
    }
    public String printIsIpAddressPublic(){
        if(isIpAddressPublic(IP)) return "Address is public.";
        else return "Address is private.";
    }
    public AddressClassType determineClassType() {
        if (firstOctet < 128) return AddressClassType.A;
        if (firstOctet < 192) return AddressClassType.B;
        if (firstOctet < 224) return AddressClassType.C;
        if (firstOctet < 240) return AddressClassType.D;
        else return AddressClassType.E;
    }

    public String ipToBinary(long ip) {
        if (ip < 0) {
            ip = abs(ip);
        }
        String bin = Long.toBinaryString(ip);
        return new StringBuilder("00000000000000000000000000000000".substring(bin.length()) + bin)
                .insert(24, ".")
                .insert(16, ".")
                .insert(8, ".")
                .toString();
    }

    private void printLongBin(long val) {
        System.out.println(ipToBinary(val));
    }

    public void completeAddressValues() {
        subnetAddress = IpOperations.getSubnetAddress(longIpAddress, longMask);
        firstHostAddress = IpOperations.getFirstHostAddress(subnetAddress);
        broadcastAddress = IpOperations.getBroadcastAddress(longIpAddress, longMask);
        lastHostAddress = IpOperations.getLastHostAddress(broadcastAddress);
        maxHostAmount = IpOperations.getMaxHostAmount(longMask);
    }

    public String convertToDecValue(String binToConvert) {
        String[] splitBin = binToConvert.split("\\.");
        int firstDecOctet = Integer.parseInt(splitBin[0], 2);
        int secondDecOctet = Integer.parseInt(splitBin[1], 2);
        int thirdDecOctet = Integer.parseInt(splitBin[2], 2);
        int fourthDecOctet = Integer.parseInt(splitBin[3], 2);
        return Integer.toString(firstDecOctet) + "." + Integer.toString(secondDecOctet) + "." + Integer.toString(thirdDecOctet) + "." + Integer.toString(fourthDecOctet);
    }

    public void printDecAddressValue() {
        System.out.println("Decimal Mask:");
        System.out.println(convertToDecValue(ipToBinary(longMask)));
        System.out.println("Decimal subnet address:");
        System.out.println(convertToDecValue(ipToBinary(subnetAddress)));
        System.out.println("Decimal first host address:");
        System.out.println(convertToDecValue(ipToBinary(firstHostAddress)));
        System.out.println("Decimal broadcast address:");
        System.out.println(convertToDecValue(ipToBinary(broadcastAddress)));
        System.out.println("Decimal last host address:");
        System.out.println(convertToDecValue(ipToBinary(lastHostAddress)));
        System.out.println("Max host amount:");
        System.out.println(maxHostAmount);
        System.out.println();
    }

    public void printBinAddressValues() {
        System.out.println("Binary IP address:");
        printLongBin(longIpAddress);
        System.out.println("Binary Mask:");
        printLongBin(longMask);
        System.out.println("Binary subnet address:");
        printLongBin(subnetAddress);
        System.out.println("Binary first host address:");
        printLongBin(firstHostAddress);
        System.out.println("Binary broadcast address:");
        printLongBin(broadcastAddress);
        System.out.println("Binary last host address:");
        printLongBin(lastHostAddress);
    }


}

