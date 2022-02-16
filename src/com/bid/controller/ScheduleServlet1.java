package com.bid.controller;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduleServlet1
 */
@WebServlet("/ScheduleServlet1")
public class ScheduleServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;
	int count = 0;
	public void destroy() {
		timer.cancel();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() {
		timer = new Timer();
		
	}
}
