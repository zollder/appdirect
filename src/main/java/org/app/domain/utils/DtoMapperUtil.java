package org.app.domain.utils;

import org.app.domain.model.Creator;
import org.app.domain.model.entities.User;


//--------------------------------------------------------------------------------------------------
/** DtoMapperUtil implementation.
 *  Provides Event<->Subcriber conversion services. */
//--------------------------------------------------------------------------------------------------
public class DtoMapperUtil
{
	/** Maps {@link Creator} to {@link User} fields. */
	public static User convertToUser(Creator creator, User user)
	{
		user.setOpenId(creator.getOpenId());
		user.setFirstName(creator.getFirstName());
		user.setLastName(creator.getLastName());
		user.setEmail(creator.getEmail());
		return user;
	}
}