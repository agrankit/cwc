package com.ank.cwc.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ank.cwc.app.model.Match;
import com.ank.cwc.app.model.Matches;
import com.ank.cwc.app.persist.EMF;
import com.ank.cwc.app.persist.PMF;

@Controller
@RequestMapping("/matches")
public class CWCMatchController {
	
	@RequestMapping(value = "/add",method=RequestMethod.GET)
	public String addMatch(ModelMap map)
	{
		return "addmatch";
	}
	
	@RequestMapping(value = "/add" , method=RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request,ModelMap map)
	{
		String team1 = request.getParameter("team1");
		String team2 = request.getParameter("team2");
		String when = request.getParameter("when");
		String where = request.getParameter("where");
		String status = request.getParameter("status");
		
		Match match = new Match();
		match.setTeam1(team1);
		match.setTeam2(team2);
		match.setWhen(when);
		match.setWhere(where);
		match.setResult(status);
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try
		{
			pm.makePersistent(match);
		}finally{
			pm.close();
		}
		
		return new ModelAndView("redirect:list");
		
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listMatch(ModelMap map)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Match.class);
		
		List<Match> matches = null;
		
		try{
			matches = (List<Match>) q.execute();
			
			if(matches.isEmpty())
				map.addAttribute("matchList", null);
			else
				map.addAttribute("matchList", matches);
		}finally{
			q.closeAll();
			pm.close();
		}
		
		return "listmatch";
		
		
	}
	
	@ModelAttribute("m")
	@RequestMapping(value="/getmatches",method=RequestMethod.GET)
	public Match getMatch(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Match.class);
		List<Match> matches = null;
		
		Match m = null;
		try {
			matches = (List<Match>) q.execute();
			
			if(!matches.isEmpty())
				
				m = matches.get(0);
		} finally{
			q.closeAll();
			pm.close();
		}
		return m;
	}
	
	@ModelAttribute("matchList")
	@RequestMapping(value="/getallmatches" , method = RequestMethod.GET)
	public ArrayList<Match> getAllMatches(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Match.class);
		List<Match> list = null;
		ArrayList<Match> matchList = new ArrayList<Match>();
		
		try {
			list = (List<Match>) q.execute();
			for(Match m : list)
				matchList.add(m);
		} finally{
			q.closeAll();
			pm.close();
		}
		
		return matchList;
	}
	
	
	@RequestMapping(value="/delete/{key}",method=RequestMethod.GET)
	public ModelAndView deleteMatch(@PathVariable String key,HttpServletRequest request , ModelMap map)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Match m = pm.getObjectById(Match.class,key);
			pm.deletePersistent(m);
		}
		finally{
			pm.close();
		}
		
		return new ModelAndView("redirect:../list");
	}
	
	
	@RequestMapping(value = "/update/{key}",method = RequestMethod.GET)
	public String updateMatch(@PathVariable String key,ModelMap map)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Match m = pm.getObjectById(Match.class,key);
			if(m!= null)
			{
				map.addAttribute("match", m);
			}
		}finally{
			pm.close();
		}
		
		return "updatematch";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView updateMatchValues(HttpServletRequest request , ModelMap map)
	{
		String team1 = request.getParameter("team1");
		String team2 = request.getParameter("team2");
		String when = request.getParameter("when");
		String where = request.getParameter("where");
		String status = request.getParameter("result");
		
		String id = request.getParameter("id");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Match m = pm.getObjectById(Match.class,id);
			m.setTeam1(team1);
			m.setTeam2(team2);
			m.setWhen(when);
			m.setWhere(where);
			m.setResult(status);
		}finally{
			pm.close();
			
		}
		
		return new ModelAndView("redirect:list");
	}

}
