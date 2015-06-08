package org.app.security;

public class SecurityPasswordEncoderMock extends PasswordEncoder
{
	private final static String prefix = "encoded_";

	@Override
	public String encodePassword(String rawPassword)
	{
		// something different from the input
		return prefix + rawPassword;
	}
}
