package com.chat.backend.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Slf4j
@SuppressWarnings("all")
public class ImageGeneratorUtil {

    /**
     * 预定义的调色板，每个数组代表一个颜色集合
     */
    private static final Color[][] COLOR_PALETTES = {
            // 自然柔和色调
            {
                    new Color(230, 240, 220),   // 浅绿
                    new Color(180, 210, 180),   // 中绿
                    new Color(130, 170, 140),   // 深绿
                    new Color(250, 240, 210),   // 米黄
                    new Color(210, 180, 140)    // 土褐色
            },

            // 海洋蓝色系
            {
                    new Color(240, 248, 255),   // 爱丽丝蓝
                    new Color(176, 224, 230),   // 粉末蓝
                    new Color(95, 158, 160),    // 军服蓝
                    new Color(70, 130, 180),    // 钢青
                    new Color(100, 149, 237)    // 矢车菊蓝
            },

            // 柔和紫色系
            {
                    new Color(230, 230, 250),   // 薰衣草
                    new Color(221, 160, 221),   // 李子
                    new Color(147, 112, 219),   // 中紫色
                    new Color(186, 85, 211),    // 中兰花紫
                    new Color(255, 240, 245)    // 淡紫红
            },

            // 温暖橙红色系
            {
                    new Color(255, 248, 220),   // 玉米丝
                    new Color(255, 228, 181),   // 小麦色
                    new Color(255, 160, 122),   // 浅鲑鱼色
                    new Color(240, 128, 128),   // 浅珊瑚色
                    new Color(250, 235, 215)    // 杏仁白
            },

            // 现代灰色系
            {
                    new Color(240, 240, 240),   // 浅灰
                    new Color(200, 200, 200),   // 中灰
                    new Color(169, 169, 169),   // 深灰
                    new Color(220, 220, 220),   // 银灰
                    new Color(192, 192, 192)    // 银色
            }
    };

    /**
     * 根据输入字符串生成唯一的图像
     *
     * @param input 输入字符串
     * @return 图像的字节数组
     */
    public static byte[] generateImage(String input) {
        try {
            // 根据输入字符串生成确定性随机数生成器
            long seed = generateSeedFromString(input);
            Random random = new Random(seed);

            // 选择一个颜色调色板
            Color[] currentPalette = COLOR_PALETTES[random.nextInt(COLOR_PALETTES.length)];

            // 创建图像
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // 设置抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 设置背景颜色
            g2d.setColor(currentPalette[0]);
            g2d.fillRect(0, 0, 400, 400);

            // 绘制多个形状
            for (int i = 0; i < 10; i++) {
                // 从调色板中选择颜色
                g2d.setColor(currentPalette[random.nextInt(currentPalette.length)]);
                drawRandomShape(g2d, random);
            }

            // 释放图形资源
            g2d.dispose();

            // 将图像转换为字节数组
            return imageToByteArray(image);
        } catch (Exception e) {
            log.error("生成图像失败", e);
            return null;
        }
    }

    /**
     * 从字符串生成确定性种子
     */
    private static long generateSeedFromString(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(input.getBytes());

        // 将MD5哈希转换为长整型种子
        long seed = 0;
        for (int i = 0; i < Math.min(hash.length, 8); i++) {
            seed = (seed << 8) | (hash[i] & 0xff);
        }
        return Math.abs(seed);
    }

    /**
     * 生成随机颜色
     */
    private static Color generateRandomColor(Random random) {
        // 使用更多颜色变体，增加随机性
        float hue = random.nextFloat();
        float saturation = 0.5f + random.nextFloat() * 0.5f;
        float brightness = 0.5f + random.nextFloat() * 0.5f;
        return Color.getHSBColor(hue, saturation, brightness);
    }

    /**
     * 绘制随机形状
     */
    private static void drawRandomShape(Graphics2D g2d, Random random) {
        int shapeType = random.nextInt(5);

        switch (shapeType) {
            // 圆形
            case 0:
                int circleSize = random.nextInt(100) + 50;
                g2d.fillOval(
                        random.nextInt(400 - circleSize),
                        random.nextInt(400 - circleSize),
                        circleSize,
                        circleSize
                );
                break;
            // 矩形
            case 1:
                int rectWidth = random.nextInt(200) + 50;
                int rectHeight = random.nextInt(200) + 50;
                g2d.fillRect(
                        random.nextInt(400 - rectWidth),
                        random.nextInt(400 - rectHeight),
                        rectWidth,
                        rectHeight
                );
                break;
            // 三角形
            case 2:
                int[] xPoints = new int[3];
                int[] yPoints = new int[3];
                for (int i = 0; i < 3; i++) {
                    xPoints[i] = random.nextInt(400);
                    yPoints[i] = random.nextInt(400);
                }
                g2d.fillPolygon(xPoints, yPoints, 3);
                break;
            // 椭圆
            case 3:
                int ellipseWidth = random.nextInt(200) + 50;
                int ellipseHeight = random.nextInt(200) + 50;
                g2d.fillOval(
                        random.nextInt(400 - ellipseWidth),
                        random.nextInt(400 - ellipseHeight),
                        ellipseWidth,
                        ellipseHeight
                );
                break;
            // 不规则多边形
            case 4:
                // 4-8边形
                int sides = random.nextInt(5) + 4;
                int[] xPolygon = new int[sides];
                int[] yPolygon = new int[sides];
                for (int i = 0; i < sides; i++) {
                    xPolygon[i] = random.nextInt(400);
                    yPolygon[i] = random.nextInt(400);
                }
                g2d.fillPolygon(xPolygon, yPolygon, sides);
                break;
            default:
                break;
        }
    }

    /**
     * 将图像转换为字节数组
     */
    private static byte[] imageToByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }

    /**
     * 将图像保存到文件
     */
    private static void saveImageToFile(byte[] imageData, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(imageData);
        }
    }

    /**
     * 主方法演示如何使用工具类
     */
    public static void main(String[] args) {
        try {
            // 使用不同的输入字符串生成图像
            String[] inputs = {"Hello", "World", "OpenAI", "Java", "Image Generation"};

            for (int i = 0; i < inputs.length; i++) {
                byte[] imageData = generateImage(inputs[i]);
                String outputPath = "C:\\Users\\14110\\Desktop\\avatar_%s.png".formatted(i);
                saveImageToFile(imageData, outputPath);
                System.out.println("生成图像: " + outputPath);
            }
        } catch (Exception e) {
            log.error("生成图像失败", e);
        }
    }
}