package org.phoenix.web.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.phoenix.model.CaseBean;
import org.phoenix.model.DataBean;
import org.phoenix.model.InterfaceBatchDataBean;
import org.phoenix.model.InterfaceDataBean;
import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.dto.DataDTO;
import org.phoenix.web.service.ICaseService;
import org.phoenix.web.service.IDataService;
import org.phoenix.web.service.IInBatchDataService;
import org.phoenix.web.service.IInDataBeanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

@Controller
@RequestMapping("/data")
@AuthClass("login")
public class DataController {
	private IDataService dataService;
	private ICaseService caseService;
	private IInBatchDataService inBatchDataService;
	private IInDataBeanService inDataBeanService;
	
	public IInDataBeanService getInDataBeanService() {
		return inDataBeanService;
	}
	@Resource
	public void setInDataBeanService(IInDataBeanService inDataBeanService) {
		this.inDataBeanService = inDataBeanService;
	}
	public IInBatchDataService getInBatchDataService() {
		return inBatchDataService;
	}
	@Resource
	public void setInBatchDataService(IInBatchDataService inBatchDataService) {
		this.inBatchDataService = inBatchDataService;
	}
	public IDataService getDataService() {
		return dataService;
	}
	@Resource
	public void setDataService(IDataService dataService) {
		this.dataService = dataService;
	}

	public ICaseService getCaseService() {
		return caseService;
	}
	@Resource
	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}

	@RequestMapping("/list/{id}")
	public String list(@PathVariable Integer id,Model model){
		model.addAttribute(caseService.getCaseBean(id));
		model.addAttribute("datas", dataService.getDataPager(id));
		return "data/list";
	}
	@RequestMapping(value="/add/{id}",method=RequestMethod.GET)
	public String add(@PathVariable Integer id, Model model,HttpSession session){
		model.addAttribute(new DataDTO());
		model.addAttribute(caseService.getCaseBean(id));
		return "data/add";
	}
	@RequestMapping(value="/add/{id}",method=RequestMethod.POST)
	public String add(@PathVariable Integer id,@Valid DataDTO dataDTO,BindingResult br,Model model){
		if(br.hasErrors()){
			model.addAttribute(caseService.getCaseBean(id));
			return "data/add";
		}
		CaseBean caseBean = new CaseBean();
		DataBean dataBean = new DataBean();
		caseBean.setId(dataDTO.getCaseId());
		dataBean.setCaseBean(caseBean);
		dataBean.setDataContent(dataDTO.getDataContent());
		dataBean.setDataName(dataDTO.getDataName());
		dataService.addData(dataBean);
		return "redirect:/data/list/"+dataDTO.getCaseId();
	}
	
	@RequestMapping(value="/delete/{cid}/{id}")
	public String delete(@PathVariable Integer cid,@PathVariable Integer id){
		dataService.deleData(id);
		return "redirect:/data/list/"+cid;
	}
	
	@RequestMapping(value="/update/{cid}/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer cid,@PathVariable Integer id,Model model,HttpSession session){
		model.addAttribute(new DataDTO());
		model.addAttribute(dataService.getData(id));
		model.addAttribute(caseService.getCaseBean(cid));
		return "data/edit";
	}
	@RequestMapping(value="/update/{cid}/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer cid,@PathVariable Integer id,@Valid DataDTO dataDTO,BindingResult br,Model model){
		if(br.hasErrors()){
			model.addAttribute(caseService.getCaseBean(cid));
			return "data/edit";
		}
		DataBean dataBean = dataService.getData(id);
		dataBean.setDataContent(dataDTO.getDataContent());
		dataBean.setDataName(dataDTO.getDataName());
		dataService.updateData(dataBean);
		return "redirect:/data/list/"+cid;
	}
	
	/**
	 * 根据接口用例的id，列出该接口用例下游多少批参数
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/INTERFACE_CASE/list/{id}")
	public String iList(@PathVariable Integer id,Model model){
		model.addAttribute("datas", inBatchDataService.getInBatchList(id));
		model.addAttribute("caseId",id);
		return "data/ilist";
	}
	/**
	 * 根据单一批次的id，删除该批次的数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/INTERFACE_CASE/dbatch/{cid}/{id}")
	public String deleteBatchBean(@PathVariable Integer cid,@PathVariable Integer id){
		inBatchDataService.deleteInBatch(id);
		return "redirect:/data/INTERFACE_CASE/list/"+cid;
	}
	/**
	 * 加载一个批次的数据，用于更新。cid用于更新完成后能返回到指定指定用例的列表
	 * @param cid
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/INTERFACE_CASE/update/{cid}/{id}")
	public String updateBatchBean(@PathVariable Integer cid,@PathVariable Integer id,Model model){
		model.addAttribute(inBatchDataService.getInBatchBean(id));
		model.addAttribute(caseService.getCaseBean(cid));
		model.addAttribute("iBatchDataBean",new InterfaceBatchDataBean());
		return "data/ibatchDataEdit";
	}
	/**
	 * 执行数据批次的更新操作，id为批次id
	 * @param caseId
	 * @param id
	 * @param expectData
	 * @return
	 */
	@RequestMapping(value="/INTERFACE_CASE/update/{cid}/{id}",method=RequestMethod.POST)
	public String updateBatchData(@PathVariable Integer cid,@PathVariable Integer id,String expectData){
		InterfaceBatchDataBean iDataBean = inBatchDataService.getInBatchBean(id);
		iDataBean.setExpectData(expectData);
		inBatchDataService.updateInBatch(iDataBean);
		System.out.println(expectData);
		return "redirect:/data/INTERFACE_CASE/list/"+cid;
	}
	/**
	 * 加载添加数据批次的界面
	 * @param caseId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/INTERFACE_CASE/add/{id}",method=RequestMethod.GET)
	public String addBatchBean(@PathVariable Integer id,Model model){
		model.addAttribute(caseService.getCaseBean(id));
		model.addAttribute(new InterfaceBatchDataBean());
		return "data/ibatchDataAdd";
	}
	/**
	 * 执行添加操作
	 * @param caseId
	 * @param expectData
	 * @return
	 */
	@RequestMapping(value="/INTERFACE_CASE/add/{caseId}",method=RequestMethod.POST)
	public String addBatch(@PathVariable Integer caseId,String expectData){
		CaseBean caseBean = caseService.getCaseBean(caseId);
		InterfaceBatchDataBean interBatchDataBean = new InterfaceBatchDataBean();
		interBatchDataBean.setCaseBean(caseBean);
		interBatchDataBean.setExpectData(expectData);
		inBatchDataService.addInBatch(interBatchDataBean);
		return "redirect:/data/INTERFACE_CASE/list/"+caseId;
	}
	
	
	/**
	 * 根据batchid加载一个数据批次
	 * @param bid
	 * @param model
	 * @return
	 */
	@RequestMapping("/INTERFACE_CASE/iblist/{bid}")
	public String iBList(@PathVariable Integer bid,Model model){
		model.addAttribute("batchBean", inBatchDataService.getInBatchBean(bid));
		return "data/iblist";
	}
	
	/**
	 * 更新一个数据批次
	 * @param bid
	 * @param id
	 * @return
	 */
	@RequestMapping("/INTERFACE_CASE/iblist/update/{bid}/{id}")
	public String updateDataBeanGet(@PathVariable Integer bid,@PathVariable Integer id,Model model){
		model.addAttribute(inDataBeanService.getDataBean(id));
		model.addAttribute("idatabean",new InterfaceDataBean());
		return "data/idataBeanEdit";
	}
	/**
	 * 执行更新操作，更新完成后跳转到指定批次的数据列表
	 * @param bid
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/INTERFACE_CASE/iblist/update/{bid}/{id}",method=RequestMethod.POST)
	public String updateDataBeanPost(String dataName,String dataContent,@PathVariable Integer bid, @PathVariable Integer id){
		InterfaceDataBean idataBean= inDataBeanService.getDataBean(id);
		idataBean.setDataName(dataName);
		idataBean.setDataContent(dataContent);
		inDataBeanService.updateDataBean(idataBean);
		return "redirect:/data/INTERFACE_CASE/iblist/"+bid;
	}
	/**
	 * 删除数据批次下的单条记录，删除后返回指定批次列表
	 * @param bid
	 * @param id
	 * @return
	 */
	@RequestMapping("/INTERFACE_CASE/iblist/delete/{bid}/{id}")
	public String deleteDataBean(@PathVariable Integer bid,@PathVariable Integer id){
		inDataBeanService.deleteDataBean(id);
		return "redirect:/data/INTERFACE_CASE/iblist/"+bid;
	}
	/**
	 * 加载添加单条数据的界面
	 * @param bid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/INTERFACE_CASE/iblist/add/{bid}",method=RequestMethod.GET)
	public String addDataBeanGet(@PathVariable Integer bid,Model model){
		model.addAttribute("bid", bid);
		model.addAttribute(new InterfaceDataBean());
		return "data/idataBeanAdd";
	}
	/**
	 * 执行添加操作
	 * @param dataName
	 * @param dataContent
	 * @param bid
	 * @return
	 */
	@RequestMapping(value="/INTERFACE_CASE/iblist/add/{bid}",method=RequestMethod.POST)
	public String addDataBean(String dataName,String dataContent,@PathVariable Integer bid){
		InterfaceBatchDataBean interBatchDataBean = inBatchDataService.getInBatchBean(bid);
		InterfaceDataBean interDataBean = new InterfaceDataBean();
		interDataBean.setDataContent(dataContent);
		interDataBean.setDataName(dataName);
		interDataBean.setInterfaceBatchDataBean(interBatchDataBean);
		inDataBeanService.addDataBean(interDataBean);
		return "redirect:/data/INTERFACE_CASE/iblist/"+bid;
	}
	
	@RequestMapping(value="/import/{cid}",method=RequestMethod.GET)
	public String importData(@PathVariable Integer cid,Model model){
		model.addAttribute(caseService.getCaseBean(cid));
		return "data/importData";
	}
	@RequestMapping(value="/import/{cid}",method=RequestMethod.POST)
	public String importDatas(@RequestParam("attachs")MultipartFile attach,HttpServletRequest req,Model model) throws IOException{
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		String errorInfo= null;
		if(attach.isEmpty()) errorInfo = "文件不能为空";
		if(!attach.getOriginalFilename().endsWith(".txt") && !attach.getOriginalFilename().endsWith(".xlsx"))errorInfo = "请选择txt或者xlsx数据文件";
		if(errorInfo != null){model.addAttribute("errorInfo", errorInfo);return "data/importData";}
		String filePath = realpath+"/"+req.getSession().getId()+"."+Files.getFileExtension(attach.getOriginalFilename());;
		File f = new File(filePath);
		FileUtils.copyInputStreamToFile(attach.getInputStream(),f);
		//
		return "redirect:/data/INTERFACE_CASE/list/8";
	}
	
}
