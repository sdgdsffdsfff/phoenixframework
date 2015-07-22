package org.phoenix.cases.aggregate;

import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

/**
 * 该类也是公共用例，但该类不能独立的运行。该类在 {@link Test1} 中被引用
 * @author mengfeiyang
 *
 */
public class TestCase2 extends WebElementActionProxy{
	private String caseName = "公共退出用例";
	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean arg0) {
		init(caseName,arg0);
		webProxy.closeWindow();
		
		return getUnitLog();
	}
}
