package org.leti.Utils;


import org.json.JSONObject;
import org.leti.Domain.Driver;
import org.leti.Domain.Storage;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Json {

    //создает txt файл из JSON Объекта в папке resources/im_doc_3
    public static void  createJsonFile(Driver driver, Storage storage){
        String str = "src\\main\\resources\\im_doc_3";
        File dir = new File(str);
        String billNumber = ""; //random
        for (int i = 0; i < 6; i++) {
            billNumber+= (int) (Math.random()*10);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //current

        String operationNumber = storage.getId().toString(); //storage.getId()
        String autoNum = driver.getAutoNum();
        String driverName = driver.getName();
        String driverLoading = driver.getContainer_id().getAddress();
        String driverUnloading = storage.getAddress();

        String containerType = driver.getContainer_id().getContainerType();
        String containerId = driver.getContainer_id().getId().toString();
        String weight =String.valueOf ((int) ( Math.random() * 100 + Math.random() * 100 ));

        int minutes = (int) (Math.random() * 5 + 5);
        int seconds = (int) (Math.random() * 60);

        String timeLoading = minutes + ":" +  (seconds<10? ("0" + seconds) : seconds) ;
        minutes = (int) (Math.random() * 5 + 5);
        seconds = (int) (Math.random() * 60);
        String timeUnloading = minutes + ":" +  (seconds<10? ("0" + seconds) : seconds);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("billNumber", billNumber);
        jsonObject.put("hash", makeSHA1Hash(operationNumber));
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("operationNumber", operationNumber);
        jsonObject.put("autoNum", autoNum);
        jsonObject.put("driverName", driverName);
        jsonObject.put("driverLoading", driverLoading);
        jsonObject.put("driverUnloading", driverUnloading);

        jsonObject.put("containerType", containerType);
        jsonObject.put("containerId", containerId);
        jsonObject.put("weight", weight);
        jsonObject.put("timeLoading", timeLoading);
        jsonObject.put("timeUnloading", timeUnloading);
        jsonObject.put("counterPartyDriverName", driver.getCounterparty().getName());
        jsonObject.put("counterPartyDriverInn", driver.getCounterparty().getInn());
        jsonObject.put("counterPartyStorageName", storage.getCounterparty().getName());
        jsonObject.put("counterPartyStorageInn", storage.getCounterparty().getInn());

        File file = new File(str + "\\im_doc_" + storage.getId()  + ".txt");

        BufferedWriter bf = null;
        ;

        try {

            //create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));
            bf.write(jsonObject.toString());

            bf.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }


    //сгенерировать хэш-код для документа по номеру накладной
    public static String makeSHA1Hash(String input)
    {
        String hexStr = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.reset();
            byte[] buffer = input.getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();



            for (int i = 0; i < digest.length; i++) {
                hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hexStr;
    }

    //парсинг файла по ID документа
    public static Map<String,String> getFileInfo(Long n) {
        Map<String,String> map = new HashMap<>();
        String path = "src\\main\\resources\\im_doc_3\\im_doc_" + n  + ".txt";
        String jsonStr = parseFileToString(path);
        JSONObject rootObject = new JSONObject(jsonStr);
        map.put("driverLoading",rootObject.get("driverLoading").toString());
        map.put("operationNumber",rootObject.get("operationNumber").toString());
        map.put("containerType",rootObject.get("containerType").toString());
        map.put("timeUnloading",rootObject.get("timeUnloading").toString());
        map.put("driverUnloading",rootObject.get("driverUnloading").toString());
        map.put("weight",rootObject.get("weight").toString());
        map.put("driverName",rootObject.get("driverName").toString());
        map.put("billNumber",rootObject.get("billNumber").toString());
        map.put("containerId",rootObject.get("containerId").toString());
        map.put("timeLoading",rootObject.get("timeLoading").toString());
        map.put("autoNum",rootObject.get("autoNum").toString());
        map.put("timestamp",rootObject.get("timestamp").toString());

        map.put("counterPartyDriverName",rootObject.get("counterPartyDriverName").toString());
        map.put("counterPartyDriverInn",rootObject.get("counterPartyDriverInn").toString());
        map.put("counterPartyStorageName",rootObject.get("counterPartyStorageName").toString());
        map.put("counterPartyStorageInn",rootObject.get("counterPartyStorageInn").toString());

        return map;
    }

    public static String parseFileToString(String path){
        String res = "";

        try(FileReader reader = new FileReader(path))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                res+= (char) c;
            }
        }
        catch(IOException ex){ }

        return res;
    }


}
