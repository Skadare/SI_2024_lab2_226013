import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void everyBranchTest(){
        //1
        RuntimeException ex;
        List<Item> firstItems = null;
        int payment = 0;
        ex =  assertThrows(RuntimeException.class, () -> SILab2.checkCart(firstItems, payment));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        //2
        List<Item> secondItems = new ArrayList<>();
        secondItems.add(new Item("Mleko",null,70,0));
        secondItems.add(new Item("Banane", "1123548", 57,0.7F));
        secondItems.add(new Item("Koka-kola","123654",75,0.99F));
        secondItems.add(new Item("Leb","5648192",68,0.95F));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(secondItems, payment));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //3
        List<Item> thirdItems = new ArrayList<>();
        thirdItems.add(new Item("Mleko","452165",70,0));
        thirdItems.add(new Item("Banane", "1123548", 57,0.7F));
        thirdItems.add(new Item("Koka-kola","ABCDEF123",75,0.99F));
        thirdItems.add(new Item("Leb","5648192",68,0.95F));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(thirdItems, payment));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //4
        List<Item> finalItems = new ArrayList<>();
        int fourth_payment = 500;
        finalItems.add(new Item("Mleko","452165",70,0));
        finalItems.add(new Item("Krevet", "0123548", 305, 0.7F));
        finalItems.add(new Item("Koka-kola","1239510",75,0.99F));
        finalItems.add(new Item("","5648192",168,0.95F));
        assertTrue(SILab2.checkCart(finalItems, fourth_payment));

        //5
        int fifth_payment = 200;
        assertFalse(SILab2.checkCart(finalItems, fifth_payment));
    }

    @Test
    void multipleConditionTest(){

        //1 -> item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'
        List<Item> firstItems = new ArrayList<>();
        firstItems.add(new Item("Krevet", "0123548", 305, 0.7F));
        assertTrue(SILab2.checkCart(firstItems,184));

        //2 -> item.getPrice() > 300 && item.getDiscount() < 0 && item.getBarcode().charAt(0) == '0'
        List<Item> secondItems = new ArrayList<>();
        secondItems.add(new Item("Krevet","0123548", 305, 0));
        assertTrue(SILab2.checkCart(secondItems,305));

        //3 -> item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) != '0'
        List<Item> thirdItems = new ArrayList<>();
        thirdItems.add(new Item("Krevet","1235480", 305, 0));
        assertTrue(SILab2.checkCart(thirdItems,305));

        //4 -> item.getPrice() < 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'
        List<Item> fourthItems = new ArrayList<>();
        fourthItems.add(new Item("Krevet","1235480", 195, 0));
        assertTrue(SILab2.checkCart(fourthItems,195));
    }

}