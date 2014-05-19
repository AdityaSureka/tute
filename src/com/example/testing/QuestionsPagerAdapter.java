package com.example.testing;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

	public class QuestionsPagerAdapter extends FragmentPagerAdapter{
		List<Fragment> fragments;
        private List<String> titles;
		
		public QuestionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			// TODO Auto-generated constructor stub
			super(fm);
			this.fragments = fragments;
		    this.titles    = new ArrayList<String>();
		}
		  
		public void addItem(String url, String title) {
			Fragment myFragment = new QuestionsFragment();
		    Bundle args = new Bundle();
		    args.putString("url", url);
		    args.putString("title", "hello");
		    myFragment.setArguments(args);
		    this.fragments.add(myFragment);
		    this.titles.add(title);
		}
		  
		@Override 
		public Fragment getItem(int position) {
		       return this.fragments.get(position);
		}
		  
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.fragments.size();
		}
	    public CharSequence getPageTitle(int position) {
	        return "Question " + (position + 1);
	    }
	}

