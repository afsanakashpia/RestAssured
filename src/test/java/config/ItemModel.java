package config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemModel {
    private String ItemName;
    private int quantity;
    private String amount;
    private String month;
    private String remarks;
    private String purchaseDate;
    public ItemModel(String ItemName,int quantity,String amount,String remarks,String month,String purchaseDate){
        this.ItemName=ItemName;
        this.amount=amount;
        this.quantity=quantity;
        this.purchaseDate=purchaseDate;
        this.month=month;
        this.remarks=remarks;
    }
    public ItemModel(){

    }
}
