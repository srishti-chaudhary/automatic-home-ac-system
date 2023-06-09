/**
 * TempController - Handle temperature logic and activate AC hardware
 */

public class TempController {
    //state variables
    private int mode;           //Mode indicator, defined as an enum (0 - Off, 1 - Cool, 2 - Heat, 3 - Auto)
    private int temp;           //Current room temperature
    private boolean coolerAck;  //Event to be sent to the cooler
    private boolean heaterAck;  //Event to be sent to the heater

    //inputs
    private int avgTemp;

    /**
     * Class constructor
     */
    public TempController(int avgTemp){
        this.mode = 1;
        this.temp = 25;
        this.avgTemp = avgTemp;
        this.coolerAck = false;
        this.heaterAck = false;
    }

    /**
     * Update function, called any time setMode, setTemp, or avgTemp are changed
     */
    public void update(){
        //if in cool or auto mode
        if(mode == 1 || mode == 3) {
            if(temp <= avgTemp - 1 && !coolerAck){
                setCoolerAck(true);
                setHeaterAck(false);
            } else if (temp > avgTemp - 1  && coolerAck) {
                coolerAck = false;
            }
        }

        //if in heat or auto mode
        if(mode == 2 || mode == 3) {
            if(temp >= avgTemp + 1 && !heaterAck){
                setCoolerAck(false);
                setHeaterAck(true);
            } else if (temp < avgTemp + 1  && heaterAck) {
                heaterAck = false;
            }
        }

        //this statement is only reachable if mode == 0
        setCoolerAck(false);
        setHeaterAck(false);
    }

    public void setMode(int setMode){
        this.mode = setMode;
        update();
    }

    public void setTemp(int setTemp){
        this.temp = setTemp;
        update();
    }

    public void setAvgTemp(int temp){
        avgTemp = temp;
        update();
    }

    public void setCoolerAck(boolean coolerAck) {
        this.coolerAck = coolerAck;
    }

    public void setHeaterAck(boolean heaterAck) {
        this.heaterAck = heaterAck;
    }

    public boolean getCoolerState() { return this.coolerAck; }

    public boolean getHeaterState() { return this.heaterAck; }
}