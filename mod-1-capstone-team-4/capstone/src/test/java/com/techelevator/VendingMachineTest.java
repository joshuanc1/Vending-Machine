package com.techelevator;

import com.techelevator.utility.VendingMachine;
import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

    @Test
    public void  feed_money_total_total_test() {
        VendingMachine testVend = new VendingMachine();

        double expected = 25.00;
        double actual = testVend.feedMoneyUpdateTotal("25.00");

        Assert.assertEquals(expected,actual,0);
    }
}
