package MVC.model.util;

import org.apache.poi.hssf.usermodel.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public Util() { super(); }

    /**
     * 字符串转MD5码加密
     * @param str：需要转码的字符串
     * @return newstr：转码后的字符串
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * 按指定长宽缩放图片
     * @param w：缩放的目标宽度
     * @param h：缩放的目标高度
     * @param src：源文件目录
     * @param dest：缩放后保存目录
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {
        double wr=0,hr=0;
        File srcFile = new File(src);
        File destFile = new File(dest);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image Itemp = bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);//设置缩放目标图片模板

        wr = w*1.0/bufImg.getWidth();//获取缩放比例
        hr = h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 按比率缩放图片
     * @param size：文件大小
     * @param src：源文件目录
     * @param dest：缩放后保存目录
     */
    public static void reSizeImage(String src,String dest,Integer size) throws Exception {
        File srcFile = new File(src);
        File destFile = new File(dest);

        long fileSize = srcFile.length();
        if(fileSize < size * 1024){return;}//文件大于size k时，才进行缩放,注意：size以K为单位
        Double rate = (size * 1024 * 0.5) / fileSize;//获取长宽缩放比例

        BufferedImage bufImg = ImageIO.read(srcFile);
        Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), Image.SCALE_SMOOTH);

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 缩放图片
     * @param srcImageFile：要缩放的图片路径
     * @param result：缩放后的图片路径
     * @param height：目标高度像素
     * @param width：目标宽度像素
     * @param bb：是否补白
     */
    public final static void scaleImage(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; //缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);//bi.SCALE_SMOOTH：选择图像平滑度比缩放速度具有更高优先级的图像缩放算法
            //计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                double ratioHeight = (new Integer(height)).doubleValue()/ bi.getHeight();
                double ratioWhidth = (new Integer(width)).doubleValue()/ bi.getWidth();
                if(ratioHeight>ratioWhidth){
                    ratio= ratioHeight;
                }else{
                    ratio= ratioWhidth;
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
                        .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
                itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
                Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                g.setColor(Color.white);//控制颜色
                g.fillRect(0, 0, width, height);//使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
                if (width == itemp.getWidth(null)){
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                }else{
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                    g.dispose();
                    itemp = image;
                }
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));//输出压缩图片
            //result.substring(result.lastIndexOf(".")+1)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪图片方法
     * @param bufferedImage：图像源
     * @param startX：裁剪开始x坐标
     * @param startY：裁剪开始y坐标
     * @param endX：裁剪结束x坐标
     * @param endY：裁剪结束y坐标
     * @return result：剪裁后的图片
     */
    public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {startX = 0;}
        if (startY == -1) {startY = 0;}
        if (endX == -1) {endX = width - 1;}
        if (endY == -1) {endY = height - 1;}
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }

    /**
     * 导出Excel
     * @param sheetName：sheet名称
     * @param title：标题
     * @param values：内容
     * @param wb：HSSFWorkbook对象
     * @return wb
     */
    public static HSSFWorkbook getExcel(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
        //1、创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){wb = new HSSFWorkbook();}
        //2、在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //3、在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        //4、创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //创建一个居中格式
        //5、生成数据列
        HSSFCell cell = null;//声明列对象
        for(int i=0;i<title.length;i++){//创建标题
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        for(int i=0;i<values.length;i++){//创建内容
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                row.createCell(j).setCellValue(values[i][j]);//将内容按顺序赋给对应的列对象
            }
        }
        return wb;
    }
}
