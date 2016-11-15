package com.zhao.mapp.model;

import java.io.Serializable;
import java.util.Date;

public class TaskModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2884039025606045399L;
	private	String	annalid;	//	系统编码
	private	String	form_code;	//	申报单号
	private	String	se_accept_code;	//	设备受理编号
	private	String	se_type;	//	设备类别
	private	String	apply_type;	//	检验类型
	private	String	apply_kind;	//	检验种类
	private	String	se_flag;	//	设备状态
	private	String	baseinfo_id;	//	设备库主键
	private	String	se_code;	//	设备注册代码
	private	String	make_num;	//	设备产品编号
	private	String	se_name;	//	设备名称
	private	String	model;	//	规格型号
	private	String	se_num;	//	设备数量
	private	String	remark;	//	备注
	private	String	user_id;	//	用户ID
	private	String	data_id;	//	工作流主键
	private	String	unitinfo_id;	//	企业库主键
	private	String	fk_id_shenbao_form;	//	申报单主键
	private	String	reportall_id;	//	报告汇总表主键
	private	Date	apply_date;	//	申报日期
	private	String	unit_name;	//	申报公司名称
	private	String	apply_unit_address;	//	申报公司地址
	private	String	tel;	//	公司电话
	private	String	apply_person;	//	联系人姓名
	private	String	linker_shouji;	//	联系人手机号
	private	String	district_code;	//	设备地址行政区划
	private	String	district;	//	设备地址
	private	String	accept_personid;	//	受理人ID
	private	String	accept_person;	//	受理人
	private	Date	accept_date;	//	受理日期
	private	String	testdepart;	//	检验科室
	private	String	test_leader;	//	检验组长姓名
	private	String	testpersons;	//	检验组员姓名
	private	String	testleader;	//	检验组长ID
	private	String	testperson;	//	检验组员id
	private	String	isreceiveperson;	//	不接受人员
	private	String	reason;	//	不接受原因
	private	Date	isreceivetime;	//	退回时间
	private	String	is_fee;	//	是否缴费
	private	String	fee_should_price;	//	应收金额
	private	String	fee_actual_price;	//	实收金额
	private	String	payee_id;	//	收款人ID
	private	String	payee_time;	//	收款时间
	private	String	invoice_code;	//	发票编号
	private	String	payee_name;	//	收款人姓名
	private	String	drawee_name;	//	交款人姓名
	private	String	drawee_tel;	//	交款人联系电话
	private	String	report_is_take;	//	报告是否取走
	private	String	report_person_name;	//	报告取走人
	private	String	report_take_time;	//	报告取走时间
	private	String	report_person_tel;	//	报告取走人联系电话
	private	String	task_flag;	//	原始记录状态（0原始 1 传递给客户端 2客户端已提交原始记录）
	private	String	ouid;	//	eg：371600110
	private	String	in_out_net_flag;	//	内外网标识
	private	String	security_code;	//	防伪编号
	private	String	construction_num;	//	告知单号
	private	String	remark1;	//	备注1
	private	String	payment_status;	//	
	private	String	se_kind;	//	
	private	String	sydw_id;	//	
	private	String	reg_dept;	//	登记机关
	private	String	testdepartid;	//	检验科室id
	private	Date	printdate;	//	打印时间
	private	String	use_unit;	//	使用单位
	private	String	install_unit;	//	安装单位
	private	String	if_car;	//	是否是罐车类型
	private	String	if_qiping;	//	是否是气瓶类型
	private	String	if_fujian;	//	是否复检
	private	String	sf_fapiao;	//	
	private	String	use_reg_num;	//	使用登记证编号
	private	String	last_test_date;	//	上次检验有效日期
	private	String	if_aqf;	//	是否是安全阀
	private	String	del;	//	逻辑删除标识
	private	String	ifcaiwu;	//	是否发送财务
	private	String	if_yuzhuce;	//	*是否为预注册设备
	private	String	jiaofeiunit;	//	缴费单位
	private	String	hjsqdate;	//	缓缴申请时间
	private	String	hjtydate;	//	缓缴同意时间
	private	String	hjjsdate;	//	缓缴结束时间
	private	String	issuer;	//	发放人
	private	Date	issue_date;	//	发放日期
	private	String	receiptor;	//	领取人
	private	String	ttracking_number;	//	快递单号
	private	String	ifcaiwu_date;	//	发送财务、申请缓缴、集中缴费时间
	private	String	isgl;	//	是否关联 0或者空---未关联  1----已关联
	private	String	securitycode;	//	
	private	String	archive_state;	//	存档
	private	String	operate_time;	//	
	private	String	bjfee;	//	补缴费用
	private	String	bjremark;	//	补缴说明
	private	String	isbj;	//	是否补缴 默认 0或者 空，1---补缴
	private	String	bj_fapiao;	//	补缴费用的发票号
	private	String	bjxzremark;	//	补缴费用--修正说明
	private	String	isbjsh;	//	补缴是否审核 默认 0或者 空，1---通过审核
	private	String	payee_time_bj;	//	补缴收款时间
	private	String	isbjsp;	//	补缴是否审批 默认 0或者 空，1---通过审批
	private String report_name;//报告名称
	private String test_book_num;//报告编号
	public TaskModel() {
		// TODO Auto-generated constructor stub
	}

	

	public TaskModel(String annalid, String form_code, String se_accept_code, String se_type, String apply_type, String apply_kind, String se_flag, String baseinfo_id, String se_code, String make_num, String se_name, String model, String se_num, String remark, String user_id, String data_id, String unitinfo_id, String fk_id_shenbao_form, String reportall_id, Date apply_date, String unit_name, String apply_unit_address, String tel, String apply_person, String linker_shouji, String district_code, String district, String accept_personid, String accept_person, Date accept_date, String testdepart, String test_leader, String testpersons, String testleader, String testperson, String isreceiveperson, String reason, Date isreceivetime, String is_fee, String fee_should_price, String fee_actual_price, String payee_id, String payee_time, String invoice_code, String payee_name, String drawee_name, String drawee_tel, String report_is_take, String report_person_name, String report_take_time, String report_person_tel, String task_flag, String ouid, String in_out_net_flag, String security_code, String construction_num, String remark1, String payment_status, String se_kind, String sydw_id, String reg_dept, String testdepartid, Date printdate, String use_unit, String install_unit, String if_car, String if_qiping, String if_fujian, String sf_fapiao, String use_reg_num, String last_test_date, String if_aqf, String del, String ifcaiwu, String if_yuzhuce, String jiaofeiunit, String hjsqdate, String hjtydate, String hjjsdate, String issuer, Date issue_date, String receiptor, String ttracking_number, String ifcaiwu_date, String isgl, String securitycode, String archive_state, String operate_time, String bjfee, String bjremark, String isbj, String bj_fapiao, String bjxzremark, String isbjsh, String payee_time_bj, String isbjsp, String report_name, String test_book_num) {
		super();
		this.annalid = annalid;
		this.form_code = form_code;
		this.se_accept_code = se_accept_code;
		this.se_type = se_type;
		this.apply_type = apply_type;
		this.apply_kind = apply_kind;
		this.se_flag = se_flag;
		this.baseinfo_id = baseinfo_id;
		this.se_code = se_code;
		this.make_num = make_num;
		this.se_name = se_name;
		this.model = model;
		this.se_num = se_num;
		this.remark = remark;
		this.user_id = user_id;
		this.data_id = data_id;
		this.unitinfo_id = unitinfo_id;
		this.fk_id_shenbao_form = fk_id_shenbao_form;
		this.reportall_id = reportall_id;
		this.apply_date = apply_date;
		this.unit_name = unit_name;
		this.apply_unit_address = apply_unit_address;
		this.tel = tel;
		this.apply_person = apply_person;
		this.linker_shouji = linker_shouji;
		this.district_code = district_code;
		this.district = district;
		this.accept_personid = accept_personid;
		this.accept_person = accept_person;
		this.accept_date = accept_date;
		this.testdepart = testdepart;
		this.test_leader = test_leader;
		this.testpersons = testpersons;
		this.testleader = testleader;
		this.testperson = testperson;
		this.isreceiveperson = isreceiveperson;
		this.reason = reason;
		this.isreceivetime = isreceivetime;
		this.is_fee = is_fee;
		this.fee_should_price = fee_should_price;
		this.fee_actual_price = fee_actual_price;
		this.payee_id = payee_id;
		this.payee_time = payee_time;
		this.invoice_code = invoice_code;
		this.payee_name = payee_name;
		this.drawee_name = drawee_name;
		this.drawee_tel = drawee_tel;
		this.report_is_take = report_is_take;
		this.report_person_name = report_person_name;
		this.report_take_time = report_take_time;
		this.report_person_tel = report_person_tel;
		this.task_flag = task_flag;
		this.ouid = ouid;
		this.in_out_net_flag = in_out_net_flag;
		this.security_code = security_code;
		this.construction_num = construction_num;
		this.remark1 = remark1;
		this.payment_status = payment_status;
		this.se_kind = se_kind;
		this.sydw_id = sydw_id;
		this.reg_dept = reg_dept;
		this.testdepartid = testdepartid;
		this.printdate = printdate;
		this.use_unit = use_unit;
		this.install_unit = install_unit;
		this.if_car = if_car;
		this.if_qiping = if_qiping;
		this.if_fujian = if_fujian;
		this.sf_fapiao = sf_fapiao;
		this.use_reg_num = use_reg_num;
		this.last_test_date = last_test_date;
		this.if_aqf = if_aqf;
		this.del = del;
		this.ifcaiwu = ifcaiwu;
		this.if_yuzhuce = if_yuzhuce;
		this.jiaofeiunit = jiaofeiunit;
		this.hjsqdate = hjsqdate;
		this.hjtydate = hjtydate;
		this.hjjsdate = hjjsdate;
		this.issuer = issuer;
		this.issue_date = issue_date;
		this.receiptor = receiptor;
		this.ttracking_number = ttracking_number;
		this.ifcaiwu_date = ifcaiwu_date;
		this.isgl = isgl;
		this.securitycode = securitycode;
		this.archive_state = archive_state;
		this.operate_time = operate_time;
		this.bjfee = bjfee;
		this.bjremark = bjremark;
		this.isbj = isbj;
		this.bj_fapiao = bj_fapiao;
		this.bjxzremark = bjxzremark;
		this.isbjsh = isbjsh;
		this.payee_time_bj = payee_time_bj;
		this.isbjsp = isbjsp;
		this.report_name = report_name;
		this.test_book_num = test_book_num;
	}



	public String getAnnalid() {
		return annalid;
	}

	public void setAnnalid(String annalid) {
		this.annalid = annalid;
	}

	public String getForm_code() {
		return form_code;
	}

	public void setForm_code(String form_code) {
		this.form_code = form_code;
	}

	public String getSe_accept_code() {
		return se_accept_code;
	}

	public void setSe_accept_code(String se_accept_code) {
		this.se_accept_code = se_accept_code;
	}

	public String getSe_type() {
		return se_type;
	}

	public void setSe_type(String se_type) {
		this.se_type = se_type;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	public String getApply_kind() {
		return apply_kind;
	}

	public void setApply_kind(String apply_kind) {
		this.apply_kind = apply_kind;
	}

	public String getSe_flag() {
		return se_flag;
	}

	public void setSe_flag(String se_flag) {
		this.se_flag = se_flag;
	}

	public String getBaseinfo_id() {
		return baseinfo_id;
	}

	public void setBaseinfo_id(String baseinfo_id) {
		this.baseinfo_id = baseinfo_id;
	}

	public String getSe_code() {
		return se_code;
	}

	public void setSe_code(String se_code) {
		this.se_code = se_code;
	}

	public String getMake_num() {
		return make_num;
	}

	public void setMake_num(String make_num) {
		this.make_num = make_num;
	}

	public String getSe_name() {
		return se_name;
	}

	public void setSe_name(String se_name) {
		this.se_name = se_name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSe_num() {
		return se_num;
	}

	public void setSe_num(String se_num) {
		this.se_num = se_num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

	public String getUnitinfo_id() {
		return unitinfo_id;
	}

	public void setUnitinfo_id(String unitinfo_id) {
		this.unitinfo_id = unitinfo_id;
	}

	public String getFk_id_shenbao_form() {
		return fk_id_shenbao_form;
	}

	public void setFk_id_shenbao_form(String fk_id_shenbao_form) {
		this.fk_id_shenbao_form = fk_id_shenbao_form;
	}

	public String getReportall_id() {
		return reportall_id;
	}

	public void setReportall_id(String reportall_id) {
		this.reportall_id = reportall_id;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getApply_unit_address() {
		return apply_unit_address;
	}

	public void setApply_unit_address(String apply_unit_address) {
		this.apply_unit_address = apply_unit_address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getApply_person() {
		return apply_person;
	}

	public void setApply_person(String apply_person) {
		this.apply_person = apply_person;
	}

	public String getLinker_shouji() {
		return linker_shouji;
	}

	public void setLinker_shouji(String linker_shouji) {
		this.linker_shouji = linker_shouji;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAccept_personid() {
		return accept_personid;
	}

	public void setAccept_personid(String accept_personid) {
		this.accept_personid = accept_personid;
	}

	public String getAccept_person() {
		return accept_person;
	}

	public void setAccept_person(String accept_person) {
		this.accept_person = accept_person;
	}

	public Date getAccept_date() {
		return accept_date;
	}

	public void setAccept_date(Date accept_date) {
		this.accept_date = accept_date;
	}

	public String getTestdepart() {
		return testdepart;
	}

	public void setTestdepart(String testdepart) {
		this.testdepart = testdepart;
	}

	public String getTest_leader() {
		return test_leader;
	}

	public void setTest_leader(String test_leader) {
		this.test_leader = test_leader;
	}

	public String getTestpersons() {
		return testpersons;
	}

	public void setTestpersons(String testpersons) {
		this.testpersons = testpersons;
	}

	public String getTestleader() {
		return testleader;
	}

	public void setTestleader(String testleader) {
		this.testleader = testleader;
	}

	public String getTestperson() {
		return testperson;
	}

	public void setTestperson(String testperson) {
		this.testperson = testperson;
	}

	public String getIsreceiveperson() {
		return isreceiveperson;
	}

	public void setIsreceiveperson(String isreceiveperson) {
		this.isreceiveperson = isreceiveperson;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getIsreceivetime() {
		return isreceivetime;
	}

	public void setIsreceivetime(Date isreceivetime) {
		this.isreceivetime = isreceivetime;
	}

	public String getIs_fee() {
		return is_fee;
	}

	public void setIs_fee(String is_fee) {
		this.is_fee = is_fee;
	}

	public String getFee_should_price() {
		return fee_should_price;
	}

	public void setFee_should_price(String fee_should_price) {
		this.fee_should_price = fee_should_price;
	}

	public String getFee_actual_price() {
		return fee_actual_price;
	}

	public void setFee_actual_price(String fee_actual_price) {
		this.fee_actual_price = fee_actual_price;
	}

	public String getPayee_id() {
		return payee_id;
	}

	public void setPayee_id(String payee_id) {
		this.payee_id = payee_id;
	}

	public String getPayee_time() {
		return payee_time;
	}

	public void setPayee_time(String payee_time) {
		this.payee_time = payee_time;
	}

	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoice_code) {
		this.invoice_code = invoice_code;
	}

	public String getPayee_name() {
		return payee_name;
	}

	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}

	public String getDrawee_name() {
		return drawee_name;
	}

	public void setDrawee_name(String drawee_name) {
		this.drawee_name = drawee_name;
	}

	public String getDrawee_tel() {
		return drawee_tel;
	}

	public void setDrawee_tel(String drawee_tel) {
		this.drawee_tel = drawee_tel;
	}

	public String getReport_is_take() {
		return report_is_take;
	}

	public void setReport_is_take(String report_is_take) {
		this.report_is_take = report_is_take;
	}

	public String getReport_person_name() {
		return report_person_name;
	}

	public void setReport_person_name(String report_person_name) {
		this.report_person_name = report_person_name;
	}

	public String getReport_take_time() {
		return report_take_time;
	}

	public void setReport_take_time(String report_take_time) {
		this.report_take_time = report_take_time;
	}

	public String getReport_person_tel() {
		return report_person_tel;
	}

	public void setReport_person_tel(String report_person_tel) {
		this.report_person_tel = report_person_tel;
	}

	public String getTask_flag() {
		return task_flag;
	}

	public void setTask_flag(String task_flag) {
		this.task_flag = task_flag;
	}

	public String getOuid() {
		return ouid;
	}

	public void setOuid(String ouid) {
		this.ouid = ouid;
	}

	public String getIn_out_net_flag() {
		return in_out_net_flag;
	}

	public void setIn_out_net_flag(String in_out_net_flag) {
		this.in_out_net_flag = in_out_net_flag;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getConstruction_num() {
		return construction_num;
	}

	public void setConstruction_num(String construction_num) {
		this.construction_num = construction_num;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getSe_kind() {
		return se_kind;
	}

	public void setSe_kind(String se_kind) {
		this.se_kind = se_kind;
	}

	public String getSydw_id() {
		return sydw_id;
	}

	public void setSydw_id(String sydw_id) {
		this.sydw_id = sydw_id;
	}

	public String getReg_dept() {
		return reg_dept;
	}

	public void setReg_dept(String reg_dept) {
		this.reg_dept = reg_dept;
	}

	public String getTestdepartid() {
		return testdepartid;
	}

	public void setTestdepartid(String testdepartid) {
		this.testdepartid = testdepartid;
	}

	public Date getPrintdate() {
		return printdate;
	}

	public void setPrintdate(Date printdate) {
		this.printdate = printdate;
	}

	public String getUse_unit() {
		return use_unit;
	}

	public void setUse_unit(String use_unit) {
		this.use_unit = use_unit;
	}

	public String getInstall_unit() {
		return install_unit;
	}

	public void setInstall_unit(String install_unit) {
		this.install_unit = install_unit;
	}

	public String getIf_car() {
		return if_car;
	}

	public void setIf_car(String if_car) {
		this.if_car = if_car;
	}

	public String getIf_qiping() {
		return if_qiping;
	}

	public void setIf_qiping(String if_qiping) {
		this.if_qiping = if_qiping;
	}

	public String getIf_fujian() {
		return if_fujian;
	}

	public void setIf_fujian(String if_fujian) {
		this.if_fujian = if_fujian;
	}

	public String getSf_fapiao() {
		return sf_fapiao;
	}

	public void setSf_fapiao(String sf_fapiao) {
		this.sf_fapiao = sf_fapiao;
	}

	public String getUse_reg_num() {
		return use_reg_num;
	}

	public void setUse_reg_num(String use_reg_num) {
		this.use_reg_num = use_reg_num;
	}

	public String getLast_test_date() {
		return last_test_date;
	}

	public void setLast_test_date(String last_test_date) {
		this.last_test_date = last_test_date;
	}

	public String getIf_aqf() {
		return if_aqf;
	}

	public void setIf_aqf(String if_aqf) {
		this.if_aqf = if_aqf;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getIfcaiwu() {
		return ifcaiwu;
	}

	public void setIfcaiwu(String ifcaiwu) {
		this.ifcaiwu = ifcaiwu;
	}

	public String getIf_yuzhuce() {
		return if_yuzhuce;
	}

	public void setIf_yuzhuce(String if_yuzhuce) {
		this.if_yuzhuce = if_yuzhuce;
	}

	public String getJiaofeiunit() {
		return jiaofeiunit;
	}

	public void setJiaofeiunit(String jiaofeiunit) {
		this.jiaofeiunit = jiaofeiunit;
	}

	public String getHjsqdate() {
		return hjsqdate;
	}

	public void setHjsqdate(String hjsqdate) {
		this.hjsqdate = hjsqdate;
	}

	public String getHjtydate() {
		return hjtydate;
	}

	public void setHjtydate(String hjtydate) {
		this.hjtydate = hjtydate;
	}

	public String getHjjsdate() {
		return hjjsdate;
	}

	public void setHjjsdate(String hjjsdate) {
		this.hjjsdate = hjjsdate;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public String getReceiptor() {
		return receiptor;
	}

	public void setReceiptor(String receiptor) {
		this.receiptor = receiptor;
	}

	public String getTtracking_number() {
		return ttracking_number;
	}

	public void setTtracking_number(String ttracking_number) {
		this.ttracking_number = ttracking_number;
	}

	public String getIfcaiwu_date() {
		return ifcaiwu_date;
	}

	public void setIfcaiwu_date(String ifcaiwu_date) {
		this.ifcaiwu_date = ifcaiwu_date;
	}

	public String getIsgl() {
		return isgl;
	}

	public void setIsgl(String isgl) {
		this.isgl = isgl;
	}

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public String getArchive_state() {
		return archive_state;
	}

	public void setArchive_state(String archive_state) {
		this.archive_state = archive_state;
	}

	public String getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}

	public String getBjfee() {
		return bjfee;
	}

	public void setBjfee(String bjfee) {
		this.bjfee = bjfee;
	}

	public String getBjremark() {
		return bjremark;
	}

	public void setBjremark(String bjremark) {
		this.bjremark = bjremark;
	}

	public String getIsbj() {
		return isbj;
	}

	public void setIsbj(String isbj) {
		this.isbj = isbj;
	}

	public String getBj_fapiao() {
		return bj_fapiao;
	}

	public void setBj_fapiao(String bj_fapiao) {
		this.bj_fapiao = bj_fapiao;
	}

	public String getBjxzremark() {
		return bjxzremark;
	}

	public void setBjxzremark(String bjxzremark) {
		this.bjxzremark = bjxzremark;
	}

	public String getIsbjsh() {
		return isbjsh;
	}

	public void setIsbjsh(String isbjsh) {
		this.isbjsh = isbjsh;
	}

	public String getPayee_time_bj() {
		return payee_time_bj;
	}

	public void setPayee_time_bj(String payee_time_bj) {
		this.payee_time_bj = payee_time_bj;
	}

	public String getIsbjsp() {
		return isbjsp;
	}

	public void setIsbjsp(String isbjsp) {
		this.isbjsp = isbjsp;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public String getTest_book_num() {
		return test_book_num;
	}

	public void setTest_book_num(String test_book_num) {
		this.test_book_num = test_book_num;
	}
	
}
