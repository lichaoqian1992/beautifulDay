package com.manji.messageserver.utils;


import com.google.gson.*;
import com.manji.messageserver.dao.entity.MessageDO;
import com.manji.messageserver.dao.entity.MessageDetailDO;
import com.manji.messageserver.dao.repository.MessageDAO;
import com.manji.messageserver.dao.repository.MessageDetailDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
/**
 * Created by pudding on 2016-12-17.
 */

@Component
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MessageDetailDAO messageDetailDAO;

    public static final int cache = 10 * 1024;

    public synchronized void decompress(String inFilePath, String outFilePath) {
        GZIPInputStream gzipInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            System.out.print(inFilePath + "---------" + outFilePath);
            InputStream inputStream = new FileInputStream(inFilePath);
            gzipInputStream = new GZIPInputStream(inputStream);
            OutputStream fileOutputStream = new FileOutputStream(outFilePath);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] data = new byte[cache];
            int count = 0;
            while ((count = gzipInputStream.read(data)) != -1) {
                bufferedOutputStream.write(data, 0, count);
            }
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                gzipInputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void readTxtFile(String filePath) {
        InputStreamReader read = null;
        String encoding = "utf-8";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) { //判断文件是否存在
            try {
                read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式

                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    JsonObject asJsonObject = new JsonParser().parse(lineTxt).getAsJsonObject();
                    logger.info("读取数据 json={}",asJsonObject);
                    saveMessage(asJsonObject);

                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Transactional
    private synchronized void saveMessage(JsonObject jsonObject){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String msg_id=jsonObject.get("msg_id").getAsString();
        try {
            String from = jsonObject.get("from").getAsString();
            String to = jsonObject.get("to").getAsString();
            String chat_type = jsonObject.get("chat_type").getAsString();
            long timestamp = jsonObject.get("timestamp").getAsLong();

            MessageDO messageDO=new MessageDO();
            messageDO.setMsgId(msg_id);
            messageDO.setChatType(chat_type);
            messageDO.setCome(from);
            messageDO.setTimestamp(sdf.parse(sdf.format(new Date(timestamp))));
            messageDO.setTo(to);
            messageDO.setType("chatmessage");


            Iterator<JsonElement> iterator = jsonObject.get("payload").getAsJsonObject().get("bodies").getAsJsonArray().iterator();

            MessageDetailDO detailDO = new MessageDetailDO();

            while (iterator.hasNext()){
                JsonElement next = iterator.next();
                JsonObject asJsonObject = next.getAsJsonObject();
                detailDO.setMsgId(msg_id);
                if(asJsonObject!=null){
                    JsonElement type = asJsonObject.get("type");
                    if(type!=null){
                        detailDO.setType(type.getAsString());
                    }
                    JsonElement msg = asJsonObject.get("msg");
                    if(msg!=null){
                        detailDO.setMsg(msg.getAsString());
                    }
                    JsonElement url = asJsonObject.get("url");
                    if(url!=null){
                        detailDO.setUrl(url.getAsString());
                    }
                    JsonElement filename = asJsonObject.get("filename");
                    if(filename!=null){
                        detailDO.setFilename(filename.getAsString());
                    }
                    JsonElement thumb = asJsonObject.get("thumb");
                    if(thumb!=null){
                        detailDO.setThumb(thumb.getAsString());
                    }
                    JsonElement secret = asJsonObject.get("secret");
                    if(secret!=null){
                        detailDO.setSecret(secret.getAsString());
                    }
                    JsonElement thumb_secret = asJsonObject.get("thumb_secret");
                    if(thumb_secret!=null){
                        detailDO.setThumbSecret(thumb_secret.getAsString());
                    }
                    JsonElement length = asJsonObject.get("length");
                    if(length!=null){
                        detailDO.setLength(length.getAsInt());
                    }
                    JsonElement addr = asJsonObject.get("addr");
                    if(addr!=null){
                        detailDO.setAddr(addr.getAsString());
                    }
                    JsonElement lat = asJsonObject.get("lat");
                    if(lat!=null){
                        detailDO.setLat(lat.getAsDouble());
                    }
                    JsonElement lng = asJsonObject.get("lng");
                    if(lng!=null){
                        detailDO.setLng(lng.getAsDouble());
                    }
                    JsonElement file_length = asJsonObject.get("file_length");
                    if(file_length!=null){
                        detailDO.setFileLength(file_length.getAsLong());
                    }
                }
            }
            logger.info("数据入库-messageDo={} detailDO={}",messageDO,detailDO);
            messageDAO.save(messageDO);
            messageDetailDAO.save(detailDO);
        }catch (ParseException e) {
            logger.error("数据入库失败 msgId={} errorMessage={}",msg_id,e.getMessage());
        }
    }
}
