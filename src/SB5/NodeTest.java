package SB5;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import SB4.Node;

public class NodeTest {
	
	private Node<String> nodeString;
	private Node<Boolean> nodeBoolean;
	private Node<Byte> nodeByte;
	private Node<Integer> nodeInteger;
	private Node<Long> nodeLong;
	private Node<Float> nodeFloat;
	private Node<Double> nodeDouble;
	private Node<StringBuilder> nodeSB;
	private Node<BigDecimal> nodeBD;
	
	@Before
	public void setUp() throws Exception {
		nodeString = new Node<String>("Testvalue", new Node<String>("Testvalue2", null));
		nodeBoolean = new Node<Boolean>(true, new Node<Boolean>(false, null));
		nodeByte = new Node<Byte>((byte) 1, new Node<Byte>((byte) 2, null));
		nodeInteger = new Node<Integer>(10, new Node<Integer>(20, null));
		nodeLong = new Node<Long>(Long.valueOf(100L), new Node<Long>(Long.valueOf(200L), null));
		nodeFloat = new Node<Float>(1.1F, new Node<Float>(2.2F, null));
		nodeDouble = new Node<Double>(11.11D, new Node<Double>(22.22D, null));
		nodeSB = new Node<StringBuilder>(new StringBuilder("SB-Value"), new Node<StringBuilder>(new StringBuilder("SB-Value2"), null));
		nodeBD = new Node<BigDecimal>(new BigDecimal(1000), new Node<BigDecimal>(new BigDecimal(2000), null));
	}

	@After
	public void tearDown() throws Exception {
		nodeString = null;
		nodeBoolean = null;
		nodeByte = null;
		nodeInteger = null;
		nodeLong = null;
		nodeFloat = null;
		nodeDouble = null;
		nodeSB = null;
		nodeBD = null;
	}
	
	@Test
	public void testKonstruktor() {
		Assert.assertNotNull(nodeString);
		Assert.assertNotNull(nodeBoolean);
		Assert.assertNotNull(nodeByte);
		Assert.assertNotNull(nodeInteger);
		Assert.assertNotNull(nodeLong);
		Assert.assertNotNull(nodeFloat);
		Assert.assertNotNull(nodeDouble);
		Assert.assertNotNull(nodeSB);
		Assert.assertNotNull(nodeBD);
	}
		
	@Test
	public void testKonstruktorValues() {
		Assert.assertTrue("Testvalue".equals(nodeString.getValue()));
		Assert.assertNotNull(nodeString.getNext());
		Assert.assertTrue("Testvalue2".equals(nodeString.getNext().getValue()));
		Assert.assertNull(nodeString.getNext().getNext());
		
		Assert.assertTrue(nodeBoolean.getValue() == true);
		Assert.assertNotNull(nodeBoolean.getNext());
		Assert.assertTrue(nodeBoolean.getNext().getValue() == false);
		Assert.assertNull(nodeBoolean.getNext().getNext());
		
		Assert.assertTrue(nodeByte.getValue() == 1);
		Assert.assertNotNull(nodeByte.getNext());
		Assert.assertTrue(nodeByte.getNext().getValue() == 2);
		Assert.assertNull(nodeByte.getNext().getNext());
		
		Assert.assertTrue(nodeInteger.getValue() == 10);
		Assert.assertNotNull(nodeInteger.getNext());
		Assert.assertTrue(nodeInteger.getNext().getValue() == 20);
		Assert.assertNull(nodeInteger.getNext().getNext());
		
		Assert.assertTrue(nodeLong.getValue().equals(Long.valueOf(100L)));
		Assert.assertNotNull(nodeLong.getNext());
		Assert.assertTrue(nodeLong.getNext().getValue().equals(Long.valueOf(200L)));
		Assert.assertNull(nodeLong.getNext().getNext());
		
		Assert.assertTrue(nodeFloat.getValue().equals(Float.valueOf(1.1F)));
		Assert.assertNotNull(nodeFloat.getNext());
		Assert.assertTrue(nodeFloat.getNext().getValue().equals(Float.valueOf(2.2F)));
		Assert.assertNull(nodeFloat.getNext().getNext());
		
		Assert.assertTrue(nodeDouble.getValue().equals(Double.valueOf(11.11D)));
		Assert.assertNotNull(nodeDouble.getNext());
		Assert.assertTrue(nodeDouble.getNext().getValue().equals(Double.valueOf(22.22D)));
		Assert.assertNull(nodeDouble.getNext().getNext());
		
		Assert.assertTrue("SB-Value".equals(nodeSB.getValue().toString()));
		Assert.assertNotNull(nodeSB.getNext());
		Assert.assertTrue("SB-Value2".equals(nodeSB.getNext().getValue().toString()));
		Assert.assertNull(nodeSB.getNext().getNext());
		
		Assert.assertTrue(BigDecimal.valueOf(1000).equals(nodeBD.getValue()));
		Assert.assertNotNull(nodeBD.getNext());
		Assert.assertTrue(BigDecimal.valueOf(2000).equals(nodeBD.getNext().getValue()));
		Assert.assertNull(nodeBD.getNext().getNext());
	}
	
	@Test
	public void testValue() {
		nodeString.setValue("NeuerValue");
		Assert.assertSame("NeuerValue", nodeString.getValue());
		
		nodeBoolean.setValue(false);
		Assert.assertSame(false, nodeBoolean.getValue());
		
		nodeByte.setValue((byte) 9);
		Assert.assertSame((byte) 9, nodeByte.getValue());
		
		nodeInteger.setValue(99);
		Assert.assertSame(99, nodeInteger.getValue());
		
		nodeLong.setValue(999L);
		Assert.assertTrue(Long.valueOf(999L).equals(nodeLong.getValue()));
		
		nodeFloat.setValue(9.9F);
		Assert.assertTrue(Float.valueOf(9.9F).equals(nodeFloat.getValue()));
		
		nodeDouble.setValue(99.99D);
		Assert.assertTrue(Double.valueOf(99.99D).equals(nodeDouble.getValue()));
		
		nodeSB.setValue(new StringBuilder("Aktualisierung"));
		Assert.assertTrue("Aktualisierung".equals(nodeSB.getValue().toString()));
		
		nodeBD.setValue(new BigDecimal(9999));
		Assert.assertTrue(BigDecimal.valueOf(9999).equals(nodeBD.getValue()));
	}
		
	@Test
	public void testNext() {
		nodeString.setNext(new Node<String>("NeuMitSetter", null));
		Assert.assertTrue(nodeString.getNext().getValue().equals("NeuMitSetter"));
		Assert.assertNull(nodeString.getNext().getNext());
		
		nodeBoolean.setNext(new Node<Boolean>(false, null));
		Assert.assertTrue(nodeBoolean.getNext().getValue().equals(false));
		Assert.assertNull(nodeBoolean.getNext().getNext());
		
		nodeByte.setNext(new Node<Byte>((byte) 5, null));
		Assert.assertTrue(nodeByte.getNext().getValue().equals((byte) 5));
		Assert.assertNull(nodeByte.getNext().getNext());
		
		nodeInteger.setNext(new Node<Integer>(55, null));
		Assert.assertTrue(nodeInteger.getNext().getValue().equals(55));
		Assert.assertNull(nodeInteger.getNext().getNext());
		
		nodeLong.setNext(new Node<Long>(555L, null));
		Assert.assertTrue(nodeLong.getNext().getValue().equals(555L));
		Assert.assertNull(nodeLong.getNext().getNext());
		
		nodeFloat.setNext(new Node<Float>(5.5F, null));
		Assert.assertTrue(nodeFloat.getNext().getValue().equals(5.5F));
		Assert.assertNull(nodeFloat.getNext().getNext());
		
		nodeDouble.setNext(new Node<Double>(55.55D, null));
		Assert.assertTrue(nodeDouble.getNext().getValue().equals(55.55D));
		Assert.assertNull(nodeDouble.getNext().getNext());
		
		nodeSB.setNext(new Node<StringBuilder>(new StringBuilder("NeuerSB-Wert"), null));
		Assert.assertTrue("NeuerSB-Wert".equals(nodeSB.getNext().getValue().toString()));
		Assert.assertNull(nodeSB.getNext().getNext());
		
		nodeBD.setNext(new Node<BigDecimal>(new BigDecimal(5555), null));
		Assert.assertTrue(BigDecimal.valueOf(5555).equals(nodeBD.getNext().getValue()));
		Assert.assertNull(nodeBD.getNext().getNext());
	}
	
}
