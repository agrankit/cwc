package com.ank.cwc.app.model;

import java.util.List;

public class Matches {
	
	private List<Match> matchList;
	
	public Matches() {
		//matchList = new ArrayList<Match>();
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
	
	public void addMatch(Match m)
	{
		matchList.add(m);
	}

}
