package com.kotesu.springboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kotesu.springboot.repositories.MyDataRepository;

@Controller
public class HelloController{

	@Autowired
	MyDataRepository repository;

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg","this is sample content.");
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav;
	}

	@RequestMapping(value = "/",method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(@ModelAttribute("formModel") MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}

    @PostConstruct
    public void init() {

    	MyData d1 = new MyData();
    	d1.setName("tuyano");
    	d1.setAge(123);
    	d1.setMail("syoda@tuyano.com");
    	d1.setMemo("this is my data!");
    	repository.saveAndFlush(d1);

    	MyData d2 = new MyData();
    	d2.setName("hanako");
    	d2.setAge(15);
    	d2.setMail("hanako@flower");
    	d2.setMemo("my girl friend");
    	repository.saveAndFlush(d2);

    	MyData d3 = new MyData();
    	d3.setName("sachiko");
    	d3.setAge(37);
    	d3.setMail("sachiko@happy");
    	d3.setMemo("my work friend...");
    	repository.saveAndFlush(d3);

    }



	class DataObject{
		private int id;
		private String name;
		private String value;

		public DataObject(int id, String name, String value) {
			super();
			this.id = id;
			this.name = name;
			this.value = value;
		}

		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		public String getName() {return name;}
		public void setName(String name) {this.name= name;}
		public String getValue() {return value;}
		public void setValue(String value) {this.value=value;}
	}
}