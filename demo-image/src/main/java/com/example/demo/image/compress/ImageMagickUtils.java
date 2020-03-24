package com.example.demo.image.compress;

import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IMOperation;

public class ImageMagickUtils {
    /**
     * ImageMagick的路径
     */
    public static String imageMagickPath = null;
    static {
        /**
         *
         * 获取ImageMagick的路径
         */
        // Properties prop = new PropertiesFile().getPropertiesFile();
        //linux下不要设置此值，不然会报错
        imageMagickPath = "C:\\Program Files\\ImageMagick-6.9.3-Q16";//prop.getProperty("imageMagickPath");
    }

    /**
     *
     * 根据坐标裁剪图片
     *
     * @param srcPath   要裁剪图片的路径
     * @param newPath   裁剪图片后的路径
     * @param x         起始横坐标
     * @param y         起始纵坐标
     * @param x1        结束横坐标
     * @param y1        结束纵坐标
     */

    public static void cutImage(String srcPath, String newPath, int x, int y, int x1,   int y1) throws Exception {
        int width = x1 - x;
        int height = y1 - y;
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        /**
         * width：  裁剪的宽度
         * height： 裁剪的高度
         * x：       裁剪的横坐标
         * y：       裁剪的挫坐标
         */
        op.crop(width, height, x, y);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();

        // linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);

        convert.run(op);
    }

    /**
     *
     * 根据尺寸缩放图片
     * @param width             缩放后的图片宽度
     * @param height            缩放后的图片高度
     * @param srcPath           源图片路径
     * @param newPath           缩放后图片的路径
     */
    public static void cutImage(int width, int height, String srcPath,  String newPath) throws Exception {
        IMOperation op = new IMOperation();

        op.addImage(srcPath);
        op.resize(width, height);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        // linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
        convert.run(op);

    }

    /**
     * 压缩图片
     * @param filePath 源文件路径
     * @param toPath   缩略图路径
     */
    public static void createThumbnail(String filePath,
                                       String toPath,int width, int height) throws Exception {

        GMOperation op = new GMOperation();
        //待处理图片的绝对路径
        op.addImage(filePath);

        //图片压缩比，有效值范围是0.0-100.0，数值越大，缩略图越清晰
        op.quality(75.0);

        //width 和height可以是原图的尺寸，也可以是按比例处理后的尺寸
        op.addRawArgs("-resize", width + "x" + height);

        op.addRawArgs("-gravity", "center");
        //处理后图片的绝对路径
        op.addImage(toPath);

        // 如果使用ImageMagick，设为false,使用GraphicsMagick，就设为true，默认为false
        ConvertCmd convert = new ConvertCmd(true);
        // linux下不要设置此值，不然会报错
        convert.setSearchPath("E:\\GraphicsMagicPro_1_4_5_Binary\\GraphicsMagicPro_1_4_5");
        convert.run(op);
    }

    /**
     * 根据宽度缩放图片
     *
     * @param width            缩放后的图片宽度
     * @param srcPath          源图片路径
     * @param newPath          缩放后图片的路径
     */
    public static void cutImage(int width, String srcPath, String newPath)  throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        op.resize(width, null);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        // linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
        convert.run(op);
    }

    /**
     * 给图片加水印
     * @param srcPath            源图片路径
     */
    public static void addImgText(String srcPath) throws Exception {
        IMOperation op = new IMOperation();
        op.font("宋体").gravity("southeast").pointsize(18).fill("#BCBFC8")
                .draw("text 5,5 juziku.com");
        op.addImage();
        op.addImage();
        ConvertCmd convert = new ConvertCmd();
        // linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
        convert.run(op, srcPath, srcPath);
    }
}