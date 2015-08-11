package org.phoenix.api.action;

import java.util.HashMap;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * 接口测试
 * @author mengfeiyang
 *
 */
public interface APIAction {
	/**
	 * 获取WebConversation对象，通过这个对象可以设置cookie，host，代理等
	 * @return
	 */
	WebConversation getWebConversation();
	/**
	 * 本方法需要手动指定get或post方式，不区分大小，需要传入一个WebConversation对象
	 * @param url
	 * @param postorget
	 * @param wc
	 * @return
	 */
	WebResponse getResponseByHost(String url,String postorget,WebConversation wc);
	/**
	 * 通过post方式load数据
	 * @param url
	 * @return
	 */
	WebResponse getResponseByPost(String url);
	/**
	 * 通过post方式load数据
	 * @param url
	 * @param connTimeOut 最大连接的超时时间
	 * @param readTimeout 读取数据的最大超时时间
	 * @return
	 */
	WebResponse getResponseByPost(String url,int connTimeOut,int readTimeout);
	/**
	 * post请求
	 * @param url
	 * @param parameters 接口请求的参数
	 * @param headers
	 * @return
	 */
	WebResponse getResponseByPost(String url,HashMap<String,String> parameters,HashMap<String,String> headers);
	/**
	 * get方式请求数据
	 * @param url
	 * @return
	 */
	WebResponse getResponseByGet(String url);
	/**
	 * 通过get方式load数据
	 * @param url
	 * @param connTimeOut 最大连接的超时时间
	 * @param readTimeout 读取数据的最大超时时间
	 * @return
	 */
	WebResponse getResponseByGet(String url,int connTimeOut,int readTimeout);
	/**
	 * get请求
	 * @param url
	 * @param parameters 接口请求的参数
	 * @param headers
	 * @return
	 */
	WebResponse getResponseByGet(String url,HashMap<String,String> parameters,HashMap<String,String> headers);
	/**
	 * 通过指定的jsonpath从json内容中取指定值。
	 * @param jsonContent  json的内容
	 * @param jsonPath  json指定节点的路径
	 * @return
	 */
	String getJSONValue(String jsonContent, String jsonPath);
}
