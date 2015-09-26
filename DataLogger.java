/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialcommunication;

import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jssc.SerialPort;
import static jssc.SerialPort.PURGE_RXCLEAR;
import static jssc.SerialPort.PURGE_TXCLEAR;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

/**
 *
 * @author Jaecen
 */
public class DataLogger extends javax.swing.JFrame {

    JFileChooser jf;
    String cestaSlozky;
    int numberOfFile = 1;
    public static SerialPort serialPort;
    boolean isConnected = false;
    boolean isChosenFolder = true;
    Thread logThread;
    static boolean stopThread = false;
    boolean isAlreadySet = false;
    String sampleRateSetting = "";
    int onADCs = 0;
    public static int numOfADC = 0;
    int numOfLines = 0;   
    String pathOfFile;
  //  public static Writer out;
    boolean isStarted = false;
    
    static final Logger logger = Logger.getLogger("MyLog");  
    FileHandler fh;
    /**
     * Creates new form DataLogger
     */
    public DataLogger() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        StartButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        popisekVybranaSlozka = new javax.swing.JLabel();
        jLabelCOM = new javax.swing.JLabel();
        jTextCOM = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ConnectButton = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioVoltage = new javax.swing.JRadioButton();
        jRadioADCVal = new javax.swing.JRadioButton();
        jSampleRate = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        StartButton.setText("Start Measurment");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Destination Folder");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        popisekVybranaSlozka.setText("...");

        jLabelCOM.setText("Selected Port:");

        jTextCOM.setText("COM7");
        jTextCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCOMActionPerformed(evt);
            }
        });

        jLabel1.setText("Sample rate [Hz]:");

        ConnectButton.setText("Connect and set");
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        labelStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelStatus.setText("Waiting for connection to CV_box");

        jLabel3.setText("v1.3");

        buttonGroup1.add(jRadioVoltage);
        jRadioVoltage.setText("Voltage [V]");
        jRadioVoltage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioVoltageActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioADCVal);
        jRadioADCVal.setSelected(true);
        jRadioADCVal.setText("ADC Value");

        jSampleRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1000", "1500", "1700", "2000", "2500", "3000", "3500", "4000", "4500", "5000" }));

        jLabel4.setText("Result as:");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("ADC1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("ADC2");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox4.setSelected(true);
        jCheckBox4.setText("ADC3");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setSelected(true);
        jCheckBox5.setText("ADC4");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("ADC5");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ConnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(popisekVybranaSlozka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelCOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(11, 11, 11)
                                                .addComponent(jTextCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSampleRate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioADCVal)
                                            .addComponent(jRadioVoltage)
                                            .addComponent(jLabel4))))
                                .addGap(14, 14, 14)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(popisekVybranaSlozka))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextCOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jSampleRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioVoltage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioADCVal)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(ConnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // TODO add your handling code here:
        
        if (!isStarted)
        {
        if(!serialPort.isOpened())
        {
            try {
                serialPort.openPort();
                serialPort.purgePort(PURGE_RXCLEAR | PURGE_TXCLEAR);
            } catch (SerialPortException ex) {
                Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                        
        
        
        pathOfFile = cestaSlozky + "/" + "dataLog_"+jSampleRate.getSelectedItem().toString()+"Hz_" + numberOfFile + ".csv";
        File givenFile = new File(pathOfFile);
        while (givenFile.exists()) {
            numberOfFile++;
            pathOfFile = cestaSlozky + "/" + "dataLog_"+jSampleRate.getSelectedItem().toString()+"Hz_" + numberOfFile + ".csv";
            givenFile = new File(pathOfFile);
        }

        pathOfFile = cestaSlozky + "/" + "dataLog_"+jSampleRate.getSelectedItem().toString()+"Hz_" + numberOfFile + "_temp.csv";
        
        try {
           /* out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(givenFile), "UTF8"));*/
        //    FileWriter out = new FileWriter(givenFile);
                    
                fh = new FileHandler(pathOfFile);  
                logger.addHandler(fh);
                //SimpleFormatter formatter = new SimpleFormatter();  
                BriefFormatter formatter = new BriefFormatter();
                //formatter.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n");
                //formatter.format = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n";
                fh.setFormatter(formatter);  

        // the following statement is used to log any messages  
        //logger.info("My first log");  
            logger.setLevel(Level.FINEST);
           // logger.info("ADC1,ADC2,ADC3,ADC4,ADC5");
            //logger.log(Level.FINEST, ",ADC1,ADC2,ADC3,ADC4,ADC5");
            logger.log(Level.FINEST,getHeader());
            labelStatus.setText("Measure started - logging to "+"dataLog_"+jSampleRate.getSelectedItem().toString()+"Hz_" + numberOfFile + ".csv");
         
            //="%4$s: %5$s [%1$tc]%n";
            //fw.
          //  out.write("ADC1,ADC2,ADC3,ADC4,ADC5");
          //  out.close();
           // out.
                (new Thread(new logData())).start();                

            StartButton.setText("Stop");
            isStarted = true;
            ConnectButton.setEnabled(false);
            jButton2.setEnabled(false);
   
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        }
        else
        {
            
            stopThread = true;
            
            fh.close();
            
            Thread t = new Thread(new Runnable() {
             @Override
             public void run() {
             labelStatus.setText("Conversion from HEX -> DEC");
             changeFile(pathOfFile);
             labelStatus.setText("FINISHED - data saved in file: "+"dataLog_"+jSampleRate.getSelectedItem().toString()+"Hz_" + numberOfFile + ".csv");
             }     
            });
             t.start();
          
             BriefFormatter.nuOfSample = 0;
           // repaint();
            
            
            logger.removeHandler(fh);
            StartButton.setText("Start");
            //StartButton.setEnabled(false);
            ConnectButton.setEnabled(true);
            ConnectButton.setText("Disconnect");
            jButton2.setEnabled(true);
            isStarted = false;
            //labelStatus.setText("Measure has finished, connect and START new one");
            labelStatus.setText("FINISHED - data saved in file: "+"dataLog_"+jSampleRate.getSelectedItem().toString()+"Hz_" + numberOfFile + ".csv");
           // ConnectButton.setText("Connect and set");
            
            if(serialPort.isOpened())
            {
                try {
                  //  serialPort.writeString("STOP");
                    serialPort.closePort();
                } catch (SerialPortException ex) {
                    Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            isConnected = true;
            
        }
    }//GEN-LAST:event_StartButtonActionPerformed

    void changeFile(String pathToFile)
    {
        BufferedReader br = null;
        BufferedWriter bw = null;
        numOfLines = 1;
        
        try {
            br = new BufferedReader(new FileReader(pathToFile));
            File file = new File(pathToFile.substring(0, pathToFile.length()-9)+".csv");
            if (!file.exists())
            {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            
            
            
            String tempStr = "";
           
            tempStr = br.readLine();
            bw.write(tempStr+"\r\n");
            
            tempStr = br.readLine();
            int id = 0;
            
            while( tempStr != null)
            {
            labelStatus.setText("Conversion from HEX -> DEC");
            String strVal, outStr = "";
            
            int indOfComma = 0, indOfNextComma = 0;
            for (int i = 0; i < numOfADC+1; i++) {
               if (i < 1)
               {
                   indOfComma = 0;
                   indOfNextComma = tempStr.indexOf(',',indOfComma);
                strVal = tempStr.substring(indOfComma,indOfNextComma);
                outStr = strVal;
               } 
               else
               {
                   indOfComma = tempStr.indexOf(',',indOfComma+1);
                   indOfNextComma = tempStr.indexOf(',',indOfComma+1);
                   if(indOfNextComma < 0) indOfNextComma = tempStr.length();
                strVal = tempStr.substring(indOfComma+1,indOfNextComma);
                int i1 = Integer.parseInt(strVal, 16);
                if (buttonGroup1.isSelected(jRadioVoltage.getModel())){
                outStr = outStr + "," + convertToVoltage(i1);
                }
                else if (buttonGroup1.isSelected(jRadioADCVal.getModel()))
                        {
                             outStr = outStr + "," + i1;
                        }
               }     
            }
            outStr = outStr + "\r\n";
            
//                System.out.println("Number: "+id+"\n");
//                System.out.println(outStr);
            id++;
                bw.write(outStr);
                numOfLines ++;
                tempStr = br.readLine();
            }
            
            br.close();
            bw.close();
            
            File in = new File(pathToFile);
            if (in.exists())
            {
                in.delete();
            }
//            file.
            if (numOfLines<2)
            {
                isConnected = false;
                ConnectButton.setEnabled(true);

                StartButton.setEnabled(false);               
                labelStatus.setText("Measurement ERROR - please restart device");
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
 
        
        
        
    }
    
    String convertToVoltage(int adcVal)
    {
        
        double vRef= 3.3;
        double result = ((double)adcVal/4095) * vRef;
        return round(result,5);
    }
    
    public static String round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.toPlainString();
    //return bd.doubleValue();
}
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        
        if (jf != null) {
            jf.showOpenDialog(jButton2);

            if (jf.getSelectedFile() != null) {
                cestaSlozky = jf.getSelectedFile().getAbsolutePath();
                popisekVybranaSlozka.setText(cestaSlozky);
                //isChosenFolder = true;
                numberOfFile = 1;
             ///   if (isConnected) {
              //      StartButton.setEnabled(true);
              //  }
                //this.setSize(400+cestaSlozky.length()*4, 215);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCOMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCOMActionPerformed

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        
        String answer = "";
        int response = JOptionPane.YES_OPTION;
        
        if(!isConnected)
        {
            

            
        try {
            // TODO add your handling code here:
           //stopThread = true;
            int currentADCs = 0;
            currentADCs  |= (booleanToInt(jCheckBox1.isSelected()));
            currentADCs  |= (booleanToInt(jCheckBox2.isSelected()) << 1);
            currentADCs  |= (booleanToInt(jCheckBox4.isSelected()) << 2);
            currentADCs  |= (booleanToInt(jCheckBox5.isSelected()) << 3);
            currentADCs  |= (booleanToInt(jCheckBox6.isSelected()) << 4);
                    
            if((isAlreadySet && !sampleRateSetting.contentEquals(jSampleRate.getSelectedItem().toString())) || (isAlreadySet && currentADCs!=onADCs))
            {
                response = JOptionPane.showConfirmDialog(this, "Changing parameters requires hw reset of CV_box!\n Have you done reset?", "Reset of CV_box needed", JOptionPane.OK_CANCEL_OPTION);               
                if (response == JOptionPane.YES_OPTION) numberOfFile = 1;
            }            
            if (response == JOptionPane.YES_OPTION)
            {
            serialPort = new SerialPort(jTextCOM.getText());
            if(!serialPort.isOpened()) serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_256000,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);                        
            serialPort.purgePort(PURGE_RXCLEAR | PURGE_TXCLEAR);
            
            if(!(isAlreadySet && sampleRateSetting.contentEquals(jSampleRate.getSelectedItem().toString()) && currentADCs==onADCs))
            {
            onADCs  |= (booleanToInt(jCheckBox1.isSelected()));
            onADCs  |= (booleanToInt(jCheckBox2.isSelected()) << 1);
            onADCs  |= (booleanToInt(jCheckBox4.isSelected()) << 2);
            onADCs  |= (booleanToInt(jCheckBox5.isSelected()) << 3);
            onADCs  |= (booleanToInt(jCheckBox6.isSelected()) << 4);
            
            numOfADC = booleanToInt(jCheckBox1.isSelected())+booleanToInt(jCheckBox2.isSelected())+booleanToInt(jCheckBox4.isSelected())+booleanToInt(jCheckBox5.isSelected())+booleanToInt(jCheckBox6.isSelected());
            
            
            String picked = ""+booleanToString(jCheckBox6.isSelected())+booleanToString(jCheckBox5.isSelected())+booleanToString(jCheckBox4.isSelected())+booleanToString(jCheckBox2.isSelected())+booleanToString(jCheckBox1.isSelected());
            serialPort.writeString(picked);
            answer = serialPort.readString(8,0xFFFF);    
            if(answer.contentEquals("OK__OK__"))
            {
            
            picked = jSampleRate.getSelectedItem().toString();
            serialPort.writeString(picked);
            //selected
            answer = serialPort.readString(8,0xFFFF);
            }
            
            if(answer.contentEquals("OK__OK__"))
            {
                sampleRateSetting = picked;
                isAlreadySet = true;
                isConnected = true;
                labelStatus.setText("Connection succesful - START your measurement");
         //   if (isChosenFolder) {
                StartButton.setEnabled(true);
                ConnectButton.setText("Disconnect");
            }
            else
            {
                isConnected = false;
                ConnectButton.setEnabled(true);

                StartButton.setEnabled(false);
                serialPort.closePort();
                labelStatus.setText("Connection ERROR - CV_box is busy, please restart device");
               
            }
            }
            else
            {
                
                isConnected = true;
                labelStatus.setText("Connection succesful - START your measurement");
         //   if (isChosenFolder) {
                StartButton.setEnabled(true);
                ConnectButton.setText("Disconnect");
                serialPort.closePort();
            }
                    //serialPort.readString(i, i)
         
            
            }
            
            

            //}

        } 
        catch (SerialPortTimeoutException ex)
        {
            isConnected = false;
            ConnectButton.setEnabled(true);

            StartButton.setEnabled(false);

            labelStatus.setText("Connection ERROR - CV_box is busy, please restart device");
            Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SerialPortException ex) {
            //isConnected = false;
            ConnectButton.setEnabled(true);

            StartButton.setEnabled(false);

            labelStatus.setText("Connection ERROR - check selected port");
            Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);

        }
        }else{
            if(serialPort.isOpened())
            {  
                try {
                 //   serialPort.writeString("STOP");
                    serialPort.closePort();
                } catch (SerialPortException ex) {
                    Logger.getLogger(DataLogger.class.getName()).log(Level.SEVERE, null, ex);
                }
                serialPort = null;
            }
            ConnectButton.setText("Connect and set");
            isConnected = false;
            StartButton.setEnabled(false);
            labelStatus.setText("Disconnected - waiting for connection");
        }
    }//GEN-LAST:event_ConnectButtonActionPerformed

    String getHeader()
    {
      String res= ",";
      
        for (int i = 1; i < 6; i++) {
            if (i==1 && jCheckBox1.isSelected()) res=res+"ADC"+i+",";
            if (i==2 && jCheckBox2.isSelected()) res=res+"ADC"+i+",";
            if (i==3 && jCheckBox4.isSelected()) res=res+"ADC"+i+",";
            if (i==4 && jCheckBox5.isSelected()) res=res+"ADC"+i+",";
            if (i==5 && jCheckBox6.isSelected()) res=res+"ADC"+i+",";
        }
        res = res.substring(0, res.length()-1);
        return res;
    }
    
    
    String booleanToString(boolean myBoolean)
    {
        int b = (myBoolean) ? 1 : 0;       
        return Integer.toString(b);
    }
    
    int booleanToInt(boolean myBoolean)
    {
       return (myBoolean) ? 1 : 0;       
       
    }
    
    
    private void jRadioVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioVoltageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioVoltageActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
       limitSampleRates();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        limitSampleRates();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        limitSampleRates();
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        limitSampleRates();
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        limitSampleRates();
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    void limitSampleRates()
    {
        numOfADC = booleanToInt(jCheckBox1.isSelected())+booleanToInt(jCheckBox2.isSelected())+booleanToInt(jCheckBox4.isSelected())+booleanToInt(jCheckBox5.isSelected())+booleanToInt(jCheckBox6.isSelected());
        
        jSampleRate.removeAllItems();
//        JComboBox channels1 = new JComboBox({"1000", "1500", "1900", "2000", "2400", "3100", "3500", "4000", "4400", "7000"});

        switch(numOfADC)
                {
            case 1: jSampleRate.addItem((String) "7000");
                    
            case 2: jSampleRate.addItem((String) "4400"); 
                    jSampleRate.addItem((String) "4000"); 
                    jSampleRate.addItem((String) "3500");
                
            case 3: jSampleRate.addItem((String) "3100"); 
                
            case 4: jSampleRate.addItem((String) "2400"); 
                    jSampleRate.addItem((String) "2000"); 
                        
            case 5: jSampleRate.addItem((String) "1900");
                    jSampleRate.addItem((String) "1500");
                    jSampleRate.addItem((String) "1000");               
            default: break;
                }
    }
//    void disableWhileConnected()
//    {
//    jSampleRate.setEnabled(false);
//    jTextCOM.setEnabled(false);
//    jRadioVoltage.setEnabled(false);
//    }
    private void init() {
        // Max fs: 5 kanalu 2000Hz, 4 - 2461, 3- 3200, 2- 4571, 1- 8000

        
        
        String defaultPath = "D:\\Ostatní\\Test_app";
//        String defaultPath = "C:\\";
        limitSampleRates();
        jSampleRate.setSelectedIndex(0);
        cestaSlozky = defaultPath;
        jf = new JFileChooser(defaultPath);
        popisekVybranaSlozka.setText(defaultPath);
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        StartButton.setEnabled(false);

//         popisekVybranaSlozka.setText;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataLogger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectButton;
    private javax.swing.JButton StartButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCOM;
    private javax.swing.JRadioButton jRadioADCVal;
    private javax.swing.JRadioButton jRadioVoltage;
    private javax.swing.JComboBox jSampleRate;
    private javax.swing.JTextField jTextCOM;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel popisekVybranaSlozka;
    // End of variables declaration//GEN-END:variables
}
