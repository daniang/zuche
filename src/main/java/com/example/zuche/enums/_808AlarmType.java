package com.example.zuche.enums;

import java.util.ArrayList;
import java.util.List;

public enum _808AlarmType {
	urgency(0, "紧急报警"),
	overSpeed(1, "超速报警"),
	tiredDriver(2, "疲劳驾驶"),
	dangerWarning(3, "危险预警"),
	GNSSFault(4, "GNSS模块发生故障"),
	GNSSNoTouch(5, "GNSS天线未接或被剪断"),
	GNSSShortout(6, "GNSS天线短路"),
	underVoltage(7, "终端主电源欠压"),
	powerDown(8, "终端主电源掉电"),
	LCDFault(9, "终端LCD或显示器故障"),
	TTSFault(10, "TTS模块故障"),
	cameraFault(11, "摄像头故障"),
	transportCardFault(12, "道路运输证IC卡模块故障"),
	overSpeedWarning(13, "超速预警"),
	tiredWarning(14, "疲劳驾驶预警"),
	disassemble(15,"拆卸报警"),
	extend16(16,"SOS报警"),
	extend17(17,"暂无定义"),
	overTimeDriver(18, "当天累计驾驶超时"),
	overTimePark(19, "超时停车"),
	turnoverRange(20, "进出区域"),
	turnonverLine(21, "进出路线"),
	driverOnRoad(22, "路段行驶时间不足/过长"),
	departureLine(23, "路线偏离报警"),
	VSSFault(24, "车辆VSS故障"),
	oilMassFault(25, "车辆油量异常"),
	StolenVehicle(26, "车辆被盗"),
	illegalityTurnon(27, "车辆非法点火"),
	illegalityOffset(28, "车辆非法位移"),
	crash(29, "碰撞预警"),
	rollOver(30, "侧翻报警"),
	illegalityOpenDoor(31, "非法开门报警");
	private int index;
	private String desc;

	private _808AlarmType(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}

//	public static String getDesc(int index) {
//		for (com.vehicle.enums._808AlarmType type : com.vehicle.enums._808AlarmType.values()) {
//			if (type.index == index) {
//				return type.desc;
//			}
//		}
//
//		return null;
//	}
	public static void main(String[] args) {
////		String alamCode = "01000000000000001000000000000000";
//		String alamCode = "00000000000000110000000100000000";
//
//		StringBuffer sb = new StringBuffer();
//		sb.append(alamCode);
//		sb.reverse();
//		List<String> strings = new ArrayList<>();
//		for(int i = 0 ; i < sb.toString().toCharArray().length; i++ ){
//			if (sb.toString().toCharArray()[i] == '1') {
//				String desc = com.vehicle.enums._808AlarmType.getDesc(i);
//				strings.add(desc);
//			}
//		}
//		System.out.println(strings);
	}
}
