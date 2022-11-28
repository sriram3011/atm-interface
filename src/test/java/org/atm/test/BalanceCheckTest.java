package org.atm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.atm.main.dao.AtmOperation;
import org.atm.main.exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

public class BalanceCheckTest{
	@Test
	public void testCase1()throws ClassNotFoundException,SQLException,InvalidAmountException
	{
		assertEquals(31000,AtmOperation.balanceCheck(2202));
	}
	
}