package com.news.content.test;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;

@SuppressLint("NewApi")
public class TestContentFragment extends Fragment{
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState)  
	    {  
	        return inflater.inflate(R.layout.fragment_test_content, container, false);  
	    }  
}
