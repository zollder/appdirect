package org.app.domain.services.impl;

import oauth.signpost.OAuthConsumer;

import org.apache.log4j.Logger;
import org.app.domain.model.Creator;
import org.app.domain.model.entities.User;
import org.app.domain.services.EventDataService;
import org.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;

@Service("eventDataService")
public class EventDataServiceImpl implements EventDataService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private OAuthConsumer oAuthConsumer;

	private OAuthRestTemplate oauthRestTemplate;

    private static Logger logger = Logger.getLogger(EventDataServiceImpl.class);

    // ---------------------------------------------------------------------------------------------
	@Override
	public <T> T getEvent(String eventUrl, Class<T> resultType)
	{
		T eventEntity = oauthRestTemplate.getForEntity(eventUrl, resultType).getBody();
		logger.debug(eventEntity.toString());

		return eventEntity;
	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public User mapCreatorToUser(Creator creator, User user)
	{
		user.setOpenId(creator.getOpenId());
		user.setFirstName(creator.getFirstName());
		user.setLastName(creator.getLastName());
		user.setEmail(creator.getEmail());
		return user;
	}
//    // ---------------------------------------------------------------------------------------------
//	@Override
//	public <T> T getEvent(String url, Class<T> resultType) throws Exception
//	{
//		String responseContent = getEventPayload(oAuthConsumer.sign(url));
//
//		T result = null;
//        if (StringUtils.isNotBlank(responseContent))
//            result = parseEventPayload(responseContent, resultType);
//
//        logger.debug("AppDirect event payload: " + result);
//        return result;
//	}
//
//	// ---------------------------------------------------------------------------------------------
//	private String getEventPayload(final String eventUrl) throws ClientProtocolException, IOException
//	{
//        HttpClient client = null;
//        HttpResponse response = null;
//        try
//        {
//            client = HttpClientBuilder.create().build();
//            response = client.execute(new HttpGet(eventUrl));
//            return EntityUtils.toString(response.getEntity(), "UTF-8");
//        }
//        finally
//        {
//            if(response != null)
//                HttpClientUtils.closeQuietly(response);
//
//            if(client != null)
//                HttpClientUtils.closeQuietly(client);
//        }
//	}
//
//	// ---------------------------------------------------------------------------------------------
//	@SuppressWarnings("unchecked")
//	private <T> T parseEventPayload(String eventPayload, Class<T> eventType) throws JAXBException
//	{
//        T result = null;
//        if (eventType.isAnnotationPresent(XmlRootElement.class))
//        {
//            JAXBContext context = JAXBContext.newInstance(eventType);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            result = (T) unmarshaller.unmarshal(new StringReader(eventPayload));
//        }
//        else if (String.class.isAssignableFrom(eventType))
//        {
//            result = (T) eventPayload;
//        }
//
//		return result;
//	}

	// ---------------------------------------------------------------------------------------------
	public OAuthRestTemplate getOauthRestTemplate()
	{
		return this.oauthRestTemplate;
	}

	public void setOauthRestTemplate(OAuthRestTemplate template)
	{
		this.oauthRestTemplate = template;
	}
}
