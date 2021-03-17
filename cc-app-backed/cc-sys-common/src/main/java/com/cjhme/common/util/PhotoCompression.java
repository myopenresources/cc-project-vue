package com.cjhme.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片压缩
 * @author cjh
 * @date 2012-09-15
 */
public class PhotoCompression {

	
	/**
	 * 压缩图片
	 * @param file
	 * @param outputDir
	 * @param width
	 * @param height
	 * @return
	 */
	public static int photoCompression(File file,String outputDir,int width,int height){
		try{
			//读取图片
			Image img = ImageIO.read(file);
			
			if(img.getWidth(null)<= width && img.getHeight(null)<=height){
				width=img.getWidth(null);
				height=img.getHeight(null);
			}
			createImg(width,height,img,outputDir);
			return 1;
			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
	}
	
	/**
	 * 压缩图片(无论大小全压�?)
	 * @param in
	 * @param outputDir
	 * @param width
	 * @param height
	 * @return
	 */
	public static int photoAllCompression(InputStream  in,String outputDir,int width,int height){
		try{
			//读取图片
			Image img = ImageIO.read(in);
			createImg(width,height,img,outputDir);
			return 1;
			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
	}
	
	/**
	 * 压缩图片(小于指定大小的不压缩，大于指定大小的压缩)
	 * @param in
	 * @param outputDir
	 * @param width
	 * @param height
	 * @return
	 */
	public static int photoCompression(InputStream  in,String outputDir,int width,int height){
		try{
			//读取图片
			Image img = ImageIO.read(in);
			
			if(img.getWidth(null)<= width && img.getHeight(null)<=height){
				width=img.getWidth(null);
				height=img.getHeight(null);
			}
			createImg(width,height,img,outputDir);
			return 1;
			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
	}
	
	
	/**
	 * 创建图片
	 * @param width
	 * @param height
	 * @param img
	 * @param outDir
	 * @return
	 */
	private static int createImg(int width,int height,Image img,String outDir){
		try{
			 BufferedImage bufferImg = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);  
			  bufferImg.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
			  ImageIO.write(bufferImg, "JPEG", new File(outDir));
			  return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 创建水印图片
	 * @param in
	 * @param outDir
	 * @param fontColor
	 * @param font
	 * @param content
	 * @return
	 */
	public static int createWatermarkImg(InputStream  in,String outDir,Color fontColor,Font font,String content,int mx,int my){
		try{//读取图片
			Image img = ImageIO.read(in);
			int width = img.getWidth(null);
			int height=img.getHeight(null);
			int x = width-mx;
			int y=height-my;
			return createWatermarkImg(width,height,img,outDir,fontColor,font,content,x,y);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	
	/**
	 * 创建水印图片
	 * @param width
	 * @param height
	 * @param img
	 * @param outDir
	 * @return
	 */
	public static int createWatermarkImg(int width,int height,Image img,String outDir,Color fontColor,Font font,String content,int x,int y){
		try{
			  BufferedImage bufferImg = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);  
			  bufferImg.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
			  
			  Graphics g = bufferImg.createGraphics();
			  g.setColor(fontColor);
			  g.setFont(font);
			  g.drawString(content,x,y);
			  g.dispose();
			  ImageIO.write(bufferImg, "JPEG", new File(outDir));
			  return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public static void main(String[] args) {
		try{
			File file = new File("d:\\aa.jpg");
			InputStream in = new FileInputStream(file);
			System.out.println(createWatermarkImg(in,"d:\\bb.jpg",new Color(5,150,39),new Font("宋体",Font.PLAIN,13),"Hippo管理系统后台",130,10));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
