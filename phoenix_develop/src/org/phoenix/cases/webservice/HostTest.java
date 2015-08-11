package org.phoenix.cases.webservice;

import java.io.IOException;
import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * 使用phoenix做接口测试的案例,：<br>
 * 设置代理<br>
 * 设置cookie
 * 若对wsdl形式的接口做测试，则wsdl的文件需要以Dom方式解析。使用WebResponse中的Dom即可。
 * @author mengfeiyang
 *
 */
public class HostTest extends WebElementActionProxy{
	private static String caseName = "接口测试用例";
	public HostTest() {
	}
	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean arg0) {
		init(caseName,arg0);
		WebConversation webConveration = webProxy.webAPIAction().getWebConversation();
		webConveration.setProxyServer("10.11.11.1", 80);
		WebResponse resp = webProxy.webAPIAction().getResponseByHost(
				"http://v.youku.com/player/getPlayList/VideoIDS/XNzUwODY4Nzc2/timezone/+08/version/5/source/video?ran=7318&n=3&ctype=10&ev=1&password=",
				"get",
				webConveration
				);
		String s = null;
		try {
			s = webProxy.webAPIAction().getJSONValue(resp.getText(), "JSON.data[0].dvd.point[3].title");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(s);
		String r = webProxy.checkPoint().checkIsMatcher("创新就是一层窗户纸", s);
		if(r == null){
			System.out.println("==================接口通过===================");
		}
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		HostTest yw = new HostTest();
		LinkedList<UnitLogBean> ll = yw.run(new CaseLogBean());
		for(UnitLogBean l : ll){
			System.out.println(l.getContent());
		}
	}
	
}

