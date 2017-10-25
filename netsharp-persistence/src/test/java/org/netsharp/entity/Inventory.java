//package org.netsharp.entity;
//
//import org.netsharp.core.annotations.Column;
//import org.netsharp.core.annotations.Encryption;
//import org.netsharp.core.annotations.Table;
//
//@Table(name="test_inventory")
//public class Inventory extends BizEntity {
//	
//	private static final long serialVersionUID = -7455350005834354975L;
//	
//	private Color color=Color.blank;
//    private InventoryType type=InventoryType.product;
//    
//    @Encryption//此字段是加密配置
//    @Column(size=200)
//    private String des;
//
//	public Color getColor() {
//		return color;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}
//
//	public InventoryType getType() {
//		return type;
//	}
//
//	public void setType(InventoryType type) {
//		this.type = type;
//	}
//
//	public String getDes() {
//		return des;
//	}
//
//	public void setDes(String des) {
//		this.des = des;
//	}
//}
//
