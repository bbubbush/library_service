package ju.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ju.dto.RegistDTO;
import ju.dto.SubjectDTO;
import ju.learning.model.LearningDAO;
import ju.model.SubjectDAO;
import ju.model.TeacherDAO;

@Controller
public class LearningController {
	@Autowired
	public SubjectDAO subjectDao;
	
	@Autowired
	LearningDAO ligdao;
	
	@Autowired
	TeacherDAO teacherdao;
	
	@RequestMapping("/getTeacherInfo.ju")
	public ModelAndView getTeacherInfo(){
		return new ModelAndView("juJson","teacherInfo",teacherdao.teacherList());
	}
	
	@RequestMapping(value="/duplicateRegist.ju", method=RequestMethod.POST)
	public ModelAndView duplRegist(String mem_idx){
		List<String> list = ligdao.checkMyRgst(mem_idx);
		return new ModelAndView("juJson","myrgst",list);
	}
	
	@RequestMapping("/learningIndex.ju")
	public ModelAndView libList(HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		List<SubjectDTO> list = ligdao.checkSubjectList();
		String dateFormat="yyyy-MM-dd";
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		for(int i=0; i<list.size(); i++){
			String sdDay = sdf.format(list.get(i).getSj_sd());
			list.get(i).setSj_sday(sdDay);
			String edDay = sdf.format(list.get(i).getSj_ed());
			list.get(i).setSj_eday(edDay);
		}
		
		mav.addObject("sublist", list);
		mav.setViewName("learning/ligList");
		return mav;
	}
	
	@RequestMapping("/rgstList.ju")
	public ModelAndView rgstList(HttpSession session){
		String mem_idx = (String)session.getAttribute("sidx");
		if(mem_idx==null||mem_idx.equals("")){
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("msg", "로그인 후 이용 가능합니다.");
			mav.addObject("url", "/lee/login.ju");
			mav.setViewName("learning/ligMsg");
			return mav;
		}
		List<SubjectDTO> list = ligdao.checkMyRgstList(mem_idx);
		String dateFormat="yyyy-MM-dd";
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		for(int i=0; i<list.size(); i++){
			String sdDay = sdf.format(list.get(i).getSj_sd());
			list.get(i).setSj_sday(sdDay);
			String edDay = sdf.format(list.get(i).getSj_ed());
			list.get(i).setSj_eday(edDay);
		}
		return new ModelAndView("learning/rgstList","mylist",list);
	}
	
	@RequestMapping("/rgst.ju")
	public ModelAndView rgst(RegistDTO dto, HttpSession session){
		
		String mem_idx = (String)session.getAttribute("sidx");
		
		if(mem_idx==null||mem_idx.equals("")){
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("msg", "로그인 후 이용 가능합니다.");
			mav.addObject("url", "/lee/login.ju");
			mav.setViewName("learning/ligMsg");
			return mav;
		}
		
		dto.setMem_idx(mem_idx);
		
		Long unixTime=System.currentTimeMillis();
        dto.setRg_idx("RG"+unixTime);
		
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("msg", ligdao.rgst(dto)>0?"수강신청 성공":"수강신청 실패");
        mav.addObject("url", "/lee/learningIndex.ju");
        mav.setViewName("learning/ligMsg");
		return mav;
	}
	
	@RequestMapping("/deleteRgst.ju")
	public ModelAndView deleteRgst(RegistDTO dto, HttpSession session){
		String mem_idx = (String)session.getAttribute("sidx"); 
		
		if(mem_idx==null||mem_idx.equals("")){
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("msg", "로그인 후 이용 가능합니다.");
			mav.addObject("url", "/lee/login.ju");
			mav.setViewName("learning/ligMsg");
			return mav;
		}
		
		dto.setMem_idx(mem_idx);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg", ligdao.deleteRgst(dto)>0?"삭제성공":"삭제실패");
        mav.addObject("url", "/lee/learningIndex.ju");
        mav.setViewName("learning/ligMsg");
		return mav;
	}
}
