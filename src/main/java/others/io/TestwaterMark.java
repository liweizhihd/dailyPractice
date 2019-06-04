package others.io;

import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/23 09:52
 * @Description:
 */
public class TestwaterMark {
    private static int interval = -5;

    public static void waterMark(String inputFile, String outputFile, String waterMarkName, int type) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));

            BaseFont base = BaseFont.createFont();

            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;

            JLabel label = new JLabel();
            FontMetrics metrics;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            int textH = metrics.getHeight();
            int textW = metrics.stringWidth(label.getText());

            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();

                float heightPage = pageRect.getHeight();
                float widthPage = pageRect.getWidth();

                if (type == 1) {
                    under.setFontAndSize(base, 70);
                    for (int height = interval + textH; height < heightPage; height = height + textH * 8) {
                        for (int width = interval + textW; width < widthPage + textW; width = width + textW * 5) {
                            under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width - textW, height - textH, 45);
                        }
                    }
                } else if (type == 2) {
                    under.setFontAndSize(base, 180);
                    under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 90, 10, 60);
                }

               /* float realH = (float) (textW * Math.sin(rotation) + textH * Math.cos(rotation));
                float realW = (float) (textW * Math.cos(rotation) + textH * Math.sin(rotation));*/
                // 添加水印文字
                under.endText();
            }
            //说三遍
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mainTest() {
        String inputFile = "/Users/liweizhi/Desktop/logkit2kafka.pdf";
        String outputFile = "/Users/liweizhi/Desktop/logkit2kafka_clowdwise.pdf";
        String waterMarkName = "Cloudwise";
        waterMark(inputFile, outputFile, waterMarkName, 2);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("至少输入源文件的绝对路径！");
            return;
        }
        String inputFile = args[0];
        String waterMarkWord = "Cloudwise";
        if (args.length > 1) {
            waterMarkWord = args[1];
        }
        String[] split = inputFile.split("\\.");
        String outputFile = split[0] + "_cloudwise." + split[1];

        System.out.printf("inputFile:%s, waterMarkWord:%s\n", inputFile, waterMarkWord);
        waterMark(inputFile, outputFile, waterMarkWord, 2);
        System.out.printf("OKay~ outputFile:%s", outputFile);
        System.out.printf("水印添加完毕~ 带水印的文件绝对路径:%s", outputFile);
    }

    @Test
    public void testMethod() {
        String a = "{\"grain\": \"%s\", \"run_mode\": \"%s\", \"detect_duration\": [%d, %d], "
                + "\"data_name\": \"value\", \"date_name\": \"timestamp\", \"training_days\": %d, \"weekly_seasonality\": "
                + "true, \"daily_seasonality\": true,\"yearly_seasonality\": true, \"interval_width\": 0.997}";
        System.out.println(a);

        String s = String.format("{\"run_mode\": \"%s\", \"detect_duration\": [%d, %d], "
                        + "\"data_name\": \"value\", \"date_name\": \"timestamp\", \"training_days\": %d, \"weekly_seasonality\": "
                        + "true, \"daily_seasonality\": true,\"yearly_seasonality\": true, \"interval_width\": 0.997}",
                "mode", 1, 2, 3);
        System.out.println(s);
    }
}
