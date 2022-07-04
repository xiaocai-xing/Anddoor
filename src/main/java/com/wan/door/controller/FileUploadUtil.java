package com.wan.door.controller;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPOutputStream;
import ch.ethz.ssh2.SFTPv3Client;
import com.jcraft.jsch.*;
import com.wan.door.common.ResultEntity;
import com.wan.door.common.ScpConnectEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: create by xiaocai-xing
 * @TODO: 上传文件工具类
 * @description: com.wan.door.controller
 * @date:2022/6/28
 */

@Configuration
public class FileUploadUtil {
    @Value("119.45.171.139")
    private String remoteurl;
    @Value("root")
    private String remoteusername;
    @Value("Charles0219")
    private String remotepassword;

    @Async
    //targetPath:目标路径
    //file:上传文件
    //remoteFileName：创建远程服务器文件名
    public ResultEntity uploadFile(File file, String targetPath, String remoteFileName) throws Exception{
        //创建实例
        ScpConnectEntity scpConnectEntity = new ScpConnectEntity();
        scpConnectEntity.setTargetPath(targetPath);

//        scpConnectEntity.setRemoteurl(remoteurl);
//        scpConnectEntity.setRemotepassWord(remotepassword);
//        scpConnectEntity.setRemoteuserName(remoteusername);


        scpConnectEntity.setRemoteurl("119.45.171.139");
        scpConnectEntity.setRemotepassWord("Charles0219");
        scpConnectEntity.setRemoteuserName("root");

        //定义返回值
        String code = null;
        String message = null;


        //检验上传配置信息
        try{
            if (file == null || !file.exists()){
                throw new IllegalArgumentException("请确保上传文件不为空且存在");
            }
            if (remoteFileName ==null ||"".equals(remoteFileName.trim())){
                throw new IllegalArgumentException("远程服务器新建文件名不能为空!");
            }
            remoteUploadFile(scpConnectEntity, file, remoteFileName);
            code = "ok";
            message = remoteFileName;
        } catch (IllegalArgumentException e){
            code = "Exception";
            message = e.getMessage();
        }catch (JSchException e){
            code = "Exception";
            message = e.getMessage();
        }catch (IOException e){
            code = "Exception";
            message = e.getMessage();
        }catch (Exception e){
            throw e;
        }catch (Error e){
            code = "Error";
            message = e.getMessage();
        }
        return new ResultEntity(code,message,null);

    }

    //执行方法
    private void remoteUploadFile(ScpConnectEntity scpConnectEntity, File file, String remoteFileName) throws JSchException,IOException{
        Connection connection = null;
        ch.ethz.ssh2.Session session = null;
        SCPOutputStream scpOutputStream = null;
        FileInputStream fileInputStream = null;
        try{
            createDir(scpConnectEntity);
        }catch (JSchException e){
            throw e;
        }
        try {
            connection = new Connection(scpConnectEntity.getRemoteurl());
            connection.connect();
            if (!connection.authenticateWithPassword(scpConnectEntity.getRemoteuserName(),scpConnectEntity.getRemotepassWord())){
                throw  new RuntimeException("SSH连接服务器失败");
            }
            SCPClient scpClient = connection.createSCPClient();
            scpOutputStream = scpClient.put(remoteFileName, file.length(), scpConnectEntity.getTargetPath(),null);
            fileInputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int hasMore = fileInputStream.read(buf);
            while (hasMore !=-1){
                scpOutputStream.write(buf);
                hasMore = fileInputStream.read(buf);
            }
            session = connection.openSession();
            String cmd = "chmod +r"+scpConnectEntity.getTargetPath()+remoteFileName;
            session.execCommand(cmd);
        }catch (IOException e){
            throw new IOException("SSH上传文件至服务器出错: "+e.getMessage());
        }finally {
            if (null !=fileInputStream){
                try{
                    fileInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (null !=scpOutputStream){
                try{
                    scpOutputStream.flush();
                    scpOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (null !=session){
                session.close();
            }
            if (null !=connection){
                connection.close();
            }
        }
    }

    private boolean createDir(ScpConnectEntity scpConnectEntity) throws JSchException{
        JSch jSch = new JSch();
        Session sshSession = null;
        Channel channel = null;
        try{
            sshSession = jSch.getSession(scpConnectEntity.getRemoteuserName(),scpConnectEntity.getRemoteurl(),22);
            sshSession.setPassword(scpConnectEntity.getRemotepassWord());
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            channel = sshSession.openChannel("sftp");
            channel.connect();
        }catch (JSchException e){
            e.printStackTrace();
            throw new JSchException("SFTP连接服务器失败"+e.getMessage());
        }
        ChannelSftp channelSftp = (ChannelSftp) channel;
        if (isDirExist(scpConnectEntity.getTargetPath(),channelSftp)){
            channel.disconnect();
            channelSftp.disconnect();
            sshSession.disconnect();
            return true;
        }else {
            String pathArry[] = scpConnectEntity.getTargetPath().split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path:pathArry){
                if (path.equals("")){
                    continue;
                }
                filePath.append(path+"/");
                try{
                    if (isDirExist(filePath.toString(),channelSftp)){
                        channelSftp.cd(filePath.toString());
                    }else {
                        channelSftp.mkdir(filePath.toString());
                        channelSftp.cd(filePath.toString());
                    }
                }catch (SftpException e){
                    e.printStackTrace();
                    throw new JSchException("SFTP无法正常操作服务器"+e.getMessage());
                }
            }
        }
        channel.disconnect();
        channelSftp.disconnect();
        sshSession.disconnect();
        return true;
    }

    private boolean isDirExist(String directory, ChannelSftp channelSftp) {

        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = channelSftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    //测试方法
//    public ResultEntity uploadfiletwo(File file, String targetPath, String remoteFileName) throws JSchException {
//
//        ResultEntity resultEntity = new ResultEntity();
//        Connection connection = new Connection("119.45.171.139",22);
//        JSch jsch = new JSch();
//        Session session = jsch.getSession("root", "119.45.171.139", 22);
//        session.setPassword("Charles0219");
//        session.setConfig("StrictHostKeyChecking", "no");
//        session.connect(3000);
//
//
//        String filepath = "/Users/hunliji/新人问卷.jmx的副本";
//        File f = new File(filepath);
//
//        try(FileInputStream fis = new FileInputStream(f)){
//            connection.connect();
//            boolean isAuthenticated = connection.authenticateWithPassword("root","Charles0219");
//            if (isAuthenticated){
//                System.out.println("失败");
//            }
//            SCPClient scpClient = new SCPClient(connection);
//            SFTPv3Client sftPv3Client = new SFTPv3Client(connection);
//
//            SCPOutputStream os = scpClient.put(remoteFileName,f.length(),targetPath,null);
//            byte[] b =new byte[4096];
//            int i ;
//            while ((i = fis.read(b))!=-1){
//                os.write(b,0,i);
//            }
//            String cmd = "chmod +r "+targetPath+f.getName();
//            session.openChannel(cmd);
//            os.flush();
//            fis.close();
//            os.close();
//            connection.close();
//
//
//        }catch (IOException e){
//            resultEntity.setMessage(e.getMessage());
//        }
//        return resultEntity;
//    }



}
