package com.example.demo.util;

import com.example.demo.util.exception.CustomException;
import com.example.demo.util.exception.ErrorCode;
import com.example.demo.util.exception.ErrorMessage;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youzi
 * @date 2018-06-05 16:18
 */
public class HttpUtil {
    /**
     * post请求
     *
     * @param urlString   访问的url地址
     * @param paramString 参数封装成map的Json字符串
     * @param charset 编码
     * @return
     */
    public static String postRequest(String urlString, String paramString,String charset) {
        String result = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            connection.connect();

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.append(paramString);
            out.flush();
            out.close();
            int length = connection.getContentLength();
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                result = new String(data, charset);
                System.out.println("result:" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }
    
    /**
    *@Author FeiChen
    *@Description 获取请求参数
    *@Date 17:45 2018/6/11
    *@Param [request]
    *@return java.util.Map
    **/
    public static Map getParams(HttpServletRequest request){
        Map paramMap = new HashMap<>();
        try {
                Enumeration em = request.getParameterNames();
                while (em.hasMoreElements()){
                    String name = (String)em.nextElement();
                    String value = request.getParameter(name);
                    paramMap.put(name,value);
            }
        }catch (Exception e){
            throw new CustomException(ErrorCode.REQUEST_IO_ERROR_CODE,ErrorMessage.REQUEST_IO_ERROR);
        }
        return paramMap;
    }
    
    /**
    *@Author FeiChen
    *@Description 文件下载
    *@Date 17:04 2018/6/12
    *@Param [path, name, response]
    *@return void
    **/
    public static void doDownLoad(String path, String name,
                                  HttpServletResponse response)throws Exception {

        response.reset();
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Content-disposition",
                "attachment;success=true;filename ="
                        + URLEncoder.encode(name, "utf-8"));
        response.setContentType("application/octet-stream");

            BufferedOutputStream bos = null;
            OutputStream fos = null;

            BufferedInputStream bis = new BufferedInputStream(HttpUtil.class.getClassLoader().getResourceAsStream(path));

            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead = 0;
            while ((bytesRead = bis.read()) != -1) {
                bos.write(bytesRead);
            }
            bos.flush();
            bis.close();
            fos.close();
            bos.close();

    }
    
    /**
    *@Author FeiChen
    *@Description execel表格转换
    *@Date 20:08 2018/8/14
    *@Param [srcFilePath, beanParams, destFilePath]
    *@return void
    **/
    public static void transformXLS(String srcFilePath, Map beanParams, String destFilePath) throws Exception {
        InputStream is = new BufferedInputStream(HttpUtil.class.getClassLoader().getResourceAsStream(srcFilePath));
        XLSTransformer transformer = new XLSTransformer();
        Workbook workbook = transformer.transformXLS(is, beanParams);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destFilePath));
        workbook.write(os);
        is.close();
        os.flush();
        os.close();
    }
}
