package fr.ensma.ia.cpo.projet.plateau;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestPosition {

	private Position p;
	private Constantes c;
	
	@Before
	public void initTest(){
		p = new Position(2);
		c = new Constantes(2);
	}
	
	@Test
	public void testVerifPosition() {
		Assert.assertTrue(p.verifPosition(c));
		c = new Constantes(3);
		Assert.assertFalse(p.verifPosition(c));
		p = new Position(3);
		Assert.assertTrue(p.verifPosition(c));
	}

	@Test
	public void testCiblerDimension() {
		Assert.assertEquals(1, p.ciblerDimension(c, 0));
		Assert.assertEquals(10, p.ciblerDimension(c, 1));
		Assert.assertEquals(1, p.ciblerDimension(c, 2));
		c = new Constantes(3);
		p = new Position(c);
		Assert.assertEquals(1, p.ciblerDimension(c, 0));
		Assert.assertEquals(10, p.ciblerDimension(c, 1));
		Assert.assertEquals(100, p.ciblerDimension(c, 2));
		Assert.assertEquals(1, p.ciblerDimension(c, 3));
	}

	@Test
	public void testCiblerPosition() {
		p.ciblerPosition(c, 0);
		Assert.assertEquals(0, p.getPosition()[0]);
		Assert.assertEquals(0, p.getPosition()[1]);
		p.ciblerPosition(c, 99);
		Assert.assertEquals(9, p.getPosition()[0]);
		Assert.assertEquals(9, p.getPosition()[1]);
		p.ciblerPosition(c, 57);
		Assert.assertEquals(7, p.getPosition()[0]);
		Assert.assertEquals(5, p.getPosition()[1]);
		int[] l ={5,3};
		c.setLimites(l);
		p = new Position(c);
		p.ciblerPosition(c, 7);
		Assert.assertEquals(2, p.getPosition()[0]);
		Assert.assertEquals(1, p.getPosition()[1]);
		p.ciblerPosition(c, 8);
		Assert.assertEquals(3, p.getPosition()[0]);
		Assert.assertEquals(1, p.getPosition()[1]);
		p.ciblerPosition(c, 4);
		Assert.assertEquals(4, p.getPosition()[0]);
		Assert.assertEquals(0, p.getPosition()[1]);
		p.ciblerPosition(c, 12);
		Assert.assertEquals(2, p.getPosition()[0]);
		Assert.assertEquals(2, p.getPosition()[1]);
	}

	@Test
	public void testPassageCoordEntier() {
		
	}

	@Test
	public void testRecupCoord() {
		Assert.assertEquals(0, p.recupCoord(c, 0));
		Assert.assertEquals(0, p.recupCoord(c, 1));
		int[] a ={8,7};
		p.setPosition(a);
		Assert.assertEquals(8, p.recupCoord(c, 0));
		Assert.assertEquals(7, p.recupCoord(c, 1));
		c = new Constantes(3);
		p = new Position(c);
		Assert.assertEquals(0, p.recupCoord(c, 0));
		Assert.assertEquals(0, p.recupCoord(c, 1));
		Assert.assertEquals(0, p.recupCoord(c, 2));
	}

	@Test
	public void testVerifCoordFrontiere() {
		p.ciblerPosition(c, 9);
		Assert.assertTrue(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 19);
		Assert.assertTrue(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 10);
		Assert.assertFalse(p.verifCoordFrontiere(c));
		c = new Constantes(3);
		p = new Position(c);
		p.ciblerPosition(c, 9);
		Assert.assertTrue(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 19);
		Assert.assertTrue(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 10);
		Assert.assertFalse(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 100);
		Assert.assertFalse(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 101);
		Assert.assertFalse(p.verifCoordFrontiere(c));
	}

	@Test
	public void testVerifCoordFrontiere2D() {
		p.ciblerPosition(c, 9);
		Assert.assertTrue(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 19);
		Assert.assertTrue(p.verifCoordFrontiere(c));
		p.ciblerPosition(c, 10);
		Assert.assertFalse(p.verifCoordFrontiere(c));
	}
}
