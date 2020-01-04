package com.demo.video;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.util.StopWatch;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 截取视频前几帧
 * @author jjxiek
 * @since 2019/12/27 11:26
 */
public class FFmpegDemo {
    private static StopWatch stopWatch = new StopWatch("截取视频");
    private BASE64Encoder base64Encoder = new BASE64Encoder();
    public static void main(String[] args) throws IOException {
        System.out.println("开始任务");
        new FFmpegDemo().run();
        System.out.println("任务结束");
        System.out.println(stopWatch.prettyPrint());
    }
    public void run() throws IOException {
        //从网络加载视频流
        String u = "https://hscrm.obs.cn-south-1.myhuaweicloud.com/hscrm/management/devsoft/material/d9/m4/1576807825273_%E8%8D%A3%E8%80%80V1%E5%8F%91%E5%B8%83/m7e659a4daa45a088322074c5013dfaba.mov";
        String u2 = "https://hscrm.ac-diary.cn/hscrm/management/devsoft/material/d9/m4/1576807825273_%E8%8D%A3%E8%80%80V1%E5%8F%91%E5%B8%83/m7e659a4daa45a088322074c5013dfaba.mov";
        RenderedImage imageFromVideo = getImageFromVideo(openUrl(u2), 4);

        String s = encodeImage(imageFromVideo);
//            System.out.println(s);
    }

    public void run2() throws IOException {
        String picUrl = "https://hscrm.ac-diary.cn/hscrm/retail/devsoft/material/4/abc.jpg";
        String picUrl2 = "https://hscrm.ac-diary.cn/hscrm/retail/devsoft/material/4/test.jpg";
        BufferedImage image = openPic(picUrl);
        String s = encodeImage(image);
        System.out.println(s);
    }
    public void run3() throws IOException {
        String url = "https://hscrm.ac-diary.cn/hscrm/retail/devsoft/material/4/test.txt";
        String url2 = "https://hscrm.obs.cn-south-1.myhuaweicloud.com/hscrm/retail/devsoft/material/4/test.txt";
        InputStream is = openUrl(url);
        String result = CharStreams.toString(new InputStreamReader(
            is, Charsets.UTF_8));
        System.out.println(result);

    }
    public void run4() throws IOException{
        String url = "https://hscrm.ac-diary.cn/hscrm/retail/devsoft/material/4/test.txt";

    }

    public InputStream openUrl(String u) throws IOException {
        stopWatch.start("打开url");
        URL url = new URL(u);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        stopWatch.stop();
        return bis;
    }
    public InputStream openUrl2(String u) throws IOException {
        stopWatch.start("打开url");
        URL url = new URL(u);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(20 * 1000);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(conn.getInputStream(),output);
        ByteArrayInputStream is = new ByteArrayInputStream(output.toByteArray());
        stopWatch.stop();
        return is;
    }
    public BufferedImage openPic(String picUrl) throws IOException {
        stopWatch.start("打开图片");
        URL url = new URL(picUrl);
        BufferedImage image = ImageIO.read(url);
        stopWatch.stop();
        return image;
    }

    public String encodeImage(RenderedImage image) throws IOException {
        stopWatch.start("编码图片");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        String str = base64Encoder.encode(bytes).trim();
        stopWatch.stop();
        stopWatch.prettyPrint();
        return str;
    }
    public RenderedImage getImageFromVideo(InputStream is, int n) throws FrameGrabber.Exception {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(is);

        stopWatch.start("截取视频");
        ff.start();
        int ftp = ff.getLengthInFrames();
        Frame frame = null;
        int flag = 0;
        while (flag <= ftp) {
            //获取帧
            frame = ff.grabImage();
            //过滤前3帧，避免出现全黑图片
            if ((flag>n)&&(frame != null)) {
                break;
            }
            flag++;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        stopWatch.stop();
        return converter.getBufferedImage(frame);
    }
}
