package com.pipl.api;

import static org.junit.Assert.*;

import org.junit.Test;
import com.pipl.api.search.SearchConfiguration.AtomicExpression;
import com.pipl.api.search.SearchConfiguration.Expression;

public class ExpressionTest {

	@Test
	public void testAtomic() {
		Expression e = AtomicExpression.NAME;
		assertEquals("NAME", e.toString());
	}

	@Test
	public void testAnd() {
		Expression e = AtomicExpression.NAME.and(AtomicExpression.ADDRESS);
		assertEquals("NAME & ADDRESS", e.toString());
	}

	@Test
	public void testOr() {
		Expression e = AtomicExpression.NAME.or(AtomicExpression.ADDRESS, AtomicExpression.PHONE);
		assertEquals("NAME | ADDRESS | PHONE", e.toString());
	}

	@Test
	public void testComplex() {
		Expression e = AtomicExpression.NAME.or(AtomicExpression.ADDRESS, AtomicExpression.PHONE).and(AtomicExpression.EMAIL);
		assertEquals("(NAME | ADDRESS | PHONE) & EMAIL", e.toString());
		e = AtomicExpression.NAME.and(AtomicExpression.ADDRESS).or(AtomicExpression.EMAIL, AtomicExpression.PHONE);
		assertEquals("NAME & ADDRESS | EMAIL | PHONE", e.toString());
		e = AtomicExpression.NAME.and(AtomicExpression.ADDRESS).or(AtomicExpression.EMAIL.or(AtomicExpression.PHONE));
		assertEquals("NAME & ADDRESS | EMAIL | PHONE", e.toString());
	}

}
