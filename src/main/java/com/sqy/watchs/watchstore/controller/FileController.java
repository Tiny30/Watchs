package com.sqy.watchs.watchstore.controller;



import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.FileWrapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.UUID;


@RestController
@RequestMapping("/file")
public class FileController extends HoshiController{
    //存储路径
    static String filePath = "D:\\fileTemp\\";
    /**
     * 文件上传
     * @param file
     * @return
     * @author：SQY
     * @dataTime:2020.03.28
     */
    @PostMapping("/upload")
    public RespData<String> load(@RequestParam("file") MultipartFile file){
        return $(resp -> {
            try {
                if (file.isEmpty()){
                    RespData.succeed(false).msg("file is empty");
                }
                //获取文件名
                String fileName = file.getOriginalFilename();
                fileName = URLDecoder.decode(fileName, "UTF-8") ;
                String ext = "";
                int i = fileName.lastIndexOf('.');
                if(i > 0){
                    ext = fileName.substring(i) ;
                }
                String fileId = UUID.randomUUID().toString().replace("-", "") ;
                fileName =  fileId+ ext;

                String path = filePath + fileName;
                File newFile = new File(path);
                //判断是否存在根目录
                if(!newFile.getParentFile().exists()){
                    newFile.getParentFile().mkdirs();
                }
                //上传
                file.transferTo(newFile);
                resp.success(true).data(fileName).msg("文件上传成功！");
            } catch (IOException e) {
                e.printStackTrace();
                resp.success(false).msg("上传失败");
            }
        });
    }

    @RequestMapping("/download/{fileId}/{ext}")
    public String downLoad(@PathVariable String fileId, @PathVariable String ext, HttpServletResponse response) throws UnsupportedEncodingException {
        String filename=fileId +"." +ext;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
