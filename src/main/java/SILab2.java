import java.util.List;

class Item {
    String name;
    String barcode; //numerical
    int price;
    float discount;

    public Item(String name, String code, int price, float discount) {
        this.name = name;
        this.barcode = code;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String code) {
        this.barcode = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){
        if (allItems == null){ //1
            throw new RuntimeException("allItems list can't be null!"); //2
        }

        float sum = 0; //3

        for (int i = 0; i < allItems.size(); i++){ //4
            Item item = allItems.get(i); //5
            if (item.getName() == null || item.getName().length() == 0){ //6
                item.setName("unknown"); //7
            }
            if (item.getBarcode() != null){ //8
                String allowed = "0123456789"; //9
                char chars[] = item.getBarcode().toCharArray(); //10
                for (int j = 0; j < item.getBarcode().length(); j++){ //11
                    char c = item.getBarcode().charAt(j); //12
                    if (allowed.indexOf(c) == -1){ //13
                        throw new RuntimeException("Invalid character in item barcode!"); //14
                    }
                } //15
                if (item.getDiscount() > 0){ //16
                    sum += item.getPrice()*item.getDiscount(); //17
                }
                else { //18
                    sum += item.getPrice(); //19
                }
            }
            else { //20
                throw new RuntimeException("No barcode!"); //21
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){ //22
                sum -= 30; //23
            }
        } // 24
        if (sum <= payment){ //25
            return true; //26
        }
        else { //27
            return false; //28
        }
    } //29
}