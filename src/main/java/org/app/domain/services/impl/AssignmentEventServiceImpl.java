package org.app.domain.services.impl;

import org.app.domain.model.Response;
import org.app.domain.services.AssignmentEventService;
import org.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("assignmentEventService")
public class AssignmentEventServiceImpl implements AssignmentEventService
{
	@Autowired
	private UserRepository userRepository;


    // ---------------------------------------------------------------------------------------------

	@Override
	public Response assign(String event)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response unassign(String event)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
