package org.phoenix.cases.lianmeng;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.InterfaceBatchDataBean;
import org.phoenix.model.InterfaceDataBean;
import org.phoenix.model.UnitLogBean;

import com.meterware.httpunit.WebResponse;

/**
 * 使用phoenix做接口测试的案例,包括两个：<br>
 * 1、使用多批数据对一个接口url做测试<br>
 * 2、不使用多批数据<br>
 * 若对wsdl形式的接口做测试，则wsdl的文件需要以Dom方式解析。使用WebResponse中的Dom即可。
 * @author mengfeiyang
 *
 */
public class ContactJieKou extends WebElementActionProxy{
	private static String caseName = "接口测试用例";
	public ContactJieKou() {
	}
	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean arg0) {
		init(caseName,arg0);
		
		LinkedHashMap<InterfaceBatchDataBean, List<InterfaceDataBean>> datas = webProxy.loadInterfaceDatas(caseName);
		
		Iterator<Entry<InterfaceBatchDataBean, List<InterfaceDataBean>>> iterator = datas.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<InterfaceBatchDataBean, List<InterfaceDataBean>> entry = iterator.next();
			InterfaceBatchDataBean iBatchBean = entry.getKey();
			List<InterfaceDataBean> iDatas = entry.getValue();
			System.out.println("--数据批次："+iBatchBean.getId()+"   期望值："+iBatchBean.getExpectData());
			String url  ="http://v.youku.com/player/getPlayList/VideoIDS/XNzUwODY4Nzc2/timezone/+08/version/5/source/video?ctype=10&ev=1&password=&";
			for(InterfaceDataBean iData : iDatas){
				url += iData.getDataName()+"="+iData.getDataContent()+"&";
			}
			url = url.substring(0, url.length()-1);
			System.out.println(url);
			WebResponse resp = webProxy.webAPIAction().getResponseByGet(url);
			try {
				//如果接口返回的数据是json格式，则可以通过jsonPath取出实际值，如果不是json则可以自己通过自定义方式如正则表达式等。
				String actual = webProxy.webAPIAction().getJSONValue(resp.getText(), "JSON.data[0].dvd.point[3].title");
				//String actual = resp.getElementWithID("su").getText();根据页面中的id，tagName，XPath，Dom等方式取到实际值
				String r = webProxy.checkPoint().checkIsEqual(actual, iBatchBean.getExpectData());//使用平台的检查点进行检查，检查结果将会记录到日志中
				if(r == null)System.out.println("-----测试通过-----");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//不使用数据批次的方式
/*		WebResponse resp = webProxy.webAPIAction().getResponseByGet("http://v.youku.com/player/getPlayList/VideoIDS/XNzUwODY4Nzc2/timezone/+08/version/5/source/video?ran=7318&n=3&ctype=10&ev=1&password=");
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
		}*/
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		ContactJieKou yw = new ContactJieKou();
		LinkedList<UnitLogBean> ll = yw.run(new CaseLogBean());
		for(UnitLogBean l : ll){
			System.out.println(l.getContent());
		}
	}
	
}
