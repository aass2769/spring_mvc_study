package com.woomin.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class DataBean1 {

	//주입된 값의 길이가 0이면 오류발생. 공백도 길이로 인식함
	@NotEmpty
	private String data1;
	
	//주입된 값의 길이가 0이면 오류발생. 공백은 길이로 인식하지않음
	@NotBlank
	private String data2;
	
	//양수만 허용한다. 0 or 음수는 오류를 발생한다. 
	@Positive
	private String data3;
	
	//양수와 0을 허용한다. 음수는 오류를 발생한다.
	@PositiveOrZero
	private String data4;
	
	//음수만 허용한다. 0과 양수는 오류를 발생한다.
	@Negative
	private String data5;
	
	//음수와 0을 허용한다.양수는 오류를 발생한다.
	@NegativeOrZero
	private String data6;
	
	//@가 있으면 통과. @가없으면 오류를 발생한다.
	@Email
	private String data7;
	
	public DataBean1() {
		this.data1 = "abcd";
		this.data2 = "abcd";
		this.data3 = "40";
		this.data4 = "50";
		this.data5 = "-1";
		this.data6 = "-10";
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData4() {
		return data4;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public String getData5() {
		return data5;
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	public String getData6() {
		return data6;
	}
	public void setData6(String data6) {
		this.data6 = data6;
	}
	public String getData7() {
		return data7;
	}
	public void setData7(String data7) {
		this.data7 = data7;
	}
	
	
}
