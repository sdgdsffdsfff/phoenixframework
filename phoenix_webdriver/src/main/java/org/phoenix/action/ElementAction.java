package org.phoenix.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;

import org.phoenix.model.CaseLogBean;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/**
 * 元素操作的接口，该接口中的所有操作是web和mobile共有的，
 * 一些特殊的操作需要在实现类中独立实现
 * @author mengfeiyang
 *
 */
public interface ElementAction {
	
	void addLocatorAndDatas(int caseId,CaseLogBean caseLogBean);
	void addLocatorAndDatas(String caseName,CaseLogBean caseLogBean);
	ElementAction webElement();
	ElementAction webElement(String name);
	ICheckPoint checkPoint();
	String getData(String dataName);
	void setChromeDriverExePath(String path);
	void setFirefoxExePath(String path);
	void setWebProxy(ElementAction webProxy);
	
	/*
	 * 使用Ie打开被测的页面
	 */
	void openNewWindowByIE(String url);
	
	/*
	 * 使用Chrome打开被测的页面
	 */
	void openNewWindowByChrome(String url);
	
	/*
	 * 使用Firefox打开被测的页面
	 */
	void openNewWindowByFirefox(String url);
	
	/**
	 * 如果有打开的浏览器窗口，则关闭
	 */
	void closeWindow();
	
	/**
	 * 对元素进行点击操作
	 */
	void click();
	/**
	 * 对可输入的输入框输入值
	 */
	void setText(String text);
	
	/**
	 * 获取可视的innerText值
	 */
	String getText();
	
	/**
	 * 获取指定属性的值
	 */
	String getAttrValue(String attr);
	
	/**
	 * 暂停执行的步骤
	 */
	void sleep(long ms);
	
	/**
	 * 在文本域中追加字符
	 */
	void append(String str);
	
	/**
	 * 在元素上按Enter键
	 */
	void pressEnter();
	
	/**
	 * 在元素上按Tab键
	 */
	void pressTab();
	
	
	String innerText();
	
	/**
	 * 返回指定元素html信息
	 */
	String innerHtml();
	
	/**
	 * 返回指定元素的name值
	 */
	String name();
	
	/**
	 * 判断元素是否存在
	 */
	boolean exists();
	
	/**
	 * 选中或取消选中
	 */
	void setSelected(boolean selected);
	
	/**
	 * 在指定时间等待操作
	 */
	void waitUntil(Condition condition, long timeoutMilliseconds);
	

/*	SelenideElement $(String cssSelector);
	SelenideElement $(String cssSelector, int index);
	SelenideElement $(By selector);
	SelenideElement $(By selector, int index);
	ElementsCollection $$(String cssSelector);*/
	ElementsCollection getElements();
	
	/**
	 * 上传一个文件
	 */
	File uploadFile(String filePath);
	
	/**
	 * 根据指定的字符选择
	 */
	void selectOption(String text);
	
	/**
	 * 根据下拉框的value选择
	 */
	void selectOptionByValue(String value);
	
	/**
	 * 获取下拉框已被选择的值
	 */
	String getSelectedValue();
	
	/**
	 * 获取下拉框已被选择的数据
	 * @return
	 */
	String getSelectedText();
	
	/**
	 * 根据指定的对象下载文件
	 */
	File download() throws FileNotFoundException;
	
	/**
	 * 右键单击鼠标
	 */
	SelenideElement contextClick();
	
	/**
	 * 将鼠标悬浮在一个元素上
	 */
	SelenideElement hover();
	
	/**
	 * 将指定元素拖拽到指定的位置，目标定位方式为css
	 */
	SelenideElement dragAndDropTo(String targetCssSelector);
	
	/**
	 * 判断是否是图像
	 */
	boolean isImage();
	
	SelenideElement parent();
	
	SelenideElement waitWhile(Condition condition, long timeoutMilliseconds);
	
	boolean isDisplayed();

	SelenideElement scrollTo();

	SelenideElement getSelectedOption();

	String getCssValue(String propertyName);

	boolean isEnabled();

	boolean isSelected();

	String getAttribute(String name);

	String getTagName();

	void clear();

	void sendKeys(String str);

	void submit();
	
	void switchToWindow(String title);
	
	void switchToWindow(int index);
	
	void confirm(String expectedDialogText);
	
	SelenideElement selectRadio(String value);
	
	SelenideElement getSelectedRadio();
	
	void dismiss(String expectedDialogText);
	
	List<String> getJavascriptErrors();
	
	List<String> getWebDriverLogs(String logType, Level logLevel);
	
	void refresh();
	
	void switchToFrame(String nameOrId);
	
	void switchToParent();
	
	void back();
	
	void forward();
	
	String title();
	
	String screenshot(String fileName);
	
	String getPageSource();
	
	public void doubleClick();

}
