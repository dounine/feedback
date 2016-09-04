package dnn.entity.invoice;

/**
 * Created by lgq on 16-9-4.
 * 开票信息
 */
public class InvoiceInfo {
    private String name ; //名称
    private String identifyNumber; //纳税人识别号
    private String address ;// 地址
    private String telephone ; //电话
    private String bank ; //开户行
    private String card ; //卡号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
