package org.app.domain.services.impl;

import org.apache.log4j.Logger;
import org.app.domain.model.Creator;
import org.app.domain.model.entities.User;
import org.app.domain.services.EventDataService;
import org.app.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;

@Service("eventDataService")
public class EventDataServiceImpl implements EventDataService
{
    @Value("${oauth.consumer.key}")
    private String key;

    @Value("${oauth.consumer.secret}")
    private String secret;

	private OAuthRestTemplate oauthRestTemplate;

    private static Logger logger = Logger.getLogger(EventDataServiceImpl.class);

	// ---------------------------------------------------------------------------------------------
	@Override
	public User mapCreatorToUser(Creator creator, User user)
	{
		user.setOpenId(Utils.extractOpenId(creator.getOpenId()));
		user.setFirstName(creator.getFirstName());
		user.setLastName(creator.getLastName());
		user.setEmail(creator.getEmail());
		return user;
	}

	@Override
	public <T> T getEvent(String eventUrl, Class<T> resultType) throws Exception
	{
		BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setId("test-resource-Id");
		resourceDetails.setConsumerKey(key);
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(secret));
		resourceDetails.setUse10a(true);
		resourceDetails.setUserAuthorizationURL("http:/localhost:8080/appdirect/login");

		oauthRestTemplate = new OAuthRestTemplate(resourceDetails);

		T eventEntity = oauthRestTemplate.getForEntity(eventUrl, resultType).getBody();
		logger.debug(eventEntity.toString());

		return eventEntity;
	}
}
