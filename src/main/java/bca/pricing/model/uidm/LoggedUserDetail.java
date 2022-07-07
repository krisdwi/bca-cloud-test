package bca.pricing.model.uidm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoggedUserDetail {

	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("ad_user_name")
	private String adUserName;
	@JsonProperty("superior_id")
	private String superiorId;
	@JsonProperty("superior_name")
	private String superiorName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("role_code")
	private String roleCode;
	@JsonProperty("office_code")
	private String officeCode;
	@JsonProperty("office_name")
	private String officeName;
	@JsonProperty("dept_code")
	private String deptCode;
	@JsonProperty("dept_name")
	private String deptName;
	@JsonProperty("sub_dept_code")
	private String subDeptCode;
	@JsonProperty("sub_dept_name")
	private String subDeptName;
	@JsonProperty("job_title_code")
	private String jobTitleCode;
	@JsonProperty("job_title_name")
	private String jobTitleName;
	
	@JsonProperty("app_code")
	private String appCode;
	@JsonProperty("user_logged_in")
	private Boolean userLoggedIn;
	@JsonProperty("wrong_password")
	private Integer wrongPassword;
	@JsonProperty("user_locked")
	private Boolean userLocked;
	@JsonProperty("active_from")
	private Date activeFrom;
	@JsonProperty("active_to")
	private Date activeTo;
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("date_last_login")
	private Date dateLastLogin;
	@JsonProperty("date_last_logout")
	private Date dateLastLogout;
	@JsonProperty("date_last_login_fmt")
	private String dateLastLoginFmt;
	@JsonProperty("date_last_logout_fmt")
	private String dateLastLogoutFmt;

	@JsonProperty("officer_code")
	private String officerCode;

	@JsonProperty("mobile_no")
	private String mobileNo;
	@JsonProperty("initial")
	private String initial;
	@JsonProperty("date_last_locked")
	private String dateLastLocked;
	@JsonProperty("date_last_OTP_succeed")
	private String dateLastOTPSucceed;
	@JsonProperty("date_last_locked_fmt")
	private String dateLastLockedFmt;
	@JsonProperty("date_last_OTP_succeed_fmt")
	private String dateLastOTPSucceedFmt;
	@JsonProperty("is_valid_UID")
	private Boolean isValidUID;
	@JsonProperty("is_need_resend_OTP")
	private Boolean isNeedResendOTP;
	@JsonProperty("is_password_registered")
	private Boolean isPasswordRegistered;
}
