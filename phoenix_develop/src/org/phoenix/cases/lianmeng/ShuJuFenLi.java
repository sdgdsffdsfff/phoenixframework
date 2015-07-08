package org.phoenix.cases.lianmeng;

import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.enums.LocatorType;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

/**
 * 使用phoenix做数据分离后的测试案例
 * 并根据检查点的结果作判断，检查点的结果会记录到日志中<br>
 * 在实际使用中，数据分离（定位数据和输入的数据）可以混合使用，即可以使用存储在数据库中的数据，也可以使用保存在代码中的数据
 * @author mengfeiyang
 *
 */
public class ShuJuFenLi extends WebElementActionProxy{
	private static String caseName = "数据分离测试";
	public ShuJuFenLi() {
	}
	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean arg0) {
		init(caseName,arg0);
		//webProxy.setChromeDriverExePath("C:\\Users\\mengfeiyang\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		//webProxy.openNewWindowByChrome("http://lianmeng.360.cn/account");
		webProxy.setFirefoxExePath(webProxy.getData("FirefoxPath"));
		webProxy.openNewWindowByFirefox(webProxy.getData("首页地址"));
		webProxy.webElement("uname").setText(webProxy.getData("用户名"));
		webProxy.webElement("passwd").setText(webProxy.getData("密码"));
		webProxy.webElement("验证码").setText(webProxy.getData("验证码"));
		webProxy.webElement("登陆按钮").click();
		String errorMsg = webProxy.webElement("错误信息").getText();
		System.out.println(errorMsg);
		webProxy.sleep(1000);
		String r = webProxy.checkPoint().checkIsEqual("", errorMsg);
		if(r != null){
			webProxy.webElement("马上注册").click();
			webProxy.webElementLinkFinder(".panel-content").findElementByLinkText("导航联盟").click();
			webProxy.sleep(1000);
			webProxy.webElement("注册用户名").setText(webProxy.getData("User_Name"));
			webProxy.webElement("注册密码").setText(webProxy.getData("Pwd"));
			webProxy.webElement("确认密码").setText(webProxy.getData("Pwd"));
			webProxy.webElement("注册公司名").setSelected(true);
			webProxy.webElement("公司名称").setText(webProxy.getData("公司名称"));
			webProxy.webElement("公司ID").setText(webProxy.getData("公司ID"));
			webProxy.webElement("联系人").setText(webProxy.getData("联系人电话"));
			webProxy.webElement("联系人电话").setText(webProxy.getData("电话号码"));
			webProxy.webElement("验证码2").setText(webProxy.getData("Pwd"));
			webProxy.webElement("verifyCode").setText("1234");
			webProxy.webElement("邮箱").setText(webProxy.getData("邮箱"));
			webProxy.webElement("已读复选框").setSelected(true);
			webProxy.sleep(1000);
		} else {
			webProxy.webElement("业务管理", LocatorType.LINKTEXT).click();
			webProxy.webElement("//*[@id=\"list_view\"]/div[2]/table/tbody/tr[1]/td[4]/a", LocatorType.XPATH).click();
		}
		webProxy.closeWindow();
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		ShuJuFenLi yw = new ShuJuFenLi();
		LinkedList<UnitLogBean> ll = yw.run(new CaseLogBean());
		for(UnitLogBean l : ll){
			System.out.println(l.getContent());
		}
	}
	
}
