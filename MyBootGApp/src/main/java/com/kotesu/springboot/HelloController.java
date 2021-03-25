package com.kotesu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController{

	@RequestMapping("/{month}")
	public ModelAndView index(@PathVariable int month, ModelAndView mav) {
		mav.setViewName("index");
		int m = Math.abs(month) % 12;
		m = m == 0 ? 12 : m;
		mav.addObject("month",m);
		mav.addObject("check",Math.floor(m / 3));
		return mav;
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