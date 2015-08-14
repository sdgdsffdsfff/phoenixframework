package org.phoenix.cases.lianmeng;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

import com.codeborne.selenide.SelenideElement;

/**
 * 将页面上定位到的图片保存到本地，特别适用于无具体地址的验证码图片
 * @author mengfeiyang
 *
 */
public class SaveImg extends WebElementActionProxy{
	private static String caseName = "test";
	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean arg0) {
			init(caseName,arg0);
		try{
			webProxy.setFirefoxExePath("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			webProxy.openNewWindowByFirefox("http://lianmeng.360.cn/");
			//webProxy.openNewWindowByIE("http://lianmeng.360.cn/");
			//webProxy.openNewWindowByChrome("http://lianmeng.360.cn/");
			webProxy.getCurrentDriver().manage().window().maximize();
			webProxy.sleep(2000);
			
			String engine = arg0.getEngineType();
			SelenideElement element = webProxy.webElementLinkFinder("#captcha_img", null);
		    
			Point selenPoint = element.getLocation();
		    Dimension selenDim = element.getSize();
			Robot r = new Robot();
			Rectangle rt = new Rectangle();
			switch(engine){
				case "IEDriver":rt.setBounds(selenPoint.getX()-1, selenPoint.getY()+55, selenDim.getWidth(), selenDim.getHeight());break;
				case "FirefoxDriver":rt.setBounds(selenPoint.getX(), selenPoint.getY()+64, selenDim.getWidth(), selenDim.getHeight());break;
				case "ChromeDriver":rt.setBounds(selenPoint.getX(), selenPoint.getY(), selenDim.getWidth(), selenDim.getHeight());break;
				default:;
			}
			
			BufferedImage  bfi = r.createScreenCapture(rt);
			File f = new File("E:\\testcode.jpg"); 
			ImageIO.write(bfi, "jpg", f);
		 }catch (Exception e){
			e.printStackTrace();
		}
		webProxy.closeWindow();
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		SaveImg saveImg = new SaveImg();
		LinkedList<UnitLogBean> us = saveImg.run(new CaseLogBean());
		for(UnitLogBean u : us){
			System.out.println(u.getContent());
		}
	}

}
