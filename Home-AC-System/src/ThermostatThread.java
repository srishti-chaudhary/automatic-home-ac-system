/**
 * @author srishtichaudhary
 */

import java.io.BufferedReader;
import java.io.FileReader;

public class ThermostatThread extends Thread{
    private Thread thread;
    private Thermostat thermostat;


    ThermostatThread(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    public void run(){
        try {
            String file = "Home-AC-System/src/dataFiles/testCase3.txt";//file path
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while(line != null){
                String[] data = line.split(", ");
                thermostat.setMode(Integer.parseInt(data[0]));
                thermostat.setSetTemp(Integer.parseInt(data[1]));
                if (data.length > 2) {
                    thermostat.setFanON(Boolean.parseBoolean(data[2]));
                }

                Thread.sleep(1000 * 30);
                line = br.readLine();
            }
            br.close();
        }
        catch(Exception e)        {
            System.out.print(e);
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "temp");
            thread.start();
        }
    }
}
