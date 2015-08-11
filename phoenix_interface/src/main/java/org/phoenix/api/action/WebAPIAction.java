package org.phoenix.api.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.phoenix.api.utils.JsonPaser;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * web接口测试实现类
 * @author mengfeiyang
 *
 */
public class WebAPIAction implements APIAction{

	/**
	 * 获取WebConversation对象，通过这个对象可以设置cookie，host，代理等
	 * @return
	 */
	@Override
	public WebConversation getWebConversation(){
		return new WebConversation();
	}
	/**
	 * 本方法需要手动指定get或post方式，不区分大小，需要传入一个WebConversation对象
	 * @param url
	 * @param postorget
	 * @param wc
	 * @return
	 */
	@Override
	public WebResponse getResponseByHost(String url,String postorget,WebConversation wc){
		WebRequest req = null;
		 if(postorget.equalsIgnoreCase("post"))req = new PostMethodWebRequest(url);	
		 else if(postorget.equalsIgnoreCase("get"))req = new GetMethodWebRequest(url);	
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}	
		wr.close();
		return wr;
	}
	/**
	 * 通过post方式load数据
	 * @param url
	 * @return
	 */
	@Override
	public WebResponse getResponseByPost(String url) {
		WebConversation wc = new WebConversation();	
		WebRequest req = new PostMethodWebRequest(url);	
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}	
		wr.close();
		return wr;
	}
	/**
	 * 通过post方式load数据
	 * @param url
	 * @param connTimeOut 最大连接的超时时间
	 * @param readTimeout 读取数据的最大超时时间
	 * @return
	 */
	@Override
	public WebResponse getResponseByPost(String url, int connTimeOut, int readTimeout) {
		WebConversation wc = new WebConversation();	
		wc.set_connectTimeout(connTimeOut);
		wc.set_readTimeout(readTimeout);
		WebRequest req = new PostMethodWebRequest(url);		
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}	
		wr.close();
		return wr;
	}
	/**
	 * post请求
	 * @param url
	 * @param parameters 接口请求的参数
	 * @param headers
	 * @return
	 */
	@Override
	public WebResponse getResponseByPost(String url,HashMap<String, String> parameters, HashMap<String, String> headers) {
		WebConversation wc = new WebConversation();// 建立一个WebConversation实例		
		WebRequest req = new PostMethodWebRequest(url);// 向指定的URL发出请求，获取响应
		if(parameters != null){
			Iterator<Entry<String, String>> ps = parameters.entrySet().iterator();
			while(ps.hasNext()){
				Entry<String,String> es = ps.next();
				req.setParameter(es.getKey(), es.getValue());
			}
		}
		if(headers != null){
			Iterator<Entry<String, String>> hs = headers.entrySet().iterator();
			while(hs.hasNext()){
				Entry<String,String> entry = hs.next();
				req.setHeaderField(entry.getKey(), entry.getValue());
			}
		}
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return wr;
	}
	/**
	 * get方式请求数据
	 * @param url
	 * @return
	 */
	@Override
	public WebResponse getResponseByGet(String url) {
		WebConversation wc = new WebConversation();	
		WebRequest req = new GetMethodWebRequest(url);	
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}	
		wr.close();
		return wr;
	}
	/**
	 * 通过get方式load数据
	 * @param url
	 * @param connTimeOut 最大连接的超时时间
	 * @param readTimeout 读取数据的最大超时时间
	 * @return
	 */
	@Override
	public WebResponse getResponseByGet(String url, int connTimeOut, int readTimeout) {
		WebConversation wc = new WebConversation();// 建立一个WebConversation实例	
		wc.set_connectTimeout(connTimeOut);
		wc.set_readTimeout(readTimeout);
		WebRequest req = new GetMethodWebRequest(url);// 向指定的URL发出请求，获取响应		
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}	
		wr.close();
		return wr;
	}
	/**
	 * get请求
	 * @param url
	 * @param parameters 接口请求的参数
	 * @param headers
	 * @return
	 */
	@Override
	public WebResponse getResponseByGet(String url,HashMap<String, String> parameters, HashMap<String, String> headers) {
		WebConversation wc = new WebConversation();// 建立一个WebConversation实例		
		WebRequest req = new GetMethodWebRequest(url);// 向指定的URL发出请求，获取响应
		if(parameters != null){
			Iterator<Entry<String, String>> ps = parameters.entrySet().iterator();
			while(ps.hasNext()){
				Entry<String,String> es = ps.next();
				req.setParameter(es.getKey(), es.getValue());
			}
		}
		if(headers != null){
			Iterator<Entry<String, String>> hs = headers.entrySet().iterator();
			while(hs.hasNext()){
				Entry<String,String> entry = hs.next();
				req.setHeaderField(entry.getKey(), entry.getValue());
			}
		}
		WebResponse wr = null;
		try {
			wr = wc.getResponse(req);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return wr;
	}
	/**
	 * 通过指定的jsonpath从json内容中取指定值。
	 * @param jsonContent  json的内容
	 * @param jsonPath  json指定节点的路径
	 * @return
	 */
	@Override
	public String getJSONValue(String jsonContent, String jsonPath) {
		try {
			return JsonPaser.getNodeValue(jsonContent, jsonPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
