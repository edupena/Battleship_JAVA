package fr.ensma.ia.cpo.projet.plateau;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestConstantes {
	private Constantes c;
	
	@Before
	public void initTest(){
		c =new Constantes(3);
	}

	@Test
	public void testSetLimites() {
		int lim[]={10,10,10};
		c.setLimites(lim);
		Assert.assertEquals(3,c.getDimension());
		Assert.assertEquals(1000,c.getNbecase());
		int l[]={10,10};
		c.setLimites(l);
		Assert.assertEquals(2,c.getDimension());
		Assert.assertEquals(100,c.getNbecase());
	}
}
