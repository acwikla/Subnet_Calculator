import java.io.File;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        Calculator c= new Calculator();
        //rzeczy:
        System.out.println("Decimal IP: "+ c.IP);
        System.out.println("Mask size:" + c.maskSize);
        c.printIsIpAddressCorrect();
        c.printIsIpAddressPublic();
        System.out.println("Class type:"+c.determineClassType());
        System.out.println();
        c.completeAddressValues();
        c.printDecAddressValue();
        c.printBinAddressValues();

        //zapis do pliku:
        WriteFile file = new WriteFile();
        file.writeFile();

        //PING:
        System.out.println("Execute the command PING?(Y-Yes)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if(answer.equals("Y")){
            Ping ping = new Ping();
            System.out.println(ping.sendPingRequest(c.IP));
        }



    }
}
