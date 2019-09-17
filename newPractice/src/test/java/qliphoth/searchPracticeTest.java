package qliphoth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class searchPracticeTest {

    private searchPractice sp;

    @Before
    public void before() {
       sp = new searchPractice();
    }

    @Test
    public void singleNonDuplicate() {
        int[] nums1 = {1,2,2,3,3,7,7};
        int[] nums2 = {2,2,6,6,7};
        assertEquals(1, searchPractice.singleNonDuplicate(nums1));
        assertEquals(7,searchPractice.singleNonDuplicate(nums2));
    }

    @Test
    public void shipWithinDays() {
        int[] weight = {1,3,2,5,6};
        assertEquals(6, sp.shipWithinDays(weight, 4));
    }
}