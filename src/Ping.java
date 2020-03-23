import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ping {
    public boolean sendPingRequest(String ipAddress) {
        try{
            String cmd = "";
            if(System.getProperty("os.name").startsWith("Windows")) {
                // For Windows
                cmd = "ping -n 4 " + ipAddress;
            }

            Process myProcess = Runtime.getRuntime().exec(cmd);
            myProcess.waitFor();

            BufferedReader r = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }

            if(myProcess.exitValue() == 0) {

                return true;
            } else {

                return false;
            }

        } catch( Exception e ) {

            e.printStackTrace();
            return false;
        }
    }
}

