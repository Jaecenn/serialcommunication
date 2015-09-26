/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialcommunication;

import com.sun.jmx.snmp.BerDecoder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jssc.SerialPort.PURGE_RXCLEAR;
import static jssc.SerialPort.PURGE_TXCLEAR;
import jssc.SerialPortException;
import static serialcommunication.DataLogger.logger;
import static serialcommunication.DataLogger.serialPort;

/**
 *
 * @author Jaecen
 */
public class logData implements Runnable{
    
    static int iterationCount = 0;
    
        public void run() {
            
            int endLine;
            iterationCount = 0;
         //   int i1,i2,i3,i4,i5;
       while(!DataLogger.stopThread)
        //     while(true)
        {
           try {
                    //byte hexData[] = DataLogger.serialPort.readBytes(16);
                      
                 //     DataLogger.serialPort.purgePort(PURGE_RXCLEAR | PURGE_TXCLEAR);
               
               
                      String hex = DataLogger.serialPort.readHexString(DataLogger.numOfADC * 2 + (DataLogger.numOfADC-1) + 2);
                      //hex = hex.replaceAll("2C", ",");
                      hex=hex.replaceAll("\\s+","");
                      
                      
                      endLine = hex.indexOf("0A0D");
                      int offset = 4;
                      if (endLine < 0)
                      {
                          endLine = 0;
                          offset = 2;
                      }
                      String res = "";
                      int i = endLine+offset;
                          if(i>=hex.length())
                          {
                              i = i - hex.length();
                          }
                      boolean isOn = true;
                      String sub = "";
                      for (int iter = 0; iter < DataLogger.numOfADC; iter++) { //5
                          try{
                              if(i+4>hex.length())
                              {
                                  sub = hex.substring(i, i+2) + hex.substring(0, 0+2);
                                  
                                  //i = 0;
                              }else{
                                sub = hex.substring(i, i+4);  
                              }
                          
                          }catch(StringIndexOutOfBoundsException io)
                          {
                              offset = 4;
                          }
                          res = res + "," + sub;
                          i = i+6;
                          if(i>=hex.length())
                          {
                              i = i - hex.length();
                          }
               }
    
                     // res = res + ",";
                    //  int L = res.length();
                      
                      
                 //     i1 = Integer.parseInt(hex.substring(endLine+5, endLine+7)+hex.substring(endLine+8, endLine+10));
                      
                      
           /*           hex=hex.replaceAll("\\s+","");
                      String res="";
       //            DataLogger.out.write(hex);
                   for (int i = 0; i < 28; i=i+4) {
                       String sub = hex.substring(i, i+4);
                   int i1 = Integer.parseInt(sub, 16);

                    res = res + "," + i1; 
                   i = i + 2;        
               }
                  */
                   
                   if (iterationCount == 511)   
                   {
                       iterationCount= 0;
                    }
                    iterationCount++;  
                  
                   if (res.contains("2C"))
                   {
                       iterationCount = 0;
                   }
                    
                    
                   logger.log(Level.FINEST, res);    
                   
                   
                   
                  // logger.log(Level.FINEST, hex);                   
                   //logger.info(res);                   
                   
               } catch (SerialPortException ex) {
                   Logger.getLogger(logData.class.getName()).log(Level.SEVERE, null, ex);
               }
            
           
            //System.out.println("Hello from a thread!");
        }
       
       DataLogger.stopThread = false;
    }
                           /*if (i == 0)
                   {
                    res = res + i1;   
                   }
                   else{
                     
                   }*/
}
