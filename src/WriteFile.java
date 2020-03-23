import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class WriteFile {
    private Calculator calculator;

    public WriteFile() throws SocketException, UnknownHostException {
        calculator = new Calculator();
    }

    public void writeFile(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Ip_values"));
            calculator.completeAddressValues();
            bufferedWriter.write("IP decimal and binary:");bufferedWriter.newLine();
            bufferedWriter.write(calculator.IP);bufferedWriter.newLine();
            bufferedWriter.write(calculator.ipToBinary(calculator.longIpAddress));
            bufferedWriter.newLine();
            bufferedWriter.write("Mask decimal and binary:");bufferedWriter.newLine();
            bufferedWriter.write(calculator.convertToDecValue(calculator.ipToBinary(calculator.longMask)));bufferedWriter.newLine();
            bufferedWriter.write(calculator.ipToBinary(calculator.longMask));
            bufferedWriter.newLine();
            bufferedWriter.write("Subnet address decimal and binary:");bufferedWriter.newLine();
            bufferedWriter.write(calculator.convertToDecValue(calculator.ipToBinary(calculator.subnetAddress)));bufferedWriter.newLine();
            bufferedWriter.write(calculator.ipToBinary(calculator.subnetAddress));
            bufferedWriter.newLine();
            bufferedWriter.write("First host address decimal and binary:");bufferedWriter.newLine();
            bufferedWriter.write(calculator.convertToDecValue(calculator.ipToBinary(calculator.firstHostAddress)));bufferedWriter.newLine();
            bufferedWriter.write(calculator.ipToBinary(calculator.firstHostAddress));
            bufferedWriter.newLine();
            bufferedWriter.write("Broadcast address decimal and binary:");bufferedWriter.newLine();
            bufferedWriter.write(calculator.convertToDecValue(calculator.ipToBinary(calculator.broadcastAddress)));bufferedWriter.newLine();
            bufferedWriter.write(calculator.ipToBinary(calculator.broadcastAddress));
            bufferedWriter.newLine();
            bufferedWriter.write("Last host address decimal and binary:");bufferedWriter.newLine();
            bufferedWriter.write(calculator.convertToDecValue(calculator.ipToBinary(calculator.lastHostAddress)));bufferedWriter.newLine();
            bufferedWriter.write(calculator.ipToBinary(calculator.lastHostAddress));
            bufferedWriter.newLine();
            bufferedWriter.write("Max host amount:");bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(calculator.maxHostAmount));
            bufferedWriter.newLine();
            bufferedWriter.write("Class type:");bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(calculator.determineClassType()));
            bufferedWriter.newLine();
            bufferedWriter.write("Is address public or private?");bufferedWriter.newLine();
            bufferedWriter.write(calculator.printIsIpAddressPublic());
            bufferedWriter.newLine();
            bufferedWriter.write("Is address correct?");bufferedWriter.newLine();
            bufferedWriter.write(calculator.printIsIpAddressCorrect());
            bufferedWriter.newLine();

            bufferedWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
